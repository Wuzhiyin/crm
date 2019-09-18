package com.itheima.crm.service.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {
    // 注入DAO
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override

    //业务层注册用户的方法
    public void regist(User user) {
        // 对密码进行加密处理
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        user.setUser_state("1");
        // 调用DAO
        userDao.save(user);
    }

    /**
     * 业务层用户登录的方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        return userDao.login(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
