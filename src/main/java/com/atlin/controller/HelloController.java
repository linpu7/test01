package com.atlin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lin
 * @version v1.0
 * @program spring_security_demo
 * @description
 * @date 2021-01-31 21:04
 */
@RestController
public class HelloController {
    /**
     * annotation: @PreAuthorize()
     * pre 在add方法执行前检查
     * Authorize 权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('add')")
    @RequestMapping("/add.do")
    public String add() {
        return "success";
    }
    @PreAuthorize("hasAuthority('delete')")
    @RequestMapping("/delete.do")
    public String delete() {
        return "success";
    }

    @PreAuthorize("hasAuthority('update')")
    @RequestMapping("/update.do")
    public String update() {
        return "success";
    }

    @RequestMapping("/deleteAll")
    @PreAuthorize("hasRole('ABC')")//表示用户必须拥有ABC角色才能调用当前方法
    public String deleteAll(){
        System.out.println("deleteAll...");
        return "success";
    }

}

