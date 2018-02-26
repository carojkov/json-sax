package io.jsonsax.io;

import java.io.IOException;

/**
 * Created by alex on 2017-05-09.
 */
public interface CharSource {

  int read() throws IOException;

  boolean compareMarked(String value);

  boolean compareMarked(char[] value);

  void mark();

  void reset();

  char[] getBuffer();

  int getMark();

  int getPosition();

  void unread();

  String location();

  void pushLocation();

  String popLocation();
}
