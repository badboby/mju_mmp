package com.mju.mmpo.web.controller;

import com.github.pagehelper.PageInfo;
import com.mju.mmpo.domain.Role;
import com.mju.mmpo.domain.UserInfo;
import com.mju.mmpo.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Allen李
 * @date
 */
@Controller
@RequestMapping("/user")
class UserController {

    @Resource
    private IUserService userService;

    //查询指定id的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.selectById(userId);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //用户添加
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-page-list");
        return mv;
    }

    @RequestMapping(value = "/delete/{uid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("uid") String uid) throws Exception{
        userService.delete(uid);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                                @RequestParam(name = "ids",required = true) String[] roleIds)
            throws Exception{
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findAll.do";
    }
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "userId",required = true) String userId) throws Exception{
        ModelAndView mv= new ModelAndView();
        List<Role> list = userService.findOtherRoles(userId);
        UserInfo userInfo = userService.selectById(userId);
        mv.addObject("roleList",list);
        mv.addObject("user",userInfo);
        mv.setViewName("user-role-add");
        return mv;
    }
}