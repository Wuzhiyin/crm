package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }

	//注入Service:
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册的方法:regist
     * @return
     */
    public String regist(){
        userService.regist(user);
        return LOGIN;
    }

    /**
     * 用户登录的方法:login
     * @return
     */
    public String login(){
        User existUser = userService.login(user);
        if (existUser == null){
            this.addActionError("用户名或密码错误");
            return LOGIN;
        }else{
            ActionContext.getContext().getSession().put("existUser",existUser);
            return SUCCESS;
        }
    }
    public String findAllUser() throws IOException {
        List<User> list = userService.findAll();
        JSONArray jsonArray = JSONArray.fromObject(list);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }
}
