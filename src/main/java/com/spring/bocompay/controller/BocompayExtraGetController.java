package com.spring.bocompay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/26 18:22
 */
@Controller
@RequestMapping(value = "bocompayExtra")
public class BocompayExtraGetController {

    @GetMapping("merSignDetailQuery")
    public ModelAndView merSignDetailQuery() {
        return new ModelAndView("bocompayExtra/merSignDetailQuery");
    }

    @GetMapping("sndMerSignDetailQuery")
    public ModelAndView sndMerSignDetailQuery() {
        return new ModelAndView("bocompayExtra/sndMerSignDetailQuery");
    }

    @GetMapping("sndMerSignAdd")
    public ModelAndView sndMerSignAdd() {
        return new ModelAndView("bocompayExtra/sndMerSignAdd");
    }

    @GetMapping("pay0_singlePreOrderCreateAndPay")
    public ModelAndView pay0_singlePreOrderCreateAndPay() {
        return new ModelAndView("bocompayExtra/pay0_singlePreOrderCreateAndPay");
    }
}
