package com.spring.bocompay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/27 14:47
 */
@Controller
@RequestMapping("bocompay/onekey")
public class BocompayOneKeyGetController {

    @GetMapping("BPAYPY4161_agree")
    public ModelAndView BPAYPY4161_agree() {
        return new ModelAndView("/bocompayOnekey/BPAYPY4161_agree");
    }

    @GetMapping("BPAYPY4162_queryAgree")
    public ModelAndView BPAYPY4162_queryAgree() {
        return new ModelAndView("bocompayOnekey/BPAYPY4162_queryAgree");
    }

    @GetMapping("BPAYPY4163_unAgree")
    public ModelAndView BPAYPY4163_unAgree() {
        return new ModelAndView("bocompayOnekey/BPAYPY4163_unAgree");
    }

    @GetMapping("BPAYPY4164_sendMessage")
    public ModelAndView BPAYPY4164_sendMessage() {
        return new ModelAndView("bocompayOnekey/BPAYPY4164_sendMessage");
    }

    @GetMapping("BPAYPY4166_pay0_singlePayByMessage")
    public ModelAndView BPAYPY4166_pay0_singlePayByMessage() {
        return new ModelAndView("bocompayOnekey/BPAYPY4166_pay0_singlePayByMessage");
    }

    @GetMapping("BPAYPY4167_pay1_multiPayByMessage")
    public ModelAndView BPAYPY4167_pay1_multiPayByMessage() {
        return new ModelAndView("bocompayOnekey/BPAYPY4167_pay1_multiPayByMessage");
    }

    @GetMapping("BPAYPY4168_pay0_singlePayDirect")
    public ModelAndView BPAYPY4168_pay0_singlePayDirect() {
        return new ModelAndView("bocompayOnekey/BPAYPY4168_pay0_singlePayDirect");
    }

    @GetMapping("BPAYPY4169_pay1_multiPayDirect")
    public ModelAndView BPAYPY4169_pay1_multiPayDirect() {
        return new ModelAndView("bocompayOnekey/BPAYPY4169_pay1_multiPayDirect");
    }

}
