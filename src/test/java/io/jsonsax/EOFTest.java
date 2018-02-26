package io.jsonsax;

import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EOFTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();


  @Test
  public void unexpectedComma() throws IOException {
    String in = "[]\n\r\t ,";

    TestJsonSaxListener listener = new TestJsonSaxListener();

    JsonSaxParser parser = new JsonSaxParser(in.replace('\'', '"'), listener);

    expectedException.expect(IllegalStateException.class);
    expectedException.expectMessage("unexpected char 0x2C at 2:4");

    parser.parse();
  }

}
