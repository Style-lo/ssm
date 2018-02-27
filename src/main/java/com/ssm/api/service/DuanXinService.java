package com.ssm.api.service;

import java.util.ArrayList;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;


public class DuanXinService {
	    private static final String wxchinaMosIp = "211.147.239.62";
	    private static final int wxchinaMosPort = 9080;
	    private static final String wxchinaMosName = "ycwl@ycwl";
	    private static final String wxchinaMosPassword = "MOSyecaixgkp00";
	public GsmsResponse sendMessage(String phone, String content){
        PostMsg pm = new PostMsg();
        pm.getCmHost().setHost(wxchinaMosIp, wxchinaMosPort);
        Account account = new Account(wxchinaMosName, wxchinaMosPassword);

        return this.sendMessage(pm, account, phone, content);
    }
	
	public GsmsResponse sendMessage(PostMsg pm, Account ac, String phone, String content){
        MTPack pack = new MTPack();
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setBizType(1);
        pack.setDistinctFlag(false);
        pack.setSendType(MTPack.SendType.MASS);

        ArrayList<MessageData> msgs = new ArrayList<>();
        msgs.add(new MessageData(phone, content));
        pack.setMsgs(msgs);

        GsmsResponse resp = null;
        try {
            resp = pm.post(ac, pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
	public static void main(String[] args) {
		DuanXinService duanXinService = new DuanXinService();
		duanXinService.sendMessage("17620538053", "通知");
	}
}
