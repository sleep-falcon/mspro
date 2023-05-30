package middleware.test.infrastructure.dao;

import middleware.test.infrastructure.po.User;

public interface IUserDao {

     User queryUserInfoById(Long id);

}
