package jsonsax.io;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alex on 2017-05-09.
 */
@RunWith(JUnit4.class)
public class StringCharSourceTest extends CharSourceBaseTest<StringCharSource> {

  @Test
  public void read() throws IOException {
    super.read(new StringCharSource(value));
  }

  @Test
  public void compare() throws IOException {
    super.compare(new StringCharSource(value));
  }

  @Override
  public void compare_ThrowsException_WhenNoMarkSet() throws IOException {
    compare_throwsException(new StringCharSource(value));
  }
}
