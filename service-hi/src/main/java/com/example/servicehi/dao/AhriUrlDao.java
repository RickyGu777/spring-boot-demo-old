package com.example.servicehi.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AhriUrlDao<T> {
    /**
     * 根据UUID逻辑删除
     *
     * @param t
     * @return
     * @throws DataAccessException
     */
    Integer delete(T t) throws DataAccessException;

    /**
     * 根据UUID物理删除
     *
     * @param t
     * @return
     * @throws DataAccessException
     */
    Integer realDelete(T t) throws DataAccessException;

    /**
     * 根据UUID更新数据
     *
     * @param t
     * @return
     * @throws DataAccessException
     */
    Integer update(T t) throws DataAccessException;

    /**
     * 新增数据
     *
     * @param t
     * @return
     * @throws DataAccessException
     */
    void insert(T t) throws DataAccessException;

    /**
     * 根据UUID搜索，包含逻辑删除的数据
     *
     * @param t
     * @return
     * @throws DataAccessException
     */
    T selectByUUID(T t) throws DataAccessException;

    /**
     * 根据UUID搜索，不包含逻辑删除的数据
     *
     * @param t
     * @return
     * @throws DataAccessException
     */
    T selectByUUIDNotDel(T t) throws DataAccessException;

    /**
     * 搜索所有数据，包含逻辑删除的数据
     *
     * @return
     * @throws DataAccessException
     */
    List<T> selectAll() throws DataAccessException;

    /**
     * 搜索所有数据，不包含逻辑删除的数据
     *
     * @return
     * @throws DataAccessException
     */
    List<T> selectAllNotDel() throws DataAccessException;

    /**
     * 根据URL模糊搜索URL
     *
     * @param t
     * @return
     */
    List<T> selectURL(T t) throws DataAccessException;

    /**
     * 查询今天新增的url
     *
     * @return
     * @throws DataAccessException
     */
    List<T> selectTodayURL() throws DataAccessException;
}