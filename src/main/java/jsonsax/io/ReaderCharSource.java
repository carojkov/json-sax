package jsonsax.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * Created by alex on 2017-05-09.
 */
public class ReaderCharSource extends BaseCharSource implements CharSource {

  private Reader in;
  private int bufferSize;

  public ReaderCharSource(Reader reader) {
    this(reader, 1024);
  }

  public ReaderCharSource(Reader in, int bufferSize) {
    Objects.requireNonNull(in, "parameter `in` can not be null");

    this.buffer = new char[bufferSize];
    this.bufferSize = bufferSize;

    this.in = in;
  }

  public int readImpl() throws IOException {
    if (position < length) {
      return buffer[position++];
    }

    if (length == bufferSize && mark > -1) {
      if (bufferSize - mark > mark) {
        throw new IllegalStateException();
      }

      System.arraycopy(buffer, mark, buffer, 0, bufferSize - mark);

      position = bufferSize - mark;
      length = position;
      mark = 0;
    } else if (length == bufferSize) {
      position = 0;
      length = 0;
    }

    int len = in.read(buffer, position, bufferSize - position);

    if (len == -1) {
      return -1;
    }

    length += len;

    return buffer[position++];
  }
}
