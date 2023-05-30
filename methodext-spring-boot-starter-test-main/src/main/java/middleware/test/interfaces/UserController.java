package middleware.test.interfaces;

import middleware.methodext.annotation.DoMethodExt;
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
     * 放行：http://localhost:8081/api/queryUserInfo?userId=127
     * 拦截：http://localhost:8081/api/queryUserInfo?userId=222
     */
    @DoMethodExt(method = "blacklist", returnJson = "{\"code\":\"5xx\",\"info\":\"自定义校验方法拦截，不允许访问！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("xx:" + userId, 18, "xxxxx");
    }

    /**
     * 自定义黑名单，拦截方法
     */
    public boolean blacklist(@RequestParam String userId) {
        if ("bbb".equals(userId) || "222".equals(userId)) {
            logger.info("拦截自定义黑名单用户 userId：{}", userId);
            return false;
        }
        return true;
    }

}
