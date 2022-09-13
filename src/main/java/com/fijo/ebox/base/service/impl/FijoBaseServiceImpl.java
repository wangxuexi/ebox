package com.fijo.ebox.base.service.impl;

import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.dto.ResultUserDto;
import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.PlatUtil;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FijoBaseServiceImpl<T extends FijoBasePojo, PK extends Serializable> implements FijoBaseService<T, PK> {
    @Autowired
    private HttpServletRequest request;

    private FijoBaseMapper<T, PK> mapper;

    private static final String PAGE_TOTAL = "total";

    private static final String PAGE_RESULT = "result";

    public FijoBaseServiceImpl() {
    }

    public FijoBaseServiceImpl(FijoBaseMapper<T, PK> fijoBaseMapper) {
        this.mapper = fijoBaseMapper;
    }

    @Override
    public List<T> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public List<T> queryList(T t) {
        return mapper.queryList(t);
    }


    @Override
    public T query(T t) {
        return mapper.query(t);
    }

    @Override
    public T queryById(Long id) {
        return mapper.queryById(id);
    }

    @Override
    public Map<String, Object> queryPage(T t) {
        List<T> resultList =  mapper.queryList(t);
        Map<String, Object> dataMap = wrapQueryResult(resultList);
        return dataMap;
    }

    @Override
    public void insert(T t) throws Exception {
        ResultUserDto loginUser = PlatUtil.getLoginUser(request);
        loginUser = loginUser == null ? new ResultUserDto() : loginUser;
        Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
        String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
        String operateTime = DateUtils.getNowDateYMDHMS();
        t.setEnabled(true);
        t.setRemoved(false);
        t.setCreateUserId(userId);
        t.setCreateUserName(userName);
        t.setCreateTime(operateTime);

        t.setUpdateUserId(userId);
        t.setUpdateUserName(userName);
        t.setUpdateTime(operateTime);

        mapper.insert(t);
    }

    @Override
    public void update(T t) throws Exception {
        ResultUserDto loginUser = PlatUtil.getLoginUser(request);
        loginUser = loginUser == null ? new ResultUserDto() : loginUser;
        Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
        String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
        String operateTime = DateUtils.getNowDateYMDHMS();

        t.setUpdateUserId(userId);
        t.setUpdateUserName(userName);
        t.setUpdateTime(operateTime);

        mapper.update(t);

    }

    @Override
    @Transactional
    public void deleteById(String id) throws Exception {
        String[] ids = id.split(",");
        for (String _id : ids) {
            mapper.deleteById(_id);
        }
    }

    @Override
    @Transactional
    public void logicDeleteById(String id) throws Exception {
        id = StringUtil.str2SqlStr(id);
        ResultUserDto loginUser = PlatUtil.getLoginUser(request);
        loginUser = loginUser == null ? new ResultUserDto() : loginUser;
        Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
        String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
        String operateTime = DateUtils.getNowDateYMDHMS();
        mapper.logicDeleteById(id,false,true,userId,userName,operateTime);
    }

    protected static Map<String, Object> wrapQueryResult(List<?> list) {
        Map<String, Object> dataMap = new HashMap<>();
        PageInfo info = new PageInfo(list);
        if (info.getList() == null) {
            dataMap.put(PAGE_TOTAL, list.size());
            dataMap.put(PAGE_RESULT, list);
        } else {
            dataMap.put(PAGE_TOTAL, info.getTotal());
            dataMap.put(PAGE_RESULT, info.getList());
        }
        return dataMap;
    }
}
