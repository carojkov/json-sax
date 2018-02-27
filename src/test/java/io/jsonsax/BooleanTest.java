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
public class BooleanTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void true_() throws IOException {
    final String in = "[true]";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is("[true]"));
  }

  @Test
  public void truth_() throws IOException {
    final String in = "[truth]";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("literal `true` expected at 1:2");

    parser.parse();
  }

  @Test
  public void false_() throws IOException {
    final String in = "[false]";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    parser.parse();

    Assert.assertThat(listener.toString(), is("[false]"));
  }

  @Test
  public void falst_() throws IOException {
    final String in = "[falst]";

    TestJsonSaxListener listener = new TestJsonSaxListener();
    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("literal `false` expected at 1:2");

    parser.parse();
  }
}
