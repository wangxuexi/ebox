package com.fijo.ebox.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fijo-efast")//value=注册在eureka上的服务名
public interface EfastClient {

    /**
     * 查询页面注册的所有图表配置
     *
     * @param tenant
     * @param applicationCode
     * @param webPageCode
     * @return
     */
    @PostMapping(value = "/openApi/queryWebChartRegistConfig")
    String queryWebChartRegistConfig(@RequestParam("tenant") String tenant,
                                     @RequestParam("applicationCode") String applicationCode,
                                     @RequestParam("webPageCode") String webPageCode);

}
