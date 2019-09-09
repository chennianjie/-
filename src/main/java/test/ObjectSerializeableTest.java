package test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * @Description:  测试transient和对象序列化
 * 用transient修饰过的变量都无法进行序列化
 * @Author: nianjie.chen
 * @Date: 9/9/2019
 */
public class ObjectSerializeableTest implements Serializable{

        /**
         *
         */
        private static final long serialVersionUID = 1710022455003682613L;
        private Integer width;
        private Integer height;
        private transient Integer area;

        public ObjectSerializeableTest (Integer width, Integer height){
            this.width = width;
            this.height = height;
            this.area = width * height;
        }

        public void setArea(){
            this.area = this.width * this.height;
        }

        @Override
        public String toString(){
            StringBuffer sb = new StringBuffer(40);
            sb.append("width : ");
            sb.append(this.width);
            sb.append("\nheight : ");
            sb.append(this.height);
            sb.append("\narea : ");
            sb.append(this.area);
            return sb.toString();
        }



        public static void main(String args[]) throws Exception {
            ObjectSerializeableTest ObjectSerializeableTest = new ObjectSerializeableTest(3, 4);
            System.out.println("1.原始对象\n" + ObjectSerializeableTest);
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("ObjectSerializeableTest"));
            // 往流写入对象
            o.writeObject(ObjectSerializeableTest);
            o.close();

            // 从流读取对象
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("ObjectSerializeableTest"));
            ObjectSerializeableTest ObjectSerializeableTest1 = (ObjectSerializeableTest) in.readObject();
            System.out.println("2.反序列化后的对象\n" + ObjectSerializeableTest1);
            ObjectSerializeableTest1.setArea();
            System.out.println("3.恢复成原始对象\n" + ObjectSerializeableTest1);
            in.close();

        }
}
