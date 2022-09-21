package com.fijo.ebox.base.intercepors;

import com.fijo.ebox.base.constant.RequestHeaderConstant;
import com.fijo.ebox.base.constant.AspectConstant;
import com.fijo.ebox.base.util.plat.AjaxUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.dto.ResultUserDto;
import com.fijo.ebox.base.util.plat.JsonUtil;
import com.fijo.ebox.base.util.plat.SessionUtil;
import com.mysql.jdbc.StringUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 接口拦截器
 */
@Component
@Slf4j
public class InterfaceInterceptor implements HandlerInterceptor {

    @Autowired
    private Environment env;

    @Autowired
    HttpServletRequest request;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final String APPLICATION_CODE_KEY = "Application-Code";

    final static String REDIRECTURL_DEFAULT_KEY = "redirectUrl.default";

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();

        Map map = SessionUtil.getSession(request, AspectConstant.REDIS_KEY_LOGINUSER);
        String loginUserStr = (String) map.get(AspectConstant.REDIS_KEY_LOGINUSER);
        ResultUserDto resultUserDto = JsonUtil.json2Bean(loginUserStr,ResultUserDto.class);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();//拦截获取请求相关信息，记录日志
        HttpServletRequest request =  attributes.getRequest();

        HttpServletResponse response = attributes.getResponse();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI().toString();
        String applicationCode = request.getHeader(APPLICATION_CODE_KEY);//请求的应用名
        String propertyKey = StringUtils.isNullOrEmpty(applicationCode) ? "redirectUrl.default" :  "redirectUrl."+applicationCode;
        String redirectUrl =  env.getProperty(propertyKey);//根据应用编码获取重定向地址
        log.info("【拦截请求】url={}",request.getRequestURL());

        if(StringUtils.isNullOrEmpty(redirectUrl)){
            redirectUrl = env.getProperty(REDIRECTURL_DEFAULT_KEY);
        }
        log.info("重定向地址：{}",redirectUrl);
        String redisPath  = request.getHeader(RequestHeaderConstant.REQUEST_HEADER_REDIS_PATH_NAME);  //获取令牌路径
        String token = request.getHeader(RequestHeaderConstant.REQUEST_HEADER_REDIS_TOKEN_NAME); //获取分发的令牌

        if(!StringUtil.isNullOrEmpty(uri)&&uri.equals(AspectConstant.LOGINURI)){ // 判断是否是登录接口
            log.info("准备登陆验证");
        }else {
            String ctxHeader = request.getHeader("X-Requested-With");
            //校验登陆信息
            //判断是否存在服务通信传递的令牌
            if (StringUtils.isNullOrEmpty(redisPath) && StringUtils.isNullOrEmpty(token)) { //若不存在令牌，则校验登陆人信息

                if (resultUserDto == null || StringUtil.isNullOrEmpty(resultUserDto.getUId() + "")) {
                    log.info("【校验登陆信息】未获取到登录人信息");
                    //如果是Ajax请求
                    if ("XMLHttpRequest".equals(ctxHeader)) {
                        log.debug("ajax redirect");
                        AjaxUtil.sendRedirect(response, redirectUrl);
                        return false;
                    }
                    //如果是浏览器地址栏请求
                    else {
                        log.debug("normal redirect ");
                        response.sendRedirect(redirectUrl);
                        return false;
                    }
                }
            } else {
                String redisToken = stringRedisTemplate.opsForValue().get(redisPath); //根据令牌路径，从redis缓存中获取令牌
                log.info("【获取请求头数据】redisPath:{} ; token:{}", redisPath, token);
                if (StringUtil.isNullOrEmpty(redisToken)) {//校验令牌是否匹配

                    response.reset();
                    //设置编码格式
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    log.info("【无访问权限】无访问令牌或令牌已过期");
                    pw.write(ResultDto.ERROR("【无访问权限】无访问令牌或令牌已过期"));
                    pw.flush();
                    pw.close();
                    return false;
                } else if (redisToken.equals(token)) {//校验请求头中的令牌与实际缓存中的令牌是否相同，如果相同，则直接通过拦截
                    log.info("【令牌校验通过】");
                    return true;
                } else {
                    response.reset();
                    //设置编码格式
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    log.info("【无访问权限】令牌校验失败，令牌不匹配！");
                    pw.write(ResultDto.ERROR("【无访问权限】令牌校验失败，令牌不匹配！"));
                    pw.flush();
                    pw.close();
                    return false;
                }
            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
