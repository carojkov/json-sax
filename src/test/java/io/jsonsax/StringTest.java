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
public class StringTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

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
  }

  @Test
  public void parse_ThrowsISE_onTabsInString() throws IOException {
    //tab symbol in string
    final String in = "['\t']";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x9 at 1:3");

    parser.parse();
  }

  @Test
  public void parse_ThrowsISE_onIllegalEscapeChar() throws IOException {
    //tab symbol in string
    final String in = "['\\\"\\\\\\/\\b\\f\\n\\r\\t\\x']";

    System.out.println(in);

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("illegal escape sequence at 1:19");

    parser.parse();
  }

}
