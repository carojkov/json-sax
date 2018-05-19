package fail;

import java.io.FileReader;
import java.io.IOException;
import io.jsonsax.JsonSaxParser;
import io.jsonsax.TestJsonSaxListener;
import io.jsonsax.io.ReaderCharSource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-15.
 */
@RunWith(JUnit4.class)
public class FailFilesTest {

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

  @Test
  public void fail12() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail12.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x61 at 1:24");

      parser.parse();
    }
  }

  @Test
  public void fail13() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail13.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("number at 1:40 should not start with `0`");

      parser.parse();
    }
  }

  @Test
  public void fail14() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail14.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x78 at 1:28");

      parser.parse();
    }
  }

  @Test
  public void fail15() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail15.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2B at 1:26");

      parser.parse();
    }
  }

  @Test
  public void fail16() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail16.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x5C at 1:2");

      parser.parse();
    }
  }

  @Test
  public void fail17() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail17.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("xxx");

      parser.parse();
    }
  }

  @Test
  public void fail18() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail18.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2B at 1:26xxx");

      parser.parse();
    }
  }

  @Test
  public void fail19() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail19.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected 0x6E at 1:18");

      parser.parse();
    }
  }

  @Test
  public void fail20() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail20.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x3A at 1:17");

      parser.parse();
    }
  }

  @Test
  public void fail21() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail21.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected 0x2C at 1:26");

      parser.parse();
    }
  }

  @Test
  public void fail22() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail22.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x3A at 1:26");

      parser.parse();
    }
  }

  @Test
  public void fail23() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail23.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("literal `true` expected at 1:15");

      parser.parse();
    }
  }

  @Test
  public void fail24() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail24.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x27 at 1:2");

      parser.parse();
    }
  }

  @Test
  public void fail25() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail25.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x9 at 1:3");

      parser.parse();
    }
  }

  @Test
  public void fail26() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail26.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("xxx");

      parser.parse();
    }
  }

  @Test
  public void fail27() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail27.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("xxx");

      parser.parse();
    }
  }

  @Test
  public void fail28() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail28.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("xxx");

      parser.parse();
    }
  }

  @Test
  public void fail29() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail29.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x5D at 1:4");

      parser.parse();
    }
  }

  @Test
  public void fail30() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail30.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("xxx");

      parser.parse();
    }
  }

  @Test
  public void fail31() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail31.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2D at 1:5");

      parser.parse();
    }
  }

  @Test
  public void fail32() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail32.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x2C at 1:40");

      parser.parse();
    }
  }

  @Test
  public void fail33() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    try (FileReader reader = new FileReader("src/test/resources/fail/fail33.json")) {
      JsonSaxParser parser = new JsonSaxParser(new ReaderCharSource(reader), listener);
      parser.setNumbersAware(false);

      expectedException.expect(IllegalStateException.class);
      expectedException.expectMessage("unexpected char 0x7D at 1:12");

      parser.parse();
    }
  }
}
