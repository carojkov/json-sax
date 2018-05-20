package io.jsonsax.io;

import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.StringReader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-09.
 */
@RunWith(JUnit4.class)
public class ReaderCharSourceTest extends CharSourceBaseTest<ReaderCharSource> {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Override
  public void read() throws IOException {
    read(new ReaderCharSource(new StringReader(value)));
  }

  @Override
  public void compare() throws IOException {
    read(new ReaderCharSource(new StringReader(value)));
  }

  @Override
  public void compare_ThrowsException_WhenNoMarkSet() throws IOException {
    compare_throwsException(new ReaderCharSource(new StringReader(value)));
  }

  @Test
  public void testRead_WithSmallBuffer() throws IOException {
    read(new ReaderCharSource(new StringReader(value), 2));
  }

  @Test
  public void read_throwsIllegalStateException_WhenMarkedRegionCantShift() throws IOException {
    CharSource in = new ReaderCharSource(new StringReader(value), 2);
    in.mark();

    Assert.assertThat((char) in.read(), is('0'));
    Assert.assertThat((char) in.read(), is('1'));

    exception.expect(IllegalStateException.class);

    in.read();
  }

  @Test
  public void read_succeeds_WhenMarkReset() throws IOException {
    CharSource in = new ReaderCharSource(new StringReader(value), 2);
    in.mark();

    Assert.assertThat((char) in.read(), is('0'));
    Assert.assertThat((char) in.read(), is('1'));

    in.reset();

    Assert.assertThat((char) in.read(), is('2'));
    Assert.assertThat((char) in.read(), is('3'));
    Assert.assertThat((char) in.read(), is('4'));
    Assert.assertThat((char) in.read(), is('5'));
    Assert.assertThat((char) in.read(), is('6'));
    Assert.assertThat((char) in.read(), is('7'));
    Assert.assertThat((char) in.read(), is('8'));
    Assert.assertThat((char) in.read(), is('9'));
    Assert.assertThat(in.read(), is(-1));
    Assert.assertThat(in.read(), is(-1));
  }

  @Test
  public void read_Succeeds_WhenMarkedRegionCanShift() throws IOException {
    CharSource in = new ReaderCharSource(new StringReader(value), 2);
    Assert.assertThat((char) in.read(), is('0'));
    in.mark();
    Assert.assertThat((char) in.read(), is('1'));
    Assert.assertThat((char) in.read(), is('2'));

    exception.expect(IllegalStateException.class);

    in.read();
  }


  @Test
  public void pushLocation() throws IOException {
    pushLocation(new ReaderCharSource(new StringReader(value)));
  }

  @Test
  public void lfLocation() throws IOException {
    ReaderCharSource in = new ReaderCharSource(new StringReader("0\n1"));

    super.lfLocation(in);
  }

  @Test
  public void toString_() throws IOException {
    CharSource in = new ReaderCharSource(new StringReader(value));

    in.read();

    Assert.assertThat(in.toString(), Matchers.is("ReaderCharSource[0:1]"));
  }
}
