package com.spring.bocompay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 获取前台页面
 * @author: Katherine
 * @create: 2018/4/25 17:39
 */
@Controller
@RequestMapping("bocompay")
public class BocompayGetController {

    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("bocompay/index");
        return modelAndView;
    }

    @GetMapping("singleLayerSingleOrderCreateAndPay")
    public ModelAndView getSingleLayerSingleOrderCreateAndPay() {
        ModelAndView modelAndView = new ModelAndView("bocompay/pay1_singleLayerSingleOrderCreateAndPay");
        return modelAndView;
    }

    @GetMapping("orderDetailQuery")
    public ModelAndView orderDetailQuery() {
        ModelAndView modelAndView = new ModelAndView("bocompay/orderDetailQuery");
        return modelAndView;
    }

    @GetMapping("tradeRefund")
    public ModelAndView tradeRefund() {
        ModelAndView modelAndView = new ModelAndView("/bocompay/tradeRefund");
        return modelAndView;
    }

    @GetMapping("extraIndex")
    public ModelAndView extraIndex() {
        ModelAndView modelAndView = new ModelAndView("/bocompayExtra/extraIndex");
        return modelAndView;
    }

    @GetMapping("onekeyIndex")
    public ModelAndView onekeyIndex() {
        return new ModelAndView("/bocompayOnekey/onekeyIndex");
    }
}
