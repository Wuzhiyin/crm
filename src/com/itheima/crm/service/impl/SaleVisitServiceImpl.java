package com.itheima.crm.service.impl;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户拜访记录的业务层的实现类
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
    @Resource(name = "saleVisitDao")
    private SaleVisitDao saleVisitDao;

    /**
     * 业务层分页显示拜访记录的方法
     * @param detachedCriteria
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean <>();
        //设置当前页数:
        pageBean.setCurrPage(currPage);
        //设置每页显示记录数
        pageBean.setPageSize(pageSize);
        //查询总记录数
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        //设置总记录数
        pageBean.setTotalCount(totalCount);
        //计算总页数
        double tc = totalCount;
        Double totalPage = Math.ceil(tc / pageSize);
        //设置总页数
        pageBean.setTotalPage(totalPage.intValue());

        //设置每页显示的数据的集合
        Integer begin = (currPage - 1)*pageSize;
        List <SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        //存入到值栈
        ActionContext.getContext().getValueStack().push(pageBean);
        return pageBean;
    }


    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
