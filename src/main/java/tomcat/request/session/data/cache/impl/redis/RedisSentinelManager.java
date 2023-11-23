package tomcat.request.session.data.cache.impl.redis;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

/** author: Ranjith Manickam @ 3 Dec' 2018 */
class RedisSentinelManager extends RedisManager {

    private static final long FAILURE_WAIT_TIME = 2000L;

    static RedisSentinelManager createInstanceBy(Set<String> nodes,
                                                 String masterName,
                                                 String username,
                                                 String password,
                                                 int database,
                                                 int timeout,
                                                 JedisPoolConfig poolConfig) {
        boolean hasUsername = username != null && username.length() > 0;
        if(hasUsername) {
            return new RedisSentinelManager(nodes, masterName, username, password, database, timeout, poolConfig);
        } else {
            return new RedisSentinelManager(nodes, masterName, username, database, timeout, poolConfig);
        }
    }

    RedisSentinelManager(Set<String> nodes,
                         String masterName,
                         String username,
                         String password,
                         int database,
                         int timeout,
                         JedisPoolConfig poolConfig) {
        super(new JedisSentinelPool(masterName, nodes, poolConfig, timeout, username, password, database), FAILURE_WAIT_TIME);
    }

    RedisSentinelManager(Set<String> nodes, String masterName, String password, int database, int timeout, JedisPoolConfig poolConfig) {
        super(new JedisSentinelPool(masterName, nodes, poolConfig, timeout, password, database), 2000L);
    }
}
