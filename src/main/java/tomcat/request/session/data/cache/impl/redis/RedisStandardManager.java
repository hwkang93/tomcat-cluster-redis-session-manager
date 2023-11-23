package tomcat.request.session.data.cache.impl.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** author: Ranjith Manickam @ 12 Jul' 2018 */
class RedisStandardManager extends RedisManager {

    private static final long FAILURE_WAIT_TIME = 2000L;

    static RedisStandardManager createInstanceBy(String host,
                                                 int port,
                                                 String username,
                                                 String password,
                                                 int database,
                                                 int timeout,
                                                 JedisPoolConfig poolConfig) {
        boolean hasUsername = username != null && username.length() > 0;
        if(hasUsername) {
            return new RedisStandardManager(host, port, username, password, database, timeout, poolConfig);
        } else {
            return new RedisStandardManager(host, port, password, database, timeout, poolConfig);
        }
    }


    RedisStandardManager(String host,
                         int port,
                         String username,
                         String password,
                         int database,
                         int timeout,
                         JedisPoolConfig poolConfig) {
        super(new JedisPool(poolConfig, host, port, timeout, username, password, database), FAILURE_WAIT_TIME);
    }

    RedisStandardManager(String host, int port, String password, int database, int timeout, JedisPoolConfig poolConfig) {
        super(new JedisPool(poolConfig, host, port, timeout, password, database), FAILURE_WAIT_TIME);
    }
}
