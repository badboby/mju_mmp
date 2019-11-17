package com.mju.mmpo.web.controller;

import com.github.pagehelper.PageInfo;
import com.mju.mmpo.domain.SysLog;
import com.mju.mmpo.service.ISysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Allen李
 * @date
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Resource
    private ISysLogService logService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<SysLog> list = logService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-page-list");
        return mv;
    }
}
