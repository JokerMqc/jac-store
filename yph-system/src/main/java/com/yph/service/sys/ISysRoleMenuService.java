package com.yph.service.sys;

import com.yph.entity.sys.SysRoleMenu;
import com.yph.entity.sys.vo.SysRoleMenuVo;
import com.yph.entity.tree.ZtreeVo;

import java.util.HashMap;
import java.util.List;

/**
 * 系统角色菜单
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
public interface ISysRoleMenuService {

    /**
     *  保存角色菜单
     * @param sysRoleMenu
     * @return
     */
    int saveRoleMenu(SysRoleMenu sysRoleMenu);

    /**
     *  更新角色菜单
     * @param sysRoleMenu
     * @return
     */
    int updateRoleMenu(SysRoleMenu sysRoleMenu);

    /**
     *  删除角色菜单
     * @param sysRoleMenu
     * @return
     */
    int delRoleMenu(SysRoleMenu sysRoleMenu);

    /**
     *  通过角色Id 获取菜单角色列表
     * @return
     */
    List<ZtreeVo> findRoleAllMenuList(HashMap<String, Object> params);

    /**
     *  根据角色获取菜单
     * @param params
     * @return
     */
    int updateRoleMenuByList(HashMap<String, Object> params);

}
