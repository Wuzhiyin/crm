package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?" ,dict_type_code);
    }
}
