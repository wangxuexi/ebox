package com.fijo.ebox.base.util.plat;


import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang.exception.ExceptionUtils;

@Slf4j
public class OkHttpUtil {

    public static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType mediaType = MediaType.parse("application/octet-stream");

    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .readTimeout(8000, TimeUnit.MILLISECONDS)
            .build();



    /**
     * 发送post请求通过Form表单形式
     *
     * @param reqUrl 请求url
     * @param mapParam 请求参数
     *
     */
    private static String sendPostByForm(String reqUrl, Map<String,String> mapParam){
        String result = null;// 返回结果字符串
        try {
            long startTime = System.currentTimeMillis();
            //循环form表单，将表单内容添加到form builder中
            FormBody.Builder formBody = new FormBody.Builder();
            for (Map.Entry<String, String> m : mapParam.entrySet()) {
                String name = m.getKey();
                String value = m.getValue()+"";
                formBody.add(name, value);
            }
            //构建formBody(formBody.build())，将其传入Request请求中
            Request.Builder builder = new Request.Builder().url(reqUrl).post(formBody.build());
            try(Response response = okHttpClient.newCall(builder.build()).execute()){
                result = response.body().string();
                log.info("{} response body:{}", reqUrl.substring(reqUrl.lastIndexOf("/") + 1),
                        result);
//                return result;
            }catch(Exception e){
                log.error("调用接口出错\n"+ ExceptionUtils.getMessage(e));
            }finally{
                long endTime = System.currentTimeMillis();
                log.info("{} cost time:{}", reqUrl.substring(reqUrl.lastIndexOf("/") + 1),
                        (endTime - startTime));
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        return result;
    }

    /**
     * 发送post请求通过JSON参数
     *
     * @param reqUrl 请求url
     * @param param 请求参数
     *
     */
    private static String sendPostByJson(String reqUrl, Object param){
        String result = null;// 返回结果字符串
        try {
            String paramStr = JSON.toJSONString(param);
            RequestBody requestBody = RequestBody.create(jsonType, paramStr);
            long startTime = System.currentTimeMillis();
            Request.Builder builder = new Request.Builder().url(reqUrl).post(requestBody);
            try(Response response = okHttpClient.newCall(builder.build()).execute()){
                result = response.body().string();
            }catch(Exception e){
                log.error("调用接口出错\n"+ ExceptionUtils.getMessage(e));
            }finally{
                long endTime = System.currentTimeMillis();
                log.info("{} cost time:{}", reqUrl.substring(reqUrl.lastIndexOf("/") + 1),
                        (endTime - startTime));
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param reqUrl 请求url
     * @param file 上传的文件
     * @param fileName 文件名称
     *
     */
    public static String uploadFile(String reqUrl, File file, String fileName) {
        String result = null;// 返回结果字符串
        try {
            RequestBody fileBody = RequestBody.create(mediaType, file);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
//                    .addFormDataPart("fileName", fileName)
                    .addFormDataPart("media", fileName, fileBody)
                    .build();

            long startTime = System.currentTimeMillis();
            Request.Builder builder = new Request.Builder().url(reqUrl).post(requestBody);

            try(Response response = okHttpClient.newCall(builder.build()).execute()){
                result = response.body().string();
            }catch(Exception e){
                log.error("调用接口出错\n"+ ExceptionUtils.getMessage(e));
            }finally{
                long endTime = System.currentTimeMillis();
                log.info("{} cost time:{}", reqUrl.substring(reqUrl.lastIndexOf("/") + 1),
                        (endTime - startTime));
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        return result;
    }

    public static void main(String[] args) {
        String accessToken = "55_Y4KLVNlynUlnH2RB_tDAuhhThS_6DnGEc-p66URXC-fzQl608QjiAPWz36gM0HHDW5qEuKiP0NSLyTI3lpORY6gy1EbJyOP-x33KZcszm54Aht6mSLwE-Tst4KBl7uOLnG_2JSpY1_mloY-8ONCgAFAJIX";
        String reqUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken;
        //
        File file = new File("C:\\Users\\zhangb\\Desktop\\临时文件\\浦锦街道二维码.png");
        String result = uploadFile(reqUrl,file,file.getName());
        System.out.println(result);
    }
}
