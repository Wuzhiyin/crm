package com.itheima.crm.web.action;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 客户拜访记录的Action的类
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    private SaleVisit saleVisit = new SaleVisit();
    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }
    //在Action中注入Service
    @Resource(name = "saleVisitService")
    private SaleVisitService saleVisitService;


    //接收分页数据
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
     * 查询拜访记录列表的方法:findAll
     * @return
     */
    public String findAll(){
        //创建离线条件查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        //设置条件

        //调用业务层
        PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
        return "findAll";
    }

    /**
     * 拜访记录跳转到添加页面的方法;saveUI
     * @return
     */
    public String saveUI(){

        return "saveUI";
    }
}
