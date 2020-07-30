package cn.zhl320.reids;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
    public static void main(String[] args) {
        // 连接 Redis
        Jedis jedis = new Jedis("192.168.199.243", 6379);
        // 如果需要密码
        // jedis.auth("password");
        int i = 0;

        try {
            // 开始毫秒数
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    // 当大于等于1000毫秒（相当于1秒）时，结束操作
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {
            // 关闭连接
            jedis.close();
        }
        // 打印1秒内对Redis的操作次数
        System.out.println("redis 每秒操作：" + i + "次");
    }

    /**
     * Redis 的连接池提供了类 redis.clients.jedis.JedisPool 来创建Redis连接池对象。
     * 使用这个对象，需要使用类 `redis.clients.jedis.JedisPoolConfig` 对连接池进行配置
     */
    public void getJedisPool() {
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        //最大空闲数
        poolCfg.setMaxIdle(50);
        //最大连接数
        poolCfg.setMaxTotal(100);
        //最大等待毫秒数
        poolCfg.setMaxWaitMillis(20000);
        //使用配置创建连接池
        JedisPool pool = new JedisPool(poolCfg, "192.168.199.243");
        //从连接池中获取单个连接
        Jedis jedis = pool.getResource();
        //如果需密码
        //jedis.auth("password");


    }
}
