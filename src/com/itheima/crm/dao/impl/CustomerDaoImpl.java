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
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    /**
     * DAO中保存客户的方法
     * @param customer
     */
    @Override
    public void save(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    /**
     * DAO中带条件统计个数的方法
     * @param detachedCriteria
     * @return
     */
    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * DAO中扉页查询客户的方法
     * @param detachedCriteria
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }

    /**
     * DAO中根据ID查询客户的方法
     * @param cust_id
     * @return
     */
    @Override
    public Customer findById(Long cust_id) {
        return this.getHibernateTemplate().get(Customer.class,cust_id);
    }

    /**
     * DAO中删除客户的方法
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        this.getHibernateTemplate().delete(customer);
    }

    /**
     * DAO中修改客户的方法
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }

    /**
     * DAO中查询所有客户的方法
     * @return
     */
    @Override
    public List <Customer> findAll() {
        return (List <Customer>) this.getHibernateTemplate().find("from Customer");
    }
}
