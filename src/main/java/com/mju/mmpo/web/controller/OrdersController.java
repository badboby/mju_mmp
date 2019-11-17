package com.mju.mmpo.web.controller;

import com.github.pagehelper.PageInfo;
import com.mju.mmpo.domain.Orders;
import com.mju.mmpo.domain.Product;
import com.mju.mmpo.service.IOrdersService;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    private IOrdersService ordersService;
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Orders orders) throws Exception{
        ordersService.save(orders);
        return "redirect:/orders/findAll.do";
    }
    @RequestMapping(value = "/delete/{oid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("oid") String id) throws Exception{
        ordersService.delete(id);
        return "redirect:/orders/findAll.do";
    }
    //这边应该是查询product的全部 和 查询member的全部以便传输给页面进行选择
    @RequestMapping("/add.do")
    public ModelAndView add() throws Exception {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("orders-add");
        return mv;
    }

    @RequestMapping("/selectById.do")
    public ModelAndView selectById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.selectById(id);
        mv.addObject(orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
