package pass;

import java.io.FileReader;
import java.io.IOException;
import jsonsax.JsonSaxParser;
import jsonsax.TestJsonSaxListener;
import jsonsax.io.ReaderCharSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-15.
 */
@RunWith(JUnit4.class)
public class TestPassFiles {

  @Test
  public void pass1() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/pass/pass1.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);
      parser.parse();

    }
  }

  @Test
  public void pass2() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/pass/pass2.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);
      parser.parse();

    }
  }

  @Test
  public void pass3() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/pass/pass3.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);
      parser.parse();

    }
  }

}
