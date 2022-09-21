package com.fijo.ebox.base.mapper;

import java.io.Serializable;
import java.util.List;

public interface FijoBaseMapper<T ,PK extends Serializable> {
    List<T> queryAll();

    List<T> queryList(T t);

    T query(T t);

    T queryById(Long id);

    void insert(T t) throws Exception;

    void update(T t) throws Exception;

    void deleteById(String id) throws Exception;

    void logicDeleteById(String id,Boolean enabled , Boolean removed,Long updateUserId,String updateUserName,String updateTime) throws Exception;
}
