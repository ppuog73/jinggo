package com.hbnu;


import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;


public class RedisTest {
    /**测试string类型
     * 问题出现：
     * 1.检查ip地址和端口是否正确
     * 2.防火墙是否关闭
     * 3.redis是否启动，配置文件是否正确
     * */

    @Test
    public void testString() throws InterruptedException {
        Jedis jedis = new Jedis("192.168.153.11",6379);
        jedis.set("name","redis测试");
        System.out.println(jedis.get("name"));

        //key相同，value是会覆盖还是不允许覆盖
        jedis.set("name","redis覆盖测试");
        System.out.println(jedis.get("name"));


        //key值相同，不允许覆盖
        jedis.setnx("name","redis不允许覆盖测试");
        System.out.println(jedis.get("name"));

        /*输出结果
        redis测试
        redis覆盖测试
        redis覆盖测试
        * */

        //设置数据有效时间
        jedis.set("age","18");

        jedis.expire("age",10);

        //为了保证数据操作的有效性，设置数据和设置时间要同时完成，不能一前一后
        jedis.setex("age",10,"100");
        Thread.sleep(3000);
        Long time = jedis.ttl("age");
        System.out.println("改数据还有"+time+"存活");

        //设置数据的时候不允许覆盖，同时设置有效时间
        //XX 允许覆盖    NX不允许覆盖
        //PX 时间单位是毫秒   EX时间单位是秒
        jedis.set("时间","测试添加数据",new SetParams().nx().ex(100));
    }


    @Test
    public void testHash(){
        Jedis jedis = new Jedis("192.168.153.11",6379);
        jedis.hset("user","id","1");
        jedis.hset("user","name","jack");
        jedis.hset("user","age","18");

        System.out.println(jedis.hgetAll("user"));

    }

    @Test
    public void tetList(){
        Jedis jedis = new Jedis("192.168.153.11",6379);
        jedis.lpush("list","1,2,3,4");
        System.out.println(jedis.rpop("list"));

        jedis.lpush("list","1","2","3","4");
        System.out.println(jedis.rpop("list"));

        jedis.lpush("list","1","2","3","4");
        System.out.println(jedis.rpop("list"));

    }
}
