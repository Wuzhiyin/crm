package com.itheima.crm.dao;

import com.itheima.crm.domain.User;

public interface UserDao extends BaseDao<User>{


    User login(User user);
}
