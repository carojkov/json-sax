package io.jsonsax;

import static java.lang.Float.parseFloat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-18.
 */
@RunWith(JUnit4.class)
@Ignore
public class NumberTest {

  private TestJsonSaxListener jsonSaxListener;

  @Before
  public void init() {
    jsonSaxListener = new TestJsonSaxListener();
  }

  @Test
  public void testInt() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0]"));
  }

  @Test
  public void testInt1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[1]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[1]"));
  }

  @Test
  public void testIntNegative1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-1]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-1]"));
  }

  @Test
  public void testFloat0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.0]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.0]"));
  }

  @Test
  public void testFloat() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.01]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.01]"));
  }

  @Test
  public void testNegativeFloat() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-0.01]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-0.01]"));
  }

  @Test
  public void testExpNegative() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-1e-1]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-0.1]"));
  }

  @Test
  public void testExp() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-1e+2]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-100.0]"));
  }
}
