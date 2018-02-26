package io.jsonsax.io;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public abstract class CharSourceBaseTest<T extends CharSource> {

  final static String value = "0123456789";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public abstract void read() throws IOException;

  @Test
  public abstract void compare() throws IOException;

  @Test
  public abstract void compare_ThrowsException_WhenNoMarkSet() throws IOException;

  public void read(T in) throws IOException {
    for (int i = 0; i < value.length(); i++) {
      assertThat((char) in.read(), is(value.charAt(i)));
    }

    assertThat(in.read(), is(-1));

    assertThat(in.read(), is(-1));
  }

  public void compare(T in) throws IOException {
    in.mark();

    assertThat(in.read(), is((int) '0'));
    assertThat(in.read(), is((int) '1'));

    assertThat(in.compareMarked("1"), is(false));
    assertThat(in.compareMarked("01"), is(true));
    assertThat(in.compareMarked("0"), is(false));
    assertThat(in.compareMarked("00"), is(false));
    assertThat(in.compareMarked("010"), is(false));
  }

  public void compare_throwsException(T in) throws IOException {
    assertThat((char) in.read(), is('0'));

    expectedException.expect(IllegalStateException.class);

    in.compareMarked("0");
  }

  public void pushLocation(T in) throws IOException {
    in.read();
    in.pushLocation();
    in.read();

    assertThat(in.popLocation(), is("1:1"));
    assertThat(in.location(), is("1:2"));
  }
}
