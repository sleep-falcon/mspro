package middleware.test.interfaces;

import middleware.ratelimiter.annotation.DoRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 测试：http://localhost:8081/api/queryUserInfo?userId=127
     */
    @DoRateLimiter(permitsPerSecond = 1, returnJson = "{\"code\":\"5xx\",\"info\":\"调用方法超过最大次数，限流返回！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) throws InterruptedException {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("xx:" + userId, 18, "xxxxx");
    }

}
