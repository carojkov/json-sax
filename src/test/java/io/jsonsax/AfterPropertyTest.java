package io.jsonsax;

import static org.hamcrest.Matchers.is;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class AfterPropertyTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void parse_Space() throws IOException {
    final String in = "{'foo' :'foo-value'}";
    final String out = "{'foo':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(out));
  }

  @Test
  public void parse_NewLine() throws IOException {
    final String in = "{'foo'\n:'foo-value'}";
    final String out = "{'foo':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(out));
  }

  @Test
  public void parse_Return() throws IOException {
    final String in = "{'foo'\r :'foo-value'}";
    final String out = "{'foo':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(out));
  }

  @Test
  public void parse_Tab() throws IOException {
    final String in = "{'foo'\t :'foo-value'}";
    final String out = "{'foo':'foo-value'}";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is(out));
  }
}
