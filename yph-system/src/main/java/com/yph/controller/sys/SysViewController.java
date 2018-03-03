package com.yph.controller.sys;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统视图控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Controller
@RequestMapping("/sys/view")
public class SysViewController {


    /************************************************    菜单    START   ***********************************************/

    /**
     * 跳转到菜单编辑页面
     *
     * @return
     */
    @RequestMapping("eidtSysMenuView")
    public String eidtSysMenuView() {
        return "sys/menu/sys_menu_list";
    }

    /**
     * @return
     */
    @RequestMapping("eidtSysUserView")
    public String eidtSysUserView() {
        return "";
    }

    /**
     * 跳转到 系统角色列表
     *
     * @return
     */
    @RequestMapping("toSysRoleListView")
    public String toSysRoleListView() {
        return "sys/role/sys_role_list";
    }

    /**
     * 跳转到编辑角色菜单
     *
     * @return
     */
    @RequestMapping("toEditRoleMenuView")
    public String toEditRoleMenuView() {
        return "sys/role/sys_role_menu";
    }

    /**
     * 跳转到用户列表
     *
     * @return
     */
    @RequestMapping("toSysUserView")
    public String toSysUserView() {
        return "sys/user/sys_user_list";
    }

    /************************************************    邮件    START   ***********************************************/

    /**
     * 跳转到邮件编辑页面
     *
     * @return
     */
    @RequestMapping("toSysEmailView")
    public String toSysEmailView() {
        return "sys/email/sys_message_template";
    }

    /**
     * 跳转到邮件列表页面
     *
     * @return
     */
    @RequestMapping("toSysEmailListView")
    public String toSysEmailListView() {
        return "sys/email/sys_mail_list";
    }

    /**
     * 邮件管控台
     *
     * @return
     */
    @RequestMapping("toSysEmailContrlView")
    public String toSysEmailContrlView() {
        return "sys/email/sys_mail_contrl";
    }

    /************************************************    字典    START   ***********************************************/

    /**
     * 字典介绍
     *
     * @return
     */
    @RequestMapping("toDictInfoView")
    public String toDictInfoView() {
        return "sys/dict/sys_info";
    }

    /**
     * 字典类型 页面
     *
     * @return
     */
    @RequestMapping("toDictTypeListView")
    public String toDictTypeListView() {
        return "sys/dict/sys_dictType_list";
    }

    /**
     * 跳转到字典资源页面
     *
     * @return
     */
    @RequestMapping("toDictResListView")
    public String toDictResListView() {
        return "sys/dict/sys_dictRes_list";
    }

    /************************************************    日志管理    START   ***********************************************/

    /**
     * 跳转到系统日志页面
     *
     * @return
     */
    @RequestMapping("toSysLogListView")
    public String toSysLogListView() {
        return "sys/log/sys_log_wrong";
    }


}
