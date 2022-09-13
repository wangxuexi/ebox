package com.fijo.ebox.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fijo-esearch")
public interface EsearchClient {
    /**
     * 不指定字段搜索
     *
     * @param tenant    租户编码
     * @param tableName 表名，表名带下划线
     * @param secretKey 秘钥key值
     * @param keyWord   需要查询的关键字，可模糊搜索，或者空格间隔
     * @return
     */
    @PostMapping(value = "/openApi/withNoFieldSearch")
    String withNoFieldSearch(@RequestParam("tenant") String tenant,
                             @RequestParam("tableName") String tableName,
                             @RequestParam("secretKey") String secretKey,
                             @RequestParam("keyWord") String keyWord);

    /**
     * 指定字段搜索
     *
     * @param tenant    租户编码
     * @param tableName 表名
     * @param secretKey 秘钥key
     * @param keyWord   关键字
     * @param fields    需要指定的字段，字段为数据库字段,不区分大小写，需与数据库字段名保持一致
     * @return
     */
    @PostMapping(value = "/openApi/withFieldSearch")
    String withFieldSearch(@RequestParam("tenant") String tenant,
                           @RequestParam("tableName") String tableName,
                           @RequestParam("secretKey") String secretKey,
                           @RequestParam("keyWord") String keyWord,
                           @RequestParam("fields") String fields);

    /**
     * 自定义搜索
     *
     * @param tenant    租户编码
     * @param tableName 表名
     * @param secretKey 秘钥key值
     * @param reqJson   自定义查询语句的json串字符
     * @return
     */
    @PostMapping(value = "/openApi/customSearch")
    String customSearch(@RequestParam("tenant") String tenant,
                        @RequestParam("tableName") String tableName,
                        @RequestParam("secretKey") String secretKey,
                        @RequestParam("reqJson") String reqJson);

    /**
     * 不指定字段搜索返回分数
     *
     * @param tenant    租户编码
     * @param tableName 表名，表名带下划线
     * @param secretKey 秘钥key值
     * @param keyWord   需要查询的关键字，可模糊搜索，或者空格间隔
     * @return
     */
    @PostMapping(value = "/openApi/withNoFieldSearchWithScore")
    String withNoFieldSearchWithScore(@RequestParam("tenant") String tenant,
                                      @RequestParam("tableName") String tableName,
                                      @RequestParam("secretKey") String secretKey,
                                      @RequestParam("keyWord") String keyWord);

    /**
     * 不指定字段,多表查询
     *
     * @param tenant    租户编码
     * @param tableName 表名，表名带下划线
     * @param secretKey 秘钥key值
     * @param keyWord   需要查询的关键字，可模糊搜索，或者空格间隔
     * @param from      页数起始值
     * @param size      页长
     * @return
     */
    @PostMapping(value = "/openApi/withNoFieldBatchSearchWithScore")
    String withNoFieldBatchSearchWithScore(@RequestParam("tenant") String tenant,
                                           @RequestParam("tableName") String tableName,
                                           @RequestParam("secretKey") String secretKey,
                                           @RequestParam("keyWord") String keyWord,
                                           @RequestParam("from") Integer from,
                                           @RequestParam("size") Integer size);

}
