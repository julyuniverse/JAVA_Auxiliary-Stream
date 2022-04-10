# 여러가지 보조 스트림 클래스들

## 보조 스트림

- 실제 읽고 쓰는 스트림이 아닌 보조 기능을 제공하는 스트림
- FilterInputStream과 FilterOutputStream이 보조 스트림의 상위 클래스들
- 생성자의 매개변수로 또 다른 스트림(기반 스트림이나 다른 보조 스트림)을 가짐
- Decorator Pattern으로 구현됨.
- 상위 클래스 생성자

| 생성자 | 설명 |
| --- | --- |
| protected FilterInputStream(InputStream in) | 생성자의 매개변수로 InputStream을  받음. |
| public FilterOutputStream(OutputStream out) | 생성자의 매개변수로 OutputStream을 받음. |

![png_1](Untitled.png)

## InputStreamReader와 OutputStreamWriter

- 바이트 단위로 읽거나 쓰는 자료를 문자로 변환해 주는 보조 스트림
- FileInputStream으로 읽은 자료를 문자로 변환해 주는 예

```java
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
```

## BufferedInputStream과 BufferedOutputStream

- 약 8k의 배열이 제공되어 입출력이 빠르게 하는 기능이 제공되는 보조 스트림
- BufferedReader와 BufferedWriter는 문자용 입출력 보조 스트림
- BufferedInputStream과 BufferedOutputStream을 이용하여 파일 복사하는 예

```java
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
```

## DataInputStream과 DataOutputStream

- 자료가 메모리에 저장된 상태 그대로 읽거나 쓰는 스트림
- DataInputStream 메서드

| 메서드 | 설명 |
| --- | --- |
| byte readByte() | 1바이트를 읽어 반환함. |
| boolean readBoolean() | 읽은 자료가 0이 아니면 true를, 0이면 false를 반환함. |
| char readChar() | 한 문자를 읽어 반환함. |
| short readShort() | 2바이트를 읽어 정수 값을 반환함. |
| int readInt() | 4바이트를 읽어 정수 값을 반환함. |
| long readLong() | 8바이트를 읽어 정수 값을 반환함. |
| float readFloat() | 4바이트를 읽어 실수 값을 반환함. |
| double readDouble() | 8바이트를 읽어 실수 값을 반환함. |
| String readUTF() | 수정된 UTF-8 인코딩 기반으로 문자열을 읽어 반환함. |
- DataOutputStream 메서드

| 메서드 | 설명 |
| --- | --- |
| void writeByte(int v) | 1바이트의 자료를 출력함. |
| void writeBoolean(boolean v) | 1바이트 값을 출력함. |
| void writeChar(int v) | 2바이트 값을 출력함. |
| void writeShort(int v) | 2바이트 값을 출력함. |
| void writeInt(int v) | 4바이트 값을 출력함. |
| void writeLong(long v) | 8바이트 값을 출력함. |
| void writeFloat(float v) | 4바이트 값을 출력함. |
| void writeDouble(double v) | 8바이트 값을 출력함. |
| void writeUTF(String str) | 수정된 UTF-8 인코딩 기반으로 문자열을 출력함. |

```java
import java.io.*;

public class DataStreamTest {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("data.txt");
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeByte(100);
            dos.writeChar('A');
            dos.writeInt(10);
            dos.writeFloat(3.14f);
            dos.writeUTF("Test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("data.txt");
             DataInputStream dis = new DataInputStream(fis)) {

            System.out.println(dis.readByte());
            System.out.println(dis.readChar());
            System.out.println(dis.readInt());
            System.out.println(dis.readFloat());
            System.out.println(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 결과
        // 100
        // A
        // 10
        // 3.14
        // Test
    }
}
```