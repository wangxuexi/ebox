package com.fijo.ebox.base.service;


import com.fijo.ebox.base.pojo.FijoBasePojo;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface FijoBaseService<T extends FijoBasePojo, PK extends Serializable> {

    List<T> queryAll();

    List<T> queryList(T t);

    T query(T t);

    T queryById(Long id);

    Map<String,Object> queryPage(T t);

    void insert(T t) throws Exception;

    void update(T t) throws Exception;

    void deleteById(String id) throws Exception;

    void logicDeleteById(String id) throws Exception;
}
