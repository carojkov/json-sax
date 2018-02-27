package io.jsonsax;

import io.jsonsax.io.CharSource;
import io.jsonsax.io.ReaderCharSource;
import io.jsonsax.io.StringCharSource;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

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
        unexpectedInput(c);
      }
    }

    while ((c = in.read()) != -1) {
      switch (c) {
        case '\n':
        case '\r':
        case '\t':
        case ' ': {
          break;
        }
        default: {
          unexpectedInput(c);
        }
      }
    }
  }

  private void parseArray() throws IOException {
    listener.onArrayStart();

    int c;

    boolean expectValue = true;
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
          if (expectValue) {
            String message = String.format("unexpected char 0x%1$X at %2$s", c, in.location());

            throw new IllegalStateException(message);
          }

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

      expectValue = false;
    }

    if (c != ']') {
      String message;
      if (c == -1) {
        message = String.format("unexpected <EOF>");
      } else {
        message = String.format("unexpected char 0x%1$X at %2$s", c, in.location());
      }

      throw new IllegalStateException(message);
    }
  }

  private void parseObject() throws IOException {
    listener.onObjectStart();

    int c;

    boolean isFollowingComma = false;
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
          if (isFollowingComma) {
            unexpectedInput(c);
          }

          listener.onObjectEnd();

          break obj;
        }
        case '"': {
          parseProperty();

          isFollowingComma = false;

          break;
        }
        case ',': {
          if (isFollowingComma) {
            unexpectedInput(c);
          }

          isFollowingComma = true;

          in.pushLocation();

          break;
        }
        default: {
          unexpectedInput(c);
        }
      }
    }

    if (isFollowingComma) {
      unexpectedInput(',', in.popLocation());
    } else if (c == -1) {
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
        case '\n':
        case '\r':
        case '\t':
        case ' ': {
          break;
        }
        case ':': {
          break loop;
        }
        default: {
          throw new IllegalStateException(
              String.format("unexpected 0x%1$X at %2$s", c, in.location()));
        }
      }
    }

    if (c == -1) {
      throw new IllegalStateException("unexpected <EOF>");
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
        unexpectedInput(c);
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

  // 0 10 -1 -1e12 -1e+2 1.1 1.1e3
  // 01 -- +10 0x2 1x ee -01
  private void parseNumberString() throws IOException {
    in.mark();

    final int head = in.read();
    in.pushLocation();
    in.unread();

    boolean isZero = head == '0';

    int dotIndex = -1;

    int xc = -1, c;

    mantissa:
    while ((c = in.read()) != -1) {
      switch (c) {
        case '-': {
          if (xc != -1 || xc == '+' || xc == '-') {
            unexpectedInput(c);
          }

          break;
        }
        case '0': {
          break;
        }
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': {
          isZero = false;
          break;
        }
        case '.': {
          if (dotIndex > -1) {
            unexpectedInput(c);
          }

          dotIndex = in.getPosition();

          break;
        }
        case 'e':
        case 'E':

          break mantissa;
        default: {

          break mantissa;
        }
      }

      xc = c;
    }

    if (isZero) {

    } else if (dotIndex > -1) {

    } else if (c == 'e') {

    } else if (c == 'E') {

    } else if (head == '0') {
      throw new IllegalStateException(
          String.format("number at %1$s should not start with `0`", in.popLocation()));
    }

    xc = c;

    if (c == 'e' || c == 'E') {
      int sign = -1;
      exponent:
      while ((c = in.read()) != -1) {
        switch (c) {
          case '-':
          case '+': {
            if (sign > -1) {
              unexpectedInput(c);
            }

            sign = c;
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
          case '9': {
            break;
          }
          case '.': {
            unexpectedInput(c);

            break;
          }
          case 'e':
          case 'E': {
            unexpectedInput(c);

            break;
          }
          default: {

            break exponent;
          }
        }
        xc = c;
      }
    }

    if (xc == 'e' || xc == 'E') {
      unexpectedInput(c);
    }

    in.unread();

    int len = in.getPosition() - in.getMark();

    listener.onNumber(in.getBuffer(), in.getMark(), len);

    in.reset();
  }

  private void unexpectedInput(int c) {
    unexpectedInput(c, in.location());
  }

  private void unexpectedInput(int c, String location) {
    throw new IllegalStateException(
        String.format("unexpected char 0x%1$X at %2$s", c, location));
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
    in.pushLocation();

    if (in.read() != 'a' || in.read() != 'l' || in.read() != 's' || in.read() != 'e') {
      throw new IllegalStateException(
          String.format("literal `false` expected at %1$s", in.popLocation()));
    }

    int c = in.read();

    switch (c) {
      case '\n':
      case '\r':
      case '\t':
      case ' ': {
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
    in.pushLocation();
    if (in.read() != 'r' || in.read() != 'u' || in.read() != 'e') {
      throw new IllegalStateException(
          String.format("literal `true` expected at %1$s", in.popLocation()));
    }

    int c = in.read();

    switch (c) {
      case '\n':
      case '\r':
      case '\t':
      case ' ': {
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
      case '\n':
      case '\r':
      case '\t':
      case ' ': {
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
          in.pushLocation();
          c = in.read();
          switch (c) {
            case '"':
            case '\\':
            case '/':
            case 'b':
            case 'f':
            case 'n':
            case 'r':
            case 't':
              break;
            default: {
              throw new IllegalStateException(
                  String.format("illegal escape sequence at %1$s", in.popLocation()));
            }
          }
          break;
        }
        case '"': {
          break string;
        }
        case '\t': {
          unexpectedInput(c);
          break;
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
