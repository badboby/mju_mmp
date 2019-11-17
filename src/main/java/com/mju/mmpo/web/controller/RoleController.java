package com.mju.mmpo.web.controller;

import com.github.pagehelper.PageInfo;
import com.mju.mmpo.domain.Permission;
import com.mju.mmpo.domain.Role;
import com.mju.mmpo.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue ="1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-page-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }
    @RequestMapping(value = "/delete/{rid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("rid") String rid) throws Exception{
        //这边还需要添加一个功能就是在没有用户有这个权限时可以删除此权限、现在只是实现删除
        roleService.deleteRoleById(rid);
        return "redirect:/role/findAll.do";
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,
                                      @RequestParam(name = "ids",required = true) String[] permissionIds) throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:/role/findAll.do";
    }
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "roleId",required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> list = roleService.findOtherPermission(roleId);
        Role role = roleService.findById(roleId);
        mv.addObject("permissionList",list);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "roleId",required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
}
