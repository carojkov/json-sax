package jsonsax;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import jsonsax.io.CharSource;
import jsonsax.io.ReaderCharSource;
import jsonsax.io.StringCharSource;

public class JsonSaxParser {

  private final CharSource in;
  private JsonSaxListener listener;

  private boolean isNumbersAware = true;

  public JsonSaxParser(String json, JsonSaxListener listener) {
    this(new StringCharSource(json), listener);
  }

  public JsonSaxParser(Reader reader, JsonSaxListener listener) {
    this(new ReaderCharSource(reader), listener);
  }

  public JsonSaxParser(CharSource json, JsonSaxListener listener) {
    Objects.requireNonNull(listener, "parameter `listener` can not be null");
    this.listener = listener;
    this.in = json;
  }

  public boolean isNumbersAware() {
    return isNumbersAware;
  }

  public void setNumbersAware(boolean numbersAware) {
    isNumbersAware = numbersAware;
  }

  public void parse() throws IOException {
    int c = in.read();

    switch (c) {
      case '[': {
        parseArray();

        break;
      }
      case '{': {
        parseObject();

        break;
      }
      default: {
        throw new IllegalStateException(
            String.format("unexpected char 0x%1$X at %2$s", c, in.location()));
      }
    }
  }

  private void parseArray() throws IOException {
    listener.onArrayStart();

    int c;

    array:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '\n':
        case '\r':
        case '\t':
        case ' ':
          continue;
        case ']': {
          listener.onArrayEnd();

          break array;
        }
        case ',': {
          break;
        }
        default: {
          in.unread();
          break;
        }
      }

      clean:
      while ((c = in.read()) != -1) {
        switch (c) {
          case '\n':
          case '\r':
          case '\t':
          case ' ': {
            continue;
          }
          default: {
            in.unread();
            break clean;
          }
        }
      }

      listener.onArrayElement();

      parseValue();
    }

    if (c == -1) {
      throw new IllegalStateException("expected ']' at " + in.location());
    }
  }

  private void parseObject() throws IOException {
    listener.onObjectStart();

    int c;

    obj:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '\n':
        case '\r':
        case '\t':
        case ' ': {
          break;
        }
        case '}': {
          listener.onObjectEnd();
          break obj;
        }
        case '"': {
          parseProperty();
          break;
        }
        case ',': {
          break;
        }
        default: {
          throw new IllegalStateException(
              String.format("unexpected char 0x%1$X at %2$s", c, in.location()));
        }
      }
    }

    if (c == -1) {
      throw new IllegalStateException("'}' expected at " + in.location());
    }
  }

  private void parseProperty() throws IOException {
    in.mark();

    int c;

    string:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '\\': {
          in.read();
          break;
        }
        case '"': {
          break string;
        }
        default: {
          break;
        }
      }
    }

    int len = in.getPosition() - in.getMark() - 1;

    listener.onProperty(in.getBuffer(), in.getMark(), len);

    in.reset();

    loop:
    while ((c = in.read()) > 0) {
      switch (c) {
        case ' ':
        case '\t':
        case '\n':
        case '\r': {
          break;
        }
        case ':': {
          break loop;
        }
        default: {
          throw new IllegalStateException(
              String.format("unexpected <EOF> at %1$s", in.location()));
        }
      }
    }

    if (c == -1) {
      throw new IllegalStateException(String.format("unexpected <EOF> at %1$s", c, in.location()));
    }

    parseValue();
  }

  private void parseValue() throws IOException {
    int c;

    white:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '\n':
        case '\r':
        case '\t':
        case ' ': {
          break;
        }
        default: {
          break white;
        }
      }
    }

    switch (c) {
      case '"': {
        parseString();
        break;
      }
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      case '-': {
        parseNumber(c);
        break;
      }
      case 'f': {
        parseFalse();
        break;
      }
      case 't': {
        parseTrue();
        break;
      }
      case 'n': {
        parseNull();
        break;
      }
      case '[': {
        parseArray();
        break;
      }
      case '{': {
        parseObject();
        break;
      }
      default: {
        throw new IllegalStateException(
            String.format("unexpected 0x%1$X char at %2$s", c, in.location()));
      }
    }
  }

  private void parseNumber(int k) throws IOException {
    if (isNumbersAware) {
      parseNumberImpl(k);
    } else {
      in.unread();
      parseNumberString();
    }
  }

  private void parseNumberString() throws IOException {
    in.mark();

    int c;

    num:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '-':
        case '+':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case '.':
        case 'e':
        case 'E':
          break;
        default: {
          in.unread();
          break num;
        }
      }
    }

    int len = in.getPosition() - in.getMark();

    listener.onNumber(in.getBuffer(), in.getMark(), len);

    in.reset();
  }

  private void parseNumberImpl(int k) throws IOException {
    int sign = 1;

    long l;

    if (k == '-') {
      sign = -1;
      l = 0;
    } else {
      l = k - '0';
    }

    int c;
    int x = 10;

    reader:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': {
          l = l * x + (c - '0');
          break;
        }
        case '.': {
          break;
        }
        default: {
          in.unread();

          break reader;
        }
      }
    }

    l = l * sign;

    listener.onNumber(l);
  }

  private void parseFalse() throws IOException {
    if (in.read() != 'a' || in.read() != 'l' || in.read() != 's' || in.read() != 'e') {
      throw new IllegalStateException();
    }

    int c = in.read();

    switch (c) {
      case ' ':
      case '\n':
      case '\r':
      case '\t': {
        break;
      }
      case ',':
      case ']':
      case '}': {
        in.unread();
        break;
      }
      default: {
        throw new IllegalStateException(
            String.format("unexpected %1$d char at %2$s", c, in.location()));
      }
    }

    listener.onFalse();
  }

  private void parseTrue() throws IOException {
    if (in.read() != 'r' || in.read() != 'u' || in.read() != 'e') {
      throw new IllegalStateException();
    }

    int c = in.read();

    switch (c) {
      case ' ':
      case '\n':
      case '\r':
      case '\t': {
        break;
      }
      case ',':
      case ']':
      case '}': {
        in.unread();
        break;
      }
      default: {
        throw new IllegalStateException(
            String.format("unexpected %1$d char at %2$s", c, in.location()));
      }
    }

    listener.onTrue();
  }

  private void parseNull() throws IOException {
    if (in.read() != 'u' || in.read() != 'l' || in.read() != 'l') {
      throw new IllegalStateException();
    }

    int c = in.read();

    switch (c) {
      case ' ':
      case '\n':
      case '\r':
      case '\t': {
        break;
      }
      case ',':
      case ']':
      case '}': {
        in.unread();
        break;
      }
      default: {
        throw new IllegalStateException(
            String.format("unexpected %1$d char at %2$s", c, in.location()));
      }
    }

    listener.onNull();
  }

  private void parseString() throws IOException {
    in.mark();

    int c;

    string:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '\\': {
          in.read();
          break;
        }
        case '"': {
          break string;
        }
        default: {
          break;
        }
      }
    }

    int len = in.getPosition() - in.getMark() - 1;

    listener.onStringValue(in.getBuffer(), in.getMark(), len);

    in.reset();
  }
}
