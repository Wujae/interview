package io;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by WujaeSebas on 2018/8/29.
 * FUNCTION: TODO
 */
public class StreamTest {

    public static void main(String[] args) throws IOException {

        String chinese = "A是火车王！";

        for (char c : chinese.toCharArray()) {
            System.out.println((int)c);
        }


        ByteArrayInputStream bais8 = new ByteArrayInputStream(chinese.getBytes(Charset.forName("UTF-8")));
        ByteArrayInputStream bais16 = new ByteArrayInputStream(chinese.getBytes(Charset.forName("UTF-16")));

        System.out.println(chinese.length());
        System.out.println(bais8.available());
        System.out.println(bais16.available());     //之所以多了两个字节，是因为String串在最后有一个不可见的'\0'字符

        byte[] testread =new byte[6];
        int readed = bais16.read(testread); //只读取6个字节
        System.out.println(readed);

        Charset cs = Charset.forName ("UTF-16");    //byte 与 char 的转换间需要设定字符流
        ByteBuffer bb = ByteBuffer.allocate (testread.length);
        bb.put (testread);
        bb.flip ();
        CharBuffer cb = cs.decode (bb);

        char[] chars = cb.array();

        System.out.println(String.valueOf(chars));  //可以看到虽然取出是6个字节，但是由于最后一个字节是'\0'，所以只能4个字节的有效信息

        FileInputStream fis = new FileInputStream(new File("F://logs/MDP-CONSOLE.log"));

        int b;
        while ((b = fis.read()) != -1) {
            System.out.print((char) b);
        }

        fis.close();

    }
}
