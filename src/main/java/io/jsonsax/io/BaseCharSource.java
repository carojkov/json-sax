package io.jsonsax.io;

import java.io.IOException;

/**
 * Created by alex on 2017-05-14.
 */
public abstract class BaseCharSource implements CharSource {

  protected char[] buffer;
  protected int length = 0;
  protected int position = 0;
  protected int mark = -1;

  //
  protected long lfLine = 0;
  protected long lfPos = 0;
  //
  protected long xPosLine = -1;
  protected long xPosChar = -1;
  protected long posLine = 0;
  protected long posChar = 0;

  public void mark() {
    mark = position;
  }

  public void reset() {
    mark = -1;
  }

  public char[] getBuffer() {
    return buffer;
  }

  public int getMark() {
    return mark;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public int read() throws IOException {
    int c = readImpl();

    switch (c) {
      case '\n': {
        lfLine++;
        lfPos = posChar + 1;
        posLine++;
        posChar = 0;
        break;
      }
      default: {
        posChar++;
      }
    }

    return c;
  }


  public abstract int readImpl() throws IOException;

  @Override
  public void unread() {
    position--;
    posChar--;
  }

  public boolean compareMarked(String value) {
    return compareMarked(value.toCharArray());
  }

  public boolean compareMarked(char[] value) {
    if (mark == -1) {
      throw new IllegalStateException();
    }

    if (value.length != position - mark) {
      return false;
    }

    int i = mark;
    int j = 0;
    for (; i < value.length; i++, j++) {
      if (buffer[i] != value[j]) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String location() {
    return Long.toString(posLine + 1) + ':' + Long.toString(posChar);
  }

  @Override
  public void pushLocation() {
    xPosLine = posLine;
    xPosChar = posChar;
  }

  @Override
  public String popLocation() {
    return Long.toString(xPosLine + 1) + ':' + Long.toString(xPosChar);
  }

  @Override
  public String lfLocation() {
    return Long.toString(lfLine) + ':' + Long.toString(lfPos);
  }

  public final String toString() {
    return this.getClass().getSimpleName() + "[" + posLine + ':' + posChar + ']';
  }
}
