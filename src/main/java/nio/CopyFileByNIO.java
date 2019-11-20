package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @Description:  以nio的方式copy文件
 * @Author: nianjie.chen
 * @Date: 11/19/2019
 */
public class CopyFileByNIO {

    public void copyFile(String fileName) throws IOException {
        String relativelyPath = System.getProperty("user.dir");
        FileInputStream input = new FileInputStream(relativelyPath + fileName);
        FileOutputStream output = new FileOutputStream(relativelyPath + fileName + "copy");
        //获取channel
        ReadableByteChannel source = input.getChannel();
        WritableByteChannel destination = output.getChannel();
        copyFile(source, destination);
        source.close();
        destination.close();
        System.out.println("copy data success");
    }

    private void copyFile(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        //创建缓冲区（类似数组）
        ByteBuffer buffer = ByteBuffer.allocateDirect(20*1024);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }

    public static void main(String[] args) throws IOException {
        new CopyFileByNIO().copyFile("\\src\\main\\resources\\noiCopy");
    }
}
