package com.spring.bocompay.controller;

import com.bocom.bocompay.BocomClient;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 下载类接口
 * @author: Katherine
 * @create: 2018/4/28 14:57
 */
@RestController
@RequestMapping("bocompay/download")
public class BocompayDownloadController extends BaseController {

    /**
     * @param
     * @description: 对账文件下载<BPAYPY4281>
     * @author: Katherine
     * @create: 2018/4/28 14:59
     */
    @PostMapping("bill")
    public ResponseMessage downloadBill(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();

        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath); //

        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("FileType", request.getParameter("FileType"));
            client.setData("FileFormat", request.getParameter("FileFormat"));
            client.setData("SettDate", request.getParameter("SettDate"));

            String rst = client.execute(MerCertID, TranCode);

            responseMessage = this.responseRec(client, rst, MerCertID, TranCode, null);
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 报表文件下载<BPAYPY4282>
     * @author: Katherine
     * @create: 2018/4/28 15:17
     */
    @PostMapping("report")
    public ResponseMessage downloadReport(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();

        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath); //

        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));
            client.setData("FileType", request.getParameter("FileType"));
            client.setData("FileFormat", request.getParameter("FileFormat"));
            client.setData("SettDate", request.getParameter("SettDate"));

            String rst = client.execute(MerCertID, TranCode);

            responseMessage = this.responseRec(client, rst, MerCertID, TranCode, null);
        }

        return responseMessage;
    }
}
