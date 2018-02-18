package jsonsax;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonSaxParserArrayTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();


  @Test
  public void arrayOf0() throws IOException {
    String json = "[]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[]"));
  }


  @Test
  public void stringArrayOf1() throws IOException {
    String json = "['a']";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("['a']"));
  }

  @Test
  public void stringArrayOf2() throws IOException {
    String json = "['a', 'b']";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("['a','b']"));
  }

  @Test
  public void longStringArrayOf2() throws IOException {
    String json = "['sesame', 'open']";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("['sesame','open']"));
  }

  @Test
  public void booleanArray() throws IOException {
    String json = "[true, false]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[true,false]"));
  }

  @Test
  public void nullArray() throws IOException {
    String json = "[true, false, null]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[true,false,null]"));
  }

  @Test
  public void longArray() throws IOException {
    String json = "[-1, 0, 1]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[-1,0,1]"));
  }

  @Test
  public void embedArray() throws IOException {
    String json = "[[-1, 0, 1]]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[[-1,0,1]]"));
  }

  @Test
  public void arrayWithNullElement() throws IOException {
    String json = "[null]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[null]"));
  }

  @Test
  public void missingFirstValue() throws IOException {
    String json = "[,null]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2C at 1:2");

    parser.parse();
  }

  @Test
  public void missingLastValue() throws IOException {
    String json = "[null,]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x5D at 1:7");

    parser.parse();
  }

  @Test
  public void missingValue() throws IOException {
    String json = "[null,,null]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2C at 1:7");

    parser.parse();
  }

  @Test
  public void missingValue1() throws IOException {
    String json = "[null,,]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2C at 1:7");

    parser.parse();
  }

}