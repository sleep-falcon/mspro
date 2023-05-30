package middleware.test.interfaces;

import middleware.test.infrastructure.dao.IUserDao;
import middleware.test.infrastructure.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

//    @Resource
    private IUserDao userDao;

    @RequestMapping(path = "/api/queryUserInfoById", method = RequestMethod.GET)
    public User queryUserInfoById(){
        return userDao.queryUserInfoById(1L);
    }

}
