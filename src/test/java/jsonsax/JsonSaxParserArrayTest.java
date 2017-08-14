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
  public void parse_ArrayOf0() throws IOException {
    String json = "[]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[]"));
  }


  @Test
  public void parse_StringArrayOf1() throws IOException {
    String json = "['a']";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("['a']"));
  }

  @Test
  public void parse_StringArrayOf2() throws IOException {
    String json = "['a', 'b']";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("['a','b']"));
  }

  @Test
  public void parse_LongStringArrayOf2() throws IOException {
    String json = "['sesame', 'open']";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("['sesame','open']"));
  }

  @Test
  public void parse_booleanArray() throws IOException {
    String json = "[true, false]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[true,false]"));
  }

  @Test
  public void parse_nullArray() throws IOException {
    String json = "[true, false, null]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[true,false,null]"));
  }

  @Test
  public void parse_longArray() throws IOException {
    String json = "[-1, 0, 1]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[-1,0,1]"));
  }

  @Test
  public void parse_EmbedArray() throws IOException {
    String json = "[[-1, 0, 1]]";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("[[-1,0,1]]"));
  }
}