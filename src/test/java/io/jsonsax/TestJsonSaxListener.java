package io.jsonsax;

/**
 * Created by alex on 2017-05-14.
 */
public class TestJsonSaxListener implements JsonSaxListener {

  private StringBuilder builder = new StringBuilder();

  private boolean[] isObjectHead = new boolean[32];
  private int isObjectHeadIdx = 0;

  private boolean[] isArrayHead = new boolean[32];
  private int isArrayHeadIdx = 0;

  @Override
  public void onObjectStart() {
    builder.append('{');
    isObjectHead[isObjectHeadIdx++] = true;
  }

  @Override
  public void onObjectEnd() {
    builder.append('}');
  }

  @Override
  public void onProperty(char[] buffer, int offset, int len) {
    int idx = isObjectHeadIdx - 1;

    if (!isObjectHead[idx]) {
      builder.append(',');
    }

    isObjectHead[idx] = false;

    builder.append('\'').append(buffer, offset, len).append('\'').append(':');
  }

  @Override
  public void onArrayStart() {
    isArrayHead[isArrayHeadIdx++] = true;
    builder.append('[');
  }

  @Override
  public void onArrayElement() {
    int idx = isArrayHeadIdx - 1;
    if (!isArrayHead[idx]) {
      builder.append(',');
      isArrayHead[idx] = true;
    }
    isArrayHead[idx] = false;
  }

  @Override
  public void onArrayEnd() {
    isArrayHeadIdx--;
    builder.append(']');
  }

  @Override
  public void onStringValue(char[] buffer, int offset, int len) {
    builder.append('\'').append(buffer, offset, len).append('\'');
  }

  @Override
  public void onNull() {
    builder.append("null");
  }

  @Override
  public void onFalse() {
    builder.append("false");
  }

  @Override
  public void onTrue() {
    builder.append("true");
  }

  @Override
  public void onNumber(double l) {
    builder.append(l);
  }

  @Override
  public void onNumber(long l) {
    builder.append(l);
  }

  @Override
  public void onNumber(char[] buffer, int offset, int len) {
    builder.append(buffer, offset, len);
  }

  public String toString() {
    return builder.toString();
  }
}
