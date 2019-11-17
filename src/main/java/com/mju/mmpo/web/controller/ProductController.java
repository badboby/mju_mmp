package com.mju.mmpo.web.controller;



import com.github.pagehelper.PageInfo;
import com.mju.mmpo.domain.Product;
import com.mju.mmpo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author Allen李
 * @date
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        iProductService.save(product);
        return "redirect:/product/findAll.do";
    }


    @RequestMapping(value = "/delete/{pid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("pid") String id) throws Exception{
        //这边还需要添加一个功能就是在订单中再没有使用到此产品时，界面启用可以删除的功能，这边只是预先实现删除
        System.out.println(id);
        iProductService.delete(id);
        return "redirect:/product/findAll.do";
    }

    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findView(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                 @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        ModelAndView view = new ModelAndView();
        List<Product> list = iProductService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        view.addObject("pageInfo",pageInfo);
        view.setViewName("product-page-list");
        return view;
    }




}
