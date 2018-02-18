package jsonsax;

import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormatTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  // '{'
  @Test
  public void expect_EndOfObject() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("{", new TestJsonSaxListener());

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("'}' expected at 1:2");

    parser.parse();
  }

  // '[{'
  @Test
  public void expect_EndOfObject_0() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[{", new TestJsonSaxListener());

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("'}' expected at 1:3");

    parser.parse();
  }

  // '[{]'
  @Test
  public void expect_EndOfObject_1() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[{]", new TestJsonSaxListener());

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x5D at 1:3");

    parser.parse();
  }

  // '{[]}'
  @Test
  public void expect_property() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("{[]}", new TestJsonSaxListener());

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x5B at 1:2");

    parser.parse();
  }


  @Test
  public void expect_EndOfArray() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[", new TestJsonSaxListener());

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("expected ']' at 1:2");

    parser.parse();
  }
}
