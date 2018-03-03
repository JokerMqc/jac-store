package com.yph.controller.index;

import com.google.code.kaptcha.Constants;
import com.yph.common.annotation.SysLog;
import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.SysUserRole;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.service.mail.ISysMailService;
import com.yph.service.sys.ISysMenuService;
import com.yph.service.sys.ISysUserRoleService;
import com.yph.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Administrator Hzhan
 * @create ：2017/12/23
 **/
@Api(description = "首页跳转控制器")
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysMailService sysMailService;
    /**
     * 首页根目录
     *
     * @return
     */
    @RequestMapping("/index")
    public String begin(HttpServletRequest request) {
        if (ShiroUtils.getUser() != null) {
            log.info("[校验登录]  已登录! ");
            String referer = request.getHeader("referer");
            if (referer == null) {
                return "begin";
            } else if (referer.endsWith("main.do")) {
                return "error/error404";
            }
        }
        return "user/login";
    }


    /**
     * 首页
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "跳转到首页", notes = "首页跳转")
    @SysLog("登陆日志")
    @RequestMapping("main")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SysUser sysUser  = ShiroUtils.getUser();
        SysUserRole sysUserRole = sysUserRoleService.findSysUserRoleByUserId(sysUser.getId());
        if (sysUserRole != null) {
            List<SysMenuVo> sysMenuList = sysMenuService.findSysMenuListByRole(sysUserRole.getRoleId());

            if (sysMenuList.size() > 8) {
                List<SysMenuVo> showList = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    showList.add(sysMenuList.get(i));
                }
                SysMenuVo sysMenuVo = sysMenuList.get(9);
                List<Object> hidenList = new ArrayList<>();
                for (int i = 10; i < sysMenuList.size(); i++) {
                    hidenList.add(sysMenuList.get(i));
                }
                session.setAttribute("showList", showList);
                session.setAttribute("sysMenuVo", sysMenuVo);
                session.setAttribute("hidenList", hidenList);
                session.setAttribute("isMenuSplit", false);
            } else {
                session.setAttribute("showList", null);
                session.setAttribute("sysMenuVo", null);
                session.setAttribute("hidenList", null);
                session.setAttribute("isMenuSplit", true);
                session.setAttribute("menuList", sysMenuList);
            }
        }
        session.setAttribute("user", sysUser);
        return "begin";
    }


    /**
     * 跳到登录页面
     *
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request) {
        return "user/login";
    }

    /**
     *  跳转到忘记页面
     * @return
     */
    @RequestMapping("toForgetPassword")
    public String toForgetPassword(){
        return "user/forget_password";
    }


    /**
     *   找回密码 邮件发送页面
     * @param email
     * @param validCode
     * @return
     */
    @RequestMapping("sendForgetPassWordMail")
    public String sendForgetPassWordMail(HttpServletRequest request,String email , String validCode){
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptcha.equals(validCode)){
            request.setAttribute("errorMsg","");
            return "user/forget_password";
        }

        int serverPort = request.getServerPort();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();

        StringBuilder href = new StringBuilder();
        href.append(serverName).append(":").append(serverPort).append(contextPath).append("/toModifyNewPasswordView.do");

        // 校验验证码
        int result =sysMailService.sendForgetPassWordMail(email,href.toString());
        if(result<1){
            return "error/error500";
        }
        return "views/user/forget_sendEmail_info";
    }

    /**
     *
     * @return
     */
    @RequestMapping("toModifyNewPasswordView")
    public String toModifyNewPasswordView(){
        return "";
    }

}