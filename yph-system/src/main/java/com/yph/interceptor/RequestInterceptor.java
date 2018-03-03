package com.yph.interceptor;

import com.yph.common.exception.LoginOverTimeException;
import com.yph.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 主要是请求日志拦截
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/23
 **/
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private Long startDate = null;

    private String requestURI = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        startDate = System.currentTimeMillis();
        requestURI = request.getRequestURI();
        int remotePort = request.getRemotePort();
        String remoteAddr = request.getRemoteAddr();

//        请求参数
        if (ShiroUtils.getUser() == null) {
            if (filterRequest(requestURI)) {
                checkUserLogin(request, response);
                return false;
            }
        }

        String queryString = request.getQueryString();
        log.info("【请求地址】--------> {}", remoteAddr + ":" + remotePort + requestURI);
        if ("GET".equals(request.getMethod())) {
            log.info("【请求参数】--------> {}", queryString);
        }
        return true;
    }


    /**
     * 用户为空时
     */
    private void checkUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String header = request.getHeader("x-requested-with");
        // 判断是否异步请求
        if ("XMLHttpRequest".equals(header)) {
            // ajax 请求
            throw new LoginOverTimeException(" 登录超时 ! 请重新登录.");
        } else {
            // 页面请求
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            String loginHtm = getLoginHtm(request);
            writer.write(loginHtm);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        super.afterCompletion(request, response, handler, e);
        log.info("[  执行 {} 方法耗时  : ]  ---------------->  {} 毫秒", requestURI, System.currentTimeMillis() - startDate);
    }


    /**
     * 获取重新登录 弹窗
     *
     * @return
     */
    private String getLoginHtm(HttpServletRequest request) throws UnsupportedEncodingException {

        String contextPath = request.getContextPath();

        String htm = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <link rel=\"stylesheet\" href=\"" + contextPath + "/js/plugins/layui/css/layui.css\" media=\"all\">" +
                "\t<script src=\"" + contextPath + "/js/plugins/layui/layui.js\"></script>\n" +
                " </head>\n" +
                " <body>\n" +
                " <script type=\"text/javascript\">\n" +
                "\tlayui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {\n" +
                "\t\tvar openWindow = function(){\n" +
                "\t\t\tlayer.open({\n" +
                "\t\t        type: 1\n" +
                "\t\t        ,title: false //不显示标题栏\n" +
                "\t\t        ,closeBtn: false\n" +
                "\t\t        ,area: '300px;'\n" +
                "\t\t        ,shade: 0.8\n" +
                "\t\t        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出\n" +
                "\t\t        ,btn: ['跳转到登录页面']\n" +
                "\t\t        ,btnAlign: 'c'\n" +
                "\t\t        ,moveType: 1 //拖拽模式，0或者1\n" +
                "\t\t        ,content: '<div style=\"padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;\">亲 ! 你太久没有理会我这页面了,请重新登录一下喔!</div>'\n" +
                "\t\t        ,yes: function(index, layero){\n" +
                "window.location.href='"+contextPath+"/index.do';" +
                "\t\t        }\n" +
                "\t\t      });\n" +
                "\t\t}\n" +
                "\t\topenWindow();\n" +
                "\t});\n" +
                " </script>\n" +
                " </body>\n" +
                " </html>\n";
        return htm;
    }

    /**
     * 过滤请求
     *
     * @param requestURI
     * @return
     */
    private Boolean filterRequest(String requestURI) {

        String[] filterHtm = {"index.do", "login.do", "captcha.do", "toLogin.do","toForgetPassword.do","sendForgetPassWordMail.do"};

        if (!StringUtils.isBlank(requestURI)) {
            for (int i = 0; i < filterHtm.length; i++) {
                if (requestURI.endsWith(filterHtm[i])) {
                    return false;
                }
            }
        }
        return true;
    }


}
