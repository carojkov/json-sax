package io.jsonsax;

public interface JsonSaxListener {

  void onObjectStart();

  void onObjectEnd();

  void onProperty(char[] buffer, int offset, int len);

  void onArrayStart();

  void onArrayElement();

  void onArrayEnd();

  void onStringValue(char[] buffer, int offset, int len);

  void onNull();

  void onFalse();

  void onTrue();

  void onNumber(double l);

  void onNumber(long l);

  void onNumber(char[] buffer, int offset, int len);
}
