package jsonsax;

import static org.hamcrest.Matchers.is;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-18.
 */
@RunWith(JUnit4.class)
public class StringNumberTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private TestJsonSaxListener jsonSaxListener;

  @Before
  public void init() {
    jsonSaxListener = new TestJsonSaxListener();
  }

  @Test
  public void __() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[--]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2D at 1:3");

    parser.parse();
  }

  @Test
  public void _Plus() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-+]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2B at 1:3");

    parser.parse();
  }

  @Test
  public void _PlusPlus() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[++]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2B at 1:2");

    parser.parse();
  }

  @Test
  public void _PlusMinus() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[+-]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2B at 1:2");

    parser.parse();
  }

  @Test
  public void _0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0]"));
  }

  @Test
  public void _1234567890() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[1234567890]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[1234567890]"));
  }

  @Test
  public void _0_0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.0]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.0]"));

  }

  @Test
  public void _0_0_0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.0.]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2E at 1:5");

    parser.parse();
  }

  @Test
  public void _0_00() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.00]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.00]"));
  }

  @Test
  public void _0_1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.1]"));
  }

  @Test
  public void _0_01() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.01]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.01]"));
  }

  @Test
  public void _0_134567890() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0.1234567890]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0.1234567890]"));
  }

  @Test
  public void negative_0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-0]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-0]"));
  }

  @Test
  public void negative_1234567890() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-1234567890]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-1234567890]"));
  }

  @Test
  public void negative_0_1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-0.1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-0.1]"));
  }

  @Test
  public void negative_0_1234567890() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-0.1234567890]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-0.1234567890]"));
  }

  @Test
  public void e() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[e]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x65 at 1:2");

    parser.parse();
  }

  @Test
  public void E() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[E]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x45 at 1:2");

    parser.parse();
  }

  @Test
  public void ee() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0ee]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x65 at 1:4");

    parser.parse();
  }

  @Test
  public void Ee() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0Ee]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x65 at 1:4");

    parser.parse();
  }

  @Test
  public void eE() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0eE]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x45 at 1:4");

    parser.parse();
  }

  @Test
  public void EE() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0EE]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x45 at 1:4");

    parser.parse();
  }

  @Test
  public void _0e__0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e--0]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2D at 1:5");

    parser.parse();
  }

  @Test
  public void _0ePlus_0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e+-0]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2D at 1:5");

    parser.parse();
  }

  @Test
  public void _0ePlusPlus0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e++0]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2B at 1:5");

    parser.parse();
  }

  @Test
  public void _0e_Plus0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e-+0]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2B at 1:5");

    parser.parse();
  }

  @Test
  public void _0e0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e0]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0e0]"));
  }

  @Test
  public void _1e0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[+1e0]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2B at 1:2");

    parser.parse();
  }

  @Test
  public void __1e0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-1e0]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-1e0]"));
  }

  @Test
  public void _0e1234567890() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e1234567890]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0e1234567890]"));
  }

  @Test
  public void _102() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[102]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[102]"));
  }

  @Test
  public void _1e1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[1e1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[1e1]"));
  }

  @Test
  public void _1e1234567890() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[1e1234567890]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[1e1234567890]"));
  }

  @Test
  public void _1ePlus1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[1e+1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[1e+1]"));
  }

  @Test
  public void _1e_1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[1e-1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[1e-1]"));
  }

  @Test
  public void _1EPlus2() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-1E+2]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-1E+2]"));
  }

  @Test
  public void _0e1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0e1]"));
  }

  @Test
  public void _0ePlus1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e+1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0e+1]"));
  }

  @Test
  public void _0e_1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e-1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[0e-1]"));
  }

  @Test
  public void __0e_1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[-0e-1]", jsonSaxListener);
    parser.setNumbersAware(false);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[-0e-1]"));
  }

  @Test
  public void _0e() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0e]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x5D at 1:4");

    parser.parse();
  }

  @Test
  public void _0x() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[0x]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x78 at 1:3");

    parser.parse();
  }

  @Test
  public void _00() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[01]", jsonSaxListener);
    parser.setNumbersAware(false);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("number at 1:2 should not start with `0`");

    parser.parse();
  }

}
