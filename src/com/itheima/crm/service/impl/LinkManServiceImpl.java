package com.itheima.crm.service.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 联系人Service的实现类
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {

        this.linkManDao = linkManDao;
    }

    /**
     * 业务层分页查询联系人的方法
     * @param detachedCriteria
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<LinkMan> pageBean = new PageBean <>();
        //设置当前页数
        pageBean.setCurrPage(currPage);
        //设置每页显示记录数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        Integer totalCount = linkManDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 每页显示数据的集合
        Integer begin = (currPage - 1)*pageSize;
        List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 业务层保存联系人的方法
     * @param linkMan
     */
    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    /**
     * 业务层根据ID查询联系人的方法
     * @param lkm_id
     * @return
     */
    @Override
    public LinkMan findById(Long lkm_id) {
        return linkManDao.findById(lkm_id);
    }
}
