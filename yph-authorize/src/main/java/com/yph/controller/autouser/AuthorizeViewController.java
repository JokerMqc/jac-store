package com.yph.controller.autouser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * @create 2018-01-29 14:16
 **/
@Controller
@RequestMapping("authorize/autouser/view")
public class AuthorizeViewController {


    /**
     * @Description: 跳转到自动生成用户界面
     * @return String
     * @throws
     * @author 毛其传
     * @date 2018/1/29 14:41
     */
    @RequestMapping("toAutoUserView")
    public String toAutoUserView(){

        return "authorize/authorize_list";
    }

}
