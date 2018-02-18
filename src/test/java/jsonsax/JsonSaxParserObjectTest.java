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
public class JsonSaxParserObjectTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void parse_EmtpyObject() throws IOException {
    String json = "{}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{}"));
  }

  @Test
  public void parse_propertyStringValue() throws IOException {
    String json = "{'foo':'foo-value'}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':'foo-value'}"));
  }

  @Test
  public void parse_propertyStringValue2() throws IOException {
    String json = "{'foo':'foo-value','bar':'bar-value'}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':'foo-value','bar':'bar-value'}"));

  }

  @Test
  public void parse_propertyLongValue0() throws IOException {
    String json = "{'foo':0}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':0}"));


  }

  @Test
  public void parse_propertyLongValue1() throws IOException {
    String json = "{'foo':1}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':1}"));


  }

  @Test
  public void parse_propertyLongValue1Negative() throws IOException {
    String json = "{'foo':-1}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':-1}"));


  }

  @Test
  public void parse_propertyLongMaxValue() throws IOException {
    String json = "{'foo':9223372036854775807}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':9223372036854775807}"));

  }

  @Test
  public void parse_propertyLongMinValue() throws IOException {
    String json = "{'foo':-9223372036854775808}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':-9223372036854775808}"));


  }

  @Test
  public void parse_propertyLongNegativeValue() throws IOException {
    String json = "{'foo':-1}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':-1}"));

  }

  @Test
  public void parse_propertyLongValues() throws IOException {
    String json = "{'foo':1, 'bar':2}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':1,'bar':2}"));
  }

  @Test
  public void parse_propertyFalse() throws IOException {
    String json = "{'foo':false}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':false}"));
  }

  @Test
  public void parse_propertyTrue() throws IOException {
    String json = "{'foo':true}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':true}"));

  }

  @Test
  public void parse_propertyNull() throws IOException {
    String json = "{'foo':null}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    parser.parse();

    assertThat(listener.toString(), is("{'foo':null}"));

  }

  @Test
  public void parse_extraComma() throws IOException {
    String json = "{'foo':null,}";

    json = json.replace('\'', '"');

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(json, listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x7D at 1:13");

    parser.parse();
  }
}
