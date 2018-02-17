package jsonsax;

import static org.hamcrest.Matchers.is;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class PropertyNameTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void parse_Alpha() throws IOException {
    final String in = "{'foo':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));
  }

  @Test
  public void parse_AlphaNum() throws IOException {
    final String in = "{'2foo2':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));
  }

  @Test
  public void parse_AlphaDash() throws IOException {
    final String in = "{'foo-2':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));
  }

  @Test
  public void parse_space() throws IOException {
    final String in = "{'foo 2':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));
  }

  @Test
  public void parse_EscapedDblQuote() throws IOException {
    final String in = "{'foo\\\"':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(in));
  }

  @Test
  public void parse_unexpectedCharacter() throws IOException {
    final String in = "{'foo\"':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected <EOF> at 1:7");

    parser.parse();
  }

}
