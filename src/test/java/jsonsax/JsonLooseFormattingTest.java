package jsonsax;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-15.
 */
@RunWith(JUnit4.class)
public class JsonLooseFormattingTest {

  private TestJsonSaxListener listener;

  @Before
  public void init() {
    listener = new TestJsonSaxListener();
  }

  @Test
  public void test() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[\n]", listener);

    parser.parse();

    assertThat(listener.toString(), is("[]"));
  }

  @Test
  public void test_Obj() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[{}]", listener);

    parser.parse();

    assertThat(listener.toString(), is("[{}]"));
  }

  @Test
  public void test_int() throws IOException {
    String json = "{'x': 12345}";
    json = json.replace('\'', '"');

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'x':12345}"));
  }

}
