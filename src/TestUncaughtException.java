/**
 * 如何在主线程中捕获子线程的异常
 * Runnable接口中的run方法原型：public void run();说明线程代码不可以抛出任何checked异常
 * 但是，线程中是可以抛出错误和运行级别异常（RuntimeException），这里error是由vm管控，我们主要关注后者
 * 在子线程抛出运行异常之后，线程会终端，但是对于invoke此线程的主线程不受影响，不会catch到这个异常，它会继续执行自己的代码
 * 结论：线程方法的异常只能自己处理
 *
 * UncaughtExceptionHandler接口，可以在线程抛出异常时回调他的uncaughtException(Thread t, Throwable e)处理异常;
 * 注意：虽然是在回调中处理异常，但是这个回调方法执行时仍在抛出异常的这个线程中；如果线程是通过线程池创建，线程异常发生时不一定会立即回调
 *
 * 扩展：可以搜集子线程抛出的异常，然后统一用Exception的数组搜集起来
 * 线程设计理念：“线程问题应该由内部解决而不应该委托于外部”
 */
public class TestUncaughtException extends ThreadGroup{


    private TestUncaughtException() {
        super("ThreadTest");
    }

    public static void main(String[] args) {
        new Thread(new TestUncaughtException(), new Runnable() {//传入ThreadGroup的内对象
            @Override
            public void run() {
                throw new NullPointerException();
            }
        }).start();
    }

    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Thread:"+ t.getName() + "(" + t.getId() + ")");//线程id，线程标识符
        e.printStackTrace();
    }
}
