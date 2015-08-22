package daoyeling;

import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.daoyeling.net.utils.URLUtils;

public class URLUtilsTest {

    @Test
    public void inputReadTest() {
        String url = "http://open.varicom.im";
        InputStreamReader reader = URLUtils.getInputStreamReader(url);
        int c;
        try {
            while((c = reader.read()) != -1) {
                System.out.print((char)c);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
