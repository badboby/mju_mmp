package com.mju.mmpo.web.controller;

import com.github.pagehelper.PageInfo;
import com.mju.mmpo.domain.Permission;
import com.mju.mmpo.service.IPermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue ="1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-page-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping(value = "/delete/{uid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("uid") String uid) throws Exception{
        permissionService.deleteByPermissionId(uid);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String pid) throws Exception{
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.selectById(pid);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }
}
