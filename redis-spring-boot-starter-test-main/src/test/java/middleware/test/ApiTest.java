package middleware.test;

import middleware.test.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IRedisService redisService;

    @Test
    public void test_set() {
        redisService.set("key_info_user", "xxxxx");
    }

    @Test
    public void test_get() {
        String result = redisService.get("key_info_user");
        logger.info("获取 Redis key：{} 信息：{}", "b_info_user", result);
    }

}
