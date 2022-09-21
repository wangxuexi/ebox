package com.fijo.ebox.util;

import com.alibaba.fastjson.JSON;
import com.fijo.ebox.base.client.MessageClient;
import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.base.util.plat.HttpClientUtil;
import com.fijo.ebox.base.util.plat.JsonUtil;
import com.fijo.ebox.config.WxapiConfig;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.enums.ModularEnum.NtFileTypeEnum;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wxApi")
@Slf4j
public class WxApiUtil {

    @Autowired
    private WxapiConfig wxapiConfig;

    @Autowired
    private MessageClient messageClient;

    /**
     * 获取accessToken
     * @param appId
     * @param secret
     * @return
     */
    public String getAccessToken(String appId, String secret){
        try {
            //处理请求路径
            String url = wxapiConfig.getGetAccessToken() + "&appid=" + appId + "&secret=" + secret;
            String restleStr = HttpClientUtil.doGet(url);
            return ResultDto.SUCCESS("获取成功！",restleStr);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("获取失败！",e.toString());
        }
    }

    /**
     * 上传附件素材
     * @param accessToken
     * @param multipartFile
     * @return
     */
    public String getInsertImg(String accessToken, MultipartFile multipartFile, Integer type){
        try {
            //处理请求路径
            String url = wxapiConfig.getInsertImg() + "?access_token=" + accessToken + "&type=" + NtFileTypeEnum.getEnumMsgByCode(type);
            //调用方法
            String restleStr =  HttpClientUtil.doPostWithHeaderByFormData(url,multipartFile,multipartFile.getOriginalFilename());
            return ResultDto.SUCCESS("上传成功！",restleStr);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("上传失败！",e.toString());
        }
    }


    /**
     * 图文消息内容图片上传
     * @param accessToken
     * @param multipartFile
     * @return
     */
    public String imgFileUpload(String accessToken, MultipartFile multipartFile, Integer type){
        try {
            //处理请求路径
            String url = wxapiConfig.getImgFileUpload() + "?access_token=" + accessToken + "&type=" + NtFileTypeEnum.getEnumMsgByCode(type);
            //调用方法
            String restleStr =  HttpClientUtil.doPostWithHeaderByFormData(url,multipartFile,multipartFile.getOriginalFilename());
            return ResultDto.SUCCESS("上传成功！",restleStr);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("上传失败！",e.toString());
        }
    }


    /**
     * 新增草稿
     * @param accessToken
     * @param paramsMap
     * @return
     */
    public String insertDraft(String accessToken, Map paramsMap){
        try {
            //处理请求路径
            String url = wxapiConfig.getInsertDraft() + "?access_token=" + accessToken;
            //将数据处理为JSON
            String pramsStr = JSON.toJSONString(paramsMap);
            //调用方法
            String restleStr = HttpClientUtil.doPostWithHeaderByJson(url,pramsStr);
            return ResultDto.SUCCESS("新增草稿成功！",restleStr);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("新增草稿失败！",e.toString());
        }
    }

    /**
     * 消息推送预览接口
     * @param accessToken
     * @param paramsMap
     * @return
     */
    public String sendMsgPreview(String accessToken, Map paramsMap){
        try {
            //处理请求路径
            String url = wxapiConfig.getSendMsgPreview() + "?access_token=" + accessToken;
            //将数据处理为JSON
            String pramsStr = JSON.toJSONString(paramsMap);
            //调用方法
            String restleStr = HttpClientUtil.doPostWithHeaderByJson(url,pramsStr);
            return restleStr;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("消息推送失败！",e.toString());
        }
    }



    /**
     * 消息推送预览接口
     * @param accessToken
     * @return
     */
    public String checkIsSubscribe(String accessToken, String openId){
        try {
            //处理请求路径
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN" + "?access_token=" + accessToken;
            //将数据处理为JSON
            //调用方法
            String restleStr = HttpClientUtil.doGet(url);
            return restleStr;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("消息推送失败！",e.toString());
        }
    }

