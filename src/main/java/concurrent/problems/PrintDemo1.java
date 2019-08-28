package concurrent.problems;

class PrintDemo1 {
    public static void main(String[] args) {
        final PrintDemo1 demo2 = new PrintDemo1();
        Thread t1 = new Thread(demo2::print1);
        Thread t2 = new Thread(demo2::print2);

        t1.start();
        t2.start();
    }

    public synchronized void print2() {
        for (int i = 1; i <= 100; i += 2) {
            System.out.println(i);
            this.notify();
            try {
                this.wait();
                // 防止打印速度过快导致混乱
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // NO
            }
        }
    }

    public synchronized void print1() {
        for (int i = 0; i <= 100; i += 2) {
            System.out.println(i);
            this.notify();
            try {
                this.wait();
                // 防止打印速度过快导致混乱
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // NO
            }
        }
    }
}