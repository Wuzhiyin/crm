package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * 联系人的Action的类
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }
    //注入Service
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
    //注入客户管理的Service
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //分页参数
    private Integer currPage = 1;
    private Integer pageSize = 3;

    public void setCurrPage(Integer currPage) {
        if (currPage == null) {
            currPage = 1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }

    /**
     * 查询联系人列表的Action
     * @return
     */
    public String findAll(){
        //创建离线条件查询:
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        //设置条件
        if (linkMan.getLkm_name()!=null){
            //设置名称查询的条件
            detachedCriteria.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));

        }
        //设置性别条件
        if (linkMan.getLkm_gender()!=null && !"".equals(linkMan.getLkm_gender())){
            //设置按名称查询的条件
            detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
        }
        //调用业务层
        PageBean<LinkMan> pageBean =  linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    /**
     * 跳转到添加页面的方法:saveUI
     * @return
     */
    public String saveUI(){
        //查询所有客户
        List<Customer> list = customerService.findAll();
        //将list集合保存值栈中
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }

    /**
     * 保存联系人的方法:save
     * @return
     */
    public String save(){
        //调用业务层保存联系人
        linkManService.save(linkMan);
        return "saveSuccess";
    }
    public String edit(){
        //查询某个联系人,查询所有客户
        //查询所有客户:
        List <Customer> list = customerService.findAll();
        //根据id查询联系人
        linkMan = linkManService.findById(linkMan.getLkm_id());
        //将list和linkMan的对象带到页面上
        ActionContext.getContext().getValueStack().set("list",list);
        System.out.println(list);
        //将对象的值存入到值栈
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }

    /**
     * 修改联系人的方法:update
     * @return
     */
    public String update(){
        //调用业务层
        linkManService.update(linkMan);
        return "updateSuccess";
    }

    /**
     * 删除联系人的方法:删除
     * @return
     */
    public String delete(){
        //调用业务层
        linkMan = linkManService.findById(linkMan.getLkm_id());
        //删除联系人
        linkManService.delete(linkMan);
        //页面跳转
        return "deleteSuccess";
    }
}
