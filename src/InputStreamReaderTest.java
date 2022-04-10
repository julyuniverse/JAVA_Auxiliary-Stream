import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("reader.txt"))) {
            int i;
            while ((i = isr.read()) != -1) { // isr: 보조 스트림으로 읽음.
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 결과
        // hello 안녕하세요
    }
}
