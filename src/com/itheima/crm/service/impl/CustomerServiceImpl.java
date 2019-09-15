package com.itheima.crm.service.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {
    //注入客户的Dao
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 业务层保存客户的方法
     * @param customer
     */
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示记录数
        pageBean.setPageSize(pageSize);
        //封装总记录数
        //调用DAO
        Integer totalCount = customerDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        //封装每页显示数据的集合
        Integer begin = (currPage -1)*pageSize;
        List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据ID查询客户的方法
     * @param cust_id
     * @return
     */
    @Override
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    /**
     * 业务层删除客户的方法
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    /**
     * 业务层修改客户的方法
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
