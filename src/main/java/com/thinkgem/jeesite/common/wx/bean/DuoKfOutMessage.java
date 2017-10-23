package com.thinkgem.jeesite.common.wx.bean;

public class DuoKfOutMessage extends OutMessage {
	
	public DuoKfOutMessage() {
		
	}
	
	public DuoKfOutMessage(InMessage msg) {
		setToUserName(msg.getFromUserName());
		setFromUserName(msg.getToUserName());
		setCreateTime(System.currentTimeMillis()/1000);
	}
	
	private String	MsgType	= "transfer_customer_service";
	
	private TransInfo TransInfo = null;
	
	public class TransInfo {
		String KfAccount;

		public String getKfAccount() {
			return KfAccount;
		}

		public void setKfAccount(String kfAccount) {
			KfAccount = kfAccount;
		}
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public TransInfo getTransInfo() {
		return TransInfo;
	}

	public void setTransInfo(TransInfo transInfo) {
		TransInfo = transInfo;
	}
}
