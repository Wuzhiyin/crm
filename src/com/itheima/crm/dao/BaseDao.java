package com.itheima.crm.dao;

/**
 * 通用DAO接口
 * @param <T>
 */
public interface BaseDao<T> {
    public void save(T t);
    public void update(T t);
    public void delete(T t);
}
