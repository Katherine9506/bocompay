package com.spring.bocompay.service.merchant;

import com.bocom.bocompay.BocomClient;
import com.spring.bocompay.domain.merchant.MerRetNotifyUrl;
import com.spring.bocompay.service.BaseService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/26 17:25
 */
@Service
public class MerRetNotifyUrlService extends BaseService {

    /**
     * @param merRetNotifyUrl 前后台通知地址
     * @description: 填充一级商户订单前台/后台通知地址
     * @author: Katherine
     * @create: 2018/4/26 17:28
     */
    public ResponseMessage fillMerRetNotifyUrl(BocomClient client, MerRetNotifyUrl merRetNotifyUrl) {
        merRetNotifyUrl.setMerPtcId(client.getData("MerPtcId"));
        merRetNotifyUrl.setReturnURL(client.getData("ReturnURL"));
        merRetNotifyUrl.setNotifyURL(client.getData("NotifyURL"));
        merRetNotifyUrl.setAgreedReturnURL(client.getData("AgreedReturnURL"));
        merRetNotifyUrl.setAgreedNotifyURL(client.getData("AgreedNotifyURL"));

        return this.success(merRetNotifyUrl);
    }
}
