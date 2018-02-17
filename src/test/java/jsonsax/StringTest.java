package jsonsax;

import static org.hamcrest.Matchers.is;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class StringTest {

  // {"foo":"foo-value"}
  @Test
  public void test_alpha() throws IOException {
    final String in = "{'foo':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));
  }

  // {"foo":"\"\""}
  @Test
  public void test() throws IOException {
    final String in = "{'foo':'\\\"\\\"'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));

    System.out.println(listener.toString());
  }
}
