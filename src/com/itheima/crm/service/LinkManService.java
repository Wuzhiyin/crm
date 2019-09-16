package com.itheima.crm.service;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 联系人Service的接口
 */
public interface LinkManService {
    PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
}
