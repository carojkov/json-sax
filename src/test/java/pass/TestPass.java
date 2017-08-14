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
public class TestPass {

  @Test
  public void test() throws IOException {
    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(
        new ReaderCharSource(new FileReader("src/test/resources/pass/pass1.json")), listener);
    parser.parse();
  }

}
