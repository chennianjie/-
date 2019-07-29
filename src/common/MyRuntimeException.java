package common;

/**
 * 自定义运行时异常
 */
public class MyRuntimeException extends RuntimeException {

    public MyRuntimeException(){}

    public MyRuntimeException(String mess) {
        super(mess);
    }
}
