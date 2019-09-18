package com.itheima.crm.service;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 客户拜访记录的业务层的接口
 */
public interface SaleVisitService  {
    PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
}
