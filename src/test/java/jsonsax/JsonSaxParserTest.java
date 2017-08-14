package jsonsax;

import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.Reader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonSaxParserTest {

  private TestJsonSaxListener jsonSaxListener;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void init() {
    jsonSaxListener = new TestJsonSaxListener();
  }

  @Test
  public void parse_calls_ObjectStartEnd() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("{}", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("{}"));
  }

  @Test
  public void parse_calls_ArrayStartEnd() throws IOException {
    JsonSaxParser parser = new JsonSaxParser("[]", jsonSaxListener);

    parser.parse();

    Assert.assertThat(jsonSaxListener.toString(), is("[]"));
  }

  @Test
  public void parse_throws_NPE_whenJsonIsNull() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("parameter `value` can not be null");

    new JsonSaxParser((String) null, jsonSaxListener);
  }

  @Test
  public void parse_throws_NPE_whenReaderIsNull() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("parameter `in` can not be null");

    new JsonSaxParser((Reader) null, jsonSaxListener);
  }


  @Test
  public void parse_throws_NPE_whenListenerIsNull() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("parameter `listener` can not be null");

    new JsonSaxParser("{}", null);
  }
}
