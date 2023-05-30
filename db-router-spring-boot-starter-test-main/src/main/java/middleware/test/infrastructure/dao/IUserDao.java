package middleware.test.infrastructure.dao;

import middleware.db.router.annotation.DBRouter;
import middleware.test.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {

     @DBRouter(key = "userId")
     User queryUserInfoByUserId(User req);

     @DBRouter(key = "userId")
     void insertUser(User req);

}
