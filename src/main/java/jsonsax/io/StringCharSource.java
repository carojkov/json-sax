package jsonsax.io;

import java.util.Objects;

/**
 * Created by alex on 2017-05-09.
 */
public class StringCharSource extends BaseCharSource implements CharSource {

  public StringCharSource(String value) {
    super();

    Objects.requireNonNull(value, "parameter `value` can not be null");

    this.buffer = value.toCharArray();
    this.length = buffer.length;
  }

  public int readImpl() {
    if (position < length) {
      return buffer[position++];
    } else {
      return -1;
    }
  }
}
