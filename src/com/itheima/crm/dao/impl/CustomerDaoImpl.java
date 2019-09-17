package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 客户管理的DAO的实现类
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {



}
