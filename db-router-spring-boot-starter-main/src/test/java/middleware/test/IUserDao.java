package middleware.test;

import middleware.db.router.annotation.DBRouter;

public interface IUserDao {

    @DBRouter(key = "userId")
    void insertUser(String req);

}
