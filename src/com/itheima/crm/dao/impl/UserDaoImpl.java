package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    //DAO中保存用户的方法
    @Override
    public void save(User user) {
        System.out.println("user" + user);
        this.getHibernateTemplate().save(user);
    }

    /**
     * DAO中根据用户名和密码进行查询的方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(), user.getUser_password());
        //判断一下
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