    /**
     * 消息推送
     * @param accessToken
     * @param paramsMap
     * @return
     */
    public String sendMsg(String accessToken, Map paramsMap){
        try {
            //处理请求路径
            String url = wxapiConfig.getSendMsg() + "?access_token=" + accessToken;
            //将数据处理为JSON
            String pramsStr = JSON.toJSONString(paramsMap);
            //调用方法
            String restleStr = HttpClientUtil.doPostWithHeaderByJson(url,pramsStr);
            return restleStr;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("消息推送失败！",e.toString());
        }
    }

    /**
     * 获取消息发送状态
     * @param accessToken
     * @param paramsMap
     * @return
     */
    public String getMstSendState(String accessToken, Map paramsMap){
        try {
            //处理请求路径
            String url = wxapiConfig.getGetMstSendState() + "?access_token=" + accessToken;
            //将数据处理为JSON
            String pramsStr = JSON.toJSONString(paramsMap);
            //调用方法
            String restleStr = HttpClientUtil.doPostWithHeaderByJson(url,pramsStr);
            return ResultDto.SUCCESS("查询消息发送状态！",restleStr);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("查询消息发送状态！",e.toString());
        }
    }


    /**
     * 根据路径获取文件对象
     * @param path
     * @return
     * @throws IOException
     */
    public File obtainFileByPath(String path) throws IOException {
        //对本地文件命名，path是http的完整路径，主要得到资源的名字
        String newUrl = path;
        newUrl = newUrl.split("[?]")[0];
        String[] bb = newUrl.split("/");
        //得到最后一个分隔符后的名字
        String fileName = bb[bb.length - 1];
        //保存到本地的路径
        String filePath="officefile//"+fileName;
        File file = null;
        URL urlfile;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            //判断文件的父级目录是否存在，不存在则创建
            file = new File(filePath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try{
                //创建文件
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            //下载
            urlfile = new URL(newUrl);
            inputStream = urlfile.openStream();
            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead=inputStream.read(buffer,0,8192))!=-1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    /**
     * 根据业务发送微信图文消息
     * @param appId             appId
     * @param secret            密钥
     * @param ntImgUrl          封面图 （如果草稿参数中有微信缩略图MID则不传）
     * @param articlesParamsMap       微信新增草稿参数
     * @param perDataList       人员数据
     * @param temDatailsUrl     模板消息详情路径
     * @param teamplateValue    模板消息内容
     * @param tenant            租户
     * @param applicationCode   应用编码
     * @param templateCode      模板消息ID
     * @return
     */
    public String sentWxTintMsg(String appId, String secret, Map articlesParamsMap, String ntImgUrl ,List<Map> perDataList,
                            String temDatailsUrl, String teamplateValue,String tenant,String applicationCode,String templateCode) {
        try {
            //1.获取accessToken
            String restleStr1 = getAccessToken(appId, secret);
            ResultDto restleDto1 = JsonUtil.json2Bean(restleStr1, ResultDto.class);
            Map restleMap1 = JsonUtil.json2Bean(restleDto1.getData().toString(), Map.class);
            String accessToken = restleMap1.get("access_token").toString();
            //2.调用新增草稿的接口
            if(!StringUtil.isNull(ntImgUrl)){
                //校验是否需要上传封面图
                //拿到封面图路径
                File file = obtainFileByPath(ntImgUrl);
                InputStream inputStream = new FileInputStream(file);
                MultipartFile multipartFile =
                        new MockMultipartFile(file.getName(), file.getName(), null, inputStream);
                inputStream.close();
                //调用接口上传
                String restleStr2 = getInsertImg(accessToken, multipartFile, NtFileTypeEnum.NT_FILE_TYPE_IMG.getCode());
                ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
                Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
                //存储数据
                articlesParamsMap.put("thumb_media_id",restleMap2.get("media_id").toString());
            }
            Map articlesMap = new HashMap();
            List<Map> articlesList = new ArrayList<>();
            articlesList.add(articlesParamsMap);
            articlesMap.put("articles", articlesList);
            String restleStr2 = insertDraft(accessToken, articlesMap);
            ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
            Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
            //拿到草稿的ID
            String draftMediaId = restleMap2.get("media_id").toString();
            //3.构建发送图文消息的参数
            Map paramsMap1 = new HashMap();
            //OPENID
            List<String> openIdList = new ArrayList<>();
            //遍历人员数据拿到人员的openID
            for (Map preData : perDataList) {
                if (preData.get("openId") != null) {
                    String openId = preData.get("openId").toString();
                    openIdList.add(openId);
                }
            }
            //根据长度将数组分组
            int num = 10000;    //微信每次图文消息openID数量最多一万，最少两个
            int count = openIdList.size() % num == 0 ? openIdList.size() / num : openIdList.size() / num + 1;
            List<List<String>> arrayList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                int index = i * num;
                List<String> list = new ArrayList<>();
                int j = 0;
                while (j < num && index < openIdList.size()) {
                    list.add(openIdList.get(index++));
                    j++;
                }
                arrayList.add(list);
            }
            //遍历数组
            for (List<String> list : arrayList) {
                //openId去重
                List<String> collectList = list.stream().distinct().collect(Collectors.toList());
                //校验open数量
                if (collectList.size() > 1) {    //发送图文消息
                    paramsMap1.put("touser", list);
                    //草稿ID
                    Map mpnewsMap = new HashMap();
                    mpnewsMap.put("media_id", draftMediaId);
                    paramsMap1.put("mpnews", mpnewsMap);
                    //消息类型
                    paramsMap1.put("msgtype", "mpnews");
                    //是否为转载
                    paramsMap1.put("send_ignore_reprint", 1);
                    //调用接口-预览
                    //String restleStr3 = sendMsgPreview(accessToken, paramsMap1);
                    //调用接口-群发
                    String restleStr3 = sendMsg(accessToken, paramsMap1);
                    JsonObject restleMap3 = JsonUtil.str2JsonObj(restleStr3);
                } else if (list.size() == 1) {     //如果只有一个人则发送模板消息
                    //发送模板消息
                    list.stream().forEach(data -> {
                        //调用发送模板接口
                        messageClient.sendWeChatMsg(
                                tenant,
                                applicationCode,
                                templateCode,
                                data,
                                "",
                                teamplateValue,
                                temDatailsUrl);
                    });
                }
            }
            log.info("【发送消息】 发送成功!");
            return ResultDto.SUCCESS("发送成功！");
        } catch (Exception e) {
            log.error("【发送消息】 发送失败，失败原因：{}", e.toString());
            e.printStackTrace();
            return ResultDto.ERROR("【发送消息】发送失败！", e.toString());
        }
    }


    /**
     * 创建草稿
     * @param appId
     * @param secret
     * @param articlesParamsMap
     * @param ntImgUrl
     * @return
     */
    public String generateDraft(String appId, String secret, Map articlesParamsMap, String ntImgUrl){
        try {
            //1.获取accessToken
            String restleStr1 = getAccessToken(appId, secret);
            ResultDto restleDto1 = JsonUtil.json2Bean(restleStr1, ResultDto.class);
            Map restleMap1 = JsonUtil.json2Bean(restleDto1.getData().toString(), Map.class);
            String accessToken = restleMap1.get("access_token").toString();
            //2.调用新增草稿的接口
            if(!StringUtil.isNull(ntImgUrl)){
                //校验是否需要上传封面图
                //拿到封面图路径
                File file = obtainFileByPath(ntImgUrl);
                InputStream inputStream = new FileInputStream(file);
                MultipartFile multipartFile =
                        new MockMultipartFile(file.getName(), file.getName(), null, inputStream);
                inputStream.close();
                //调用接口上传
                String restleStr2 = getInsertImg(accessToken, multipartFile, NtFileTypeEnum.NT_FILE_TYPE_IMG.getCode());
                ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
                Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
                //存储数据
                articlesParamsMap.put("thumb_media_id",restleMap2.get("media_id").toString());
            }
            Map articlesMap = new HashMap();
            List<Map> articlesList = new ArrayList<>();
            articlesList.add(articlesParamsMap);
            articlesMap.put("articles", articlesList);
            String restleStr2 = insertDraft(accessToken, articlesMap);
            ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
            Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
            //拿到草稿的ID
            String draftMediaId = restleMap2.get("media_id").toString();
            log.info("【创建草稿】 创建草稿成功!");
            return draftMediaId;
        } catch (Exception e) {
            log.error("【创建草稿】 创建草稿失败，失败原因：{}", e.toString());
            e.printStackTrace();
            return ResultDto.ERROR("【创建草稿】创建草稿失败！", e.toString());
        }
    }




}
