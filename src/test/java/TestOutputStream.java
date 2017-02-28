import java.io.ByteArrayOutputStream;

/**
 * Created by incre on 28.02.2017.
 */
public class TestOutputStream extends ByteArrayOutputStream {
    public String getData() {
        return new String(this.toByteArray());
    }
}
