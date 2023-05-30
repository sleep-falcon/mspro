package middleware.test.interfaces;

import middleware.test.infrastructure.dao.IUserDao;
import middleware.test.infrastructure.po.User;
import middleware.test.interfaces.dto.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserDao userDao;

    @RequestMapping(path = "/api/queryUserInfoById", method = RequestMethod.GET)
    public User queryUserInfoById(){
        return userDao.queryUserInfoByUserId(new User("980765512"));
    }

}
