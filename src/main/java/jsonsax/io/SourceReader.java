package jsonsax.io;

/**
 * Created by alex on 2017-05-09.
 */
public class SourceReader {

  private String source;
  private int position;
  private int length;

  public SourceReader(String source) {
    this.source = source;
  }

  public int read() {
    if (position < length) {
      return source.charAt(position++);
    } else {
      return -1;
    }
  }

  public int length() {
    return length;
  }

  public SourceReader fill() {
    length = source.length();
    return this;
  }
}
