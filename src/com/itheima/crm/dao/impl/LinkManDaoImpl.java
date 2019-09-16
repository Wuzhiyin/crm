package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 联系人DAO的实现类
 */
public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {
    /**
     * DAO中统计个数的方法
     * @param detachedCriteria
     * @return
     */
    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List <Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * DAO的分页查询
     * @param detachedCriteria
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        List <LinkMan> list = (List <LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
        return list;
    }
}
