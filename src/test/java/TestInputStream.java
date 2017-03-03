import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CharacterCodingException;

/**
 * Created by incre on 27.02.2017.
 */
public class TestInputStream extends InputStream {
    private String input = "";

    @Override
    public int read() throws IOException {
        if (input.length() == 0) {
            return -1;
        }


        char result = input.charAt(0);

        input = input.substring(1);

        return result;
    }

    public void add(String text) {
        input += text + "\n";
    }

    public String getData() {
        return input;
    }

    @Override
    public synchronized void reset() {
        input = "";
    }
}
