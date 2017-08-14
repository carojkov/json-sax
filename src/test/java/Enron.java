import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import jsonsax.JsonSaxListener;
import jsonsax.JsonSaxParser;

/**
 * Created by alex on 2017-05-14.
 */
public class Enron {

  public static void main(String[] args) throws IOException {

    File file = new File("src/test/resources/enron.json");

    Reader reader = new FileReader(file);

    JsonSaxListener listener = new JsonSaxListener() {
      @Override
      public void onObjectStart() {

      }

      @Override
      public void onObjectEnd() {

      }

      @Override
      public void onProperty(char[] buffer, int offset, int len) {

      }

      @Override
      public void onArrayStart() {

      }

      @Override
      public void onArrayElement() {

      }

      @Override
      public void onArrayEnd() {

      }

      @Override
      public void onStringValue(char[] buffer, int offset, int len) {

      }

      @Override
      public void onNull() {

      }

      @Override
      public void onFalse() {

      }

      @Override
      public void onTrue() {

      }

      @Override
      public void onNumber(double l) {

      }

      @Override
      public void onNumber(long l) {

      }
    };

    JsonSaxParser parser = new JsonSaxParser(reader, listener);

    parser.parse();

  }

}
