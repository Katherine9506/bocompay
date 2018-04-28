package com.spring.bocompay.controller;

import com.bocom.bocompay.BocomClient;
import com.spring.bocompay.domain.util.Response;
import com.spring.bocompay.util.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/28 9:44
 */
public class BaseController {

    public Response initializeResponse(BocomClient client) {
        return new Response(
                client.getHead("RspType"),
                client.getHead("RspCode"),
                client.getHead("RspMsg"),
                client.getHead("RspDate"),
                client.getHead("RspTime")
        );
    }

    public BocomClient initializeRequest(HttpServletRequest request, BocomClient client) {
        client.setHead("TranCode", request.getParameter("TranCode"));
        client.setHead("MerPtcId", request.getParameter("MerPtcId"));
        client.setHead("TranDate", request.getParameter("TranDate"));
        client.setHead("TranTime", request.getParameter("TranTime"));
        return client;
    }

    public ResponseMessage responseRecWithTranType(BocomClient client, String rst, String MerCertID, String TranCode, String TranType, Map<String, Object> responseData) {
        ResponseMessage responseMessage = null;
//        String rst = client.execute(MerCertID, TranCode, TranType);
        if (rst == null) {
            responseMessage = new ResponseMessage(true, client.getLastErr(), 500, null);
        } else {
            Response response = this.initializeResponse(client);
            if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
                responseMessage = new ResponseMessage(true, client.getHead("RspMsg"), 201, response);
            } else {
                response.setData(responseData);
                responseMessage = new ResponseMessage(true, "成功", 200, response);
            }
        }
        return responseMessage;
    }

    public ResponseMessage responseRec(BocomClient client, String rst, String MerCertID, String TranCode, Map<String, Object> responseData) {
        return this.responseRecWithTranType(client, rst, MerCertID, TranCode, null, responseData);
    }

}
