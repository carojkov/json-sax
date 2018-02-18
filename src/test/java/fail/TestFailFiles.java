package fail;

import java.io.FileReader;
import java.io.IOException;
import jsonsax.JsonSaxParser;
import jsonsax.TestJsonSaxListener;
import jsonsax.io.ReaderCharSource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-15.
 */
@RunWith(JUnit4.class)
public class TestFailFiles {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void fail1() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail1.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x22 at 1:1");

      parser.parse();
    }
  }

  @Test
  public void fail2() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail2.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected <EOF>");

      parser.parse();
    }
  }

  @Test
  public void fail3() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail3.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x75 at 1:2");

      parser.parse();
    }
  }

  @Test
  public void fail4() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail4.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x5D at 1:16");

      parser.parse();
    }
  }

  @Test
  public void fail5() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail5.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2C at 1:23");

      parser.parse();
    }
  }

  @Test
  public void fail6() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail6.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2C at 1:5");

      parser.parse();
    }
  }

  @Test
  public void fail7() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail7.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2C at 1:26");

      parser.parse();
    }
  }

  @Test
  public void fail8() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail8.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x5D at 1:16");

      parser.parse();
    }
  }

  @Test
  public void fail9() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail9.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x7D at 1:22");

      parser.parse();
    }
  }

  @Test
  public void fail10() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail10.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x22 at 1:35");

      parser.parse();
    }
  }

  @Test
  public void fail11() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail11.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2B at 1:26");

      parser.parse();
    }
  }

}
