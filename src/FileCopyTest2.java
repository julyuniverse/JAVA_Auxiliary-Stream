import java.io.*;

public class FileCopyTest2 {
    public static void main(String[] args) {
        long millisecond = 0;

        // BufferedInputStream, BufferedOutputStream 보조 스트림으로 버퍼에 담아서 더 빠르게 처리한다.
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.zip"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.zip"))) {
            millisecond = System.currentTimeMillis();

            int i;
            while ((i = bis.read()) != -1) {
                bos.write(i);
            }

            millisecond = System.currentTimeMillis() - millisecond;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(millisecond + "밀리초 소요");

        // 결과
        // 136밀리초 소요
    }
}
