package com.thinkgem.jeesite.common.utils;

public class SendEmailBean {
       private String content;
       private SendMailUtil seu;
       private String isSend;
       public String getisSend() {
    	   String message=this.toString();
    	   String  toMailAddr="928335288@qq.com";
    	   String  subject ="国商商标查询";
    	  // System.out.println(this.content.equals("111"));
    	   
    	   if(this.content==null)
    	   {
    		   isSend="false";
    		   return isSend;
    	   }
    	   
    	 //  if(this.content.equals("111"))
    	   seu.sendCommonMail(toMailAddr, subject, message);
    	   isSend="true";
		return isSend;
	}
	public void setisSend(String isSend) {
		this.isSend = isSend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "国商商标免费查询 [商标名称=" + content + ",联系人=" + name + ", 联系方式=" + mobile + "]";
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	private String name;
       private String mobile;
       
}
