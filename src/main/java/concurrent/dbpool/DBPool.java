package concurrent.dbpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 数据库连接池
 * 具有延迟等待的机制
 */
public class DBPool {

    //创建存储连接的容器
    static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initPools) {
        for (int i = 0; i < initPools; i++) {
            pool.add(SqlConnectImpl.fetchConnection());
        }
    }

    /**
     * 获取连接
     * @param ms 等待时间，单位ms
     * @return
     */
    public static Connection fetchConnection(int ms) throws InterruptedException {
        synchronized (pool) {
            //等待时间小于0时候
            if (ms <= 0) {
                //判断连接池中是否含有连接，加锁
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                //超时时间 = 等待时间 + 当前时间
                long overTime = ms + System.currentTimeMillis();
                long waitTime = ms;
                //有等待时间和连接池为空时等待
                while (pool.isEmpty() && waitTime > 0){
                    pool.wait();
                    waitTime = overTime - System.currentTimeMillis();
                }
                //如果连接池有则返回一个连接，否则返回空
                if (pool.isEmpty()) {
                    return null;
                }
                return pool.removeFirst();
            }
        }
    }

    /**
     * 释放一个连接
     * @param conn
     */
    public static void release(Connection conn) {
        if (conn!=null) {
            synchronized (pool) {
                pool.addLast(conn);
                //唤醒其他线程
                pool.notifyAll();
            }
        }
    }
}
