package com.tianyang.modules.sys.utils;

/**
 * 微信发送文本消息
 * @author Administrator
 *
 */
public class TextMessage {
	
		/*{
		   "touser": "UserID1|UserID2|UserID3", 成员ID列表 
		   "toparty": " PartyID1 | PartyID2 ", 部门ID列表 
		   "totag": " TagID1 | TagID2 ", 标签ID列表
		   "msgtype": "text",  消息类型 (必须)
		   "agentid": 1, 企业应用的id（必须）
		   "text": {
		       "content": "Holiday Request For Pony(http://xxxxx)" 消息内容 （必须）
		   },
		   "safe":"0" 表示是否是保密消息，0表示否，1表示是，默认0 
		}*/
	
	private String touser;
	private String toparty;
	private String totag;
	private String msgtype;
	private Integer agentid;
	private Content text;
	private String safe;
	public String getTouser() {
		return touser;
	}
	public Content getText() {
		return text;
	}
	public void setText(Content text) {
		this.text = text;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	
	
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
}
