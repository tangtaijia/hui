package com.hui.common.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.DESUtil;

public class SendSMSMessage {

	private static final String HOST = "221.130.6.212:2382";

	private static final String URL = "http://" + HOST + "/72ch/72chMT";

	private static final String key = "1234";

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SendSMSMessage.class);

	private HttpClient httpClient;
	private PostMethod postMethod;
	private MessageFactory messageFactory;
	private NameValuePair sessionId;

	private static final DateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	public SendSMSMessage() {
		this.httpClient = new HttpClient();
		// 设置超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
				30000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);
		try {
			this.messageFactory = MessageFactory.newInstance();
		} catch (SOAPException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 消息体
	 * 
	 * @param phoneNumber
	 * @param messageContent
	 * @return
	 * @throws SOAPException
	 */
	public SOAPMessage createRequestMessage(String phoneNumber,
			String messageContent) throws Exception {
		DESUtil des = new DESUtil();
		des.setKey(key);
		SOAPMessage message = messageFactory.createMessage();
		message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();

		SOAPHeader header = envelope.getHeader();

		header.addChildElement("TransactionID", "header",
				DBUtils.getInstance().getSitePath()).addTextNode(
				CommonUtils.getRandomString(12));
		header.addChildElement("ServiceType", "header",
				DBUtils.getInstance().getSitePath()).addTextNode("/72ch/72chMT");
		header.addChildElement("PassWord", "header",
				DBUtils.getInstance().getSitePath()).addTextNode(
				des.encryptStr(key));

		SOAPBody body = envelope.getBody();
		body.addChildElement("MsgType").addTextNode("10");
		body.addChildElement("SendID").addTextNode("");
		body.addChildElement("SendMode").addTextNode("0");
		body.addChildElement("RecivId").addTextNode(phoneNumber);
		body.addChildElement("RecivClassId").addTextNode("");
		body.addChildElement("MsgId").addTextNode("");
		Calendar cal = Calendar.getInstance();
		body.addChildElement("SendTime").addTextNode(sdf.format(cal.getTime()));
		body.addChildElement("MessageContent").addTextNode(messageContent);

		message.saveChanges();

		System.out.println(" REQUEST: ");
		message.writeTo(System.out);
		System.out.println(" ");

		return message;
	}

	public SOAPMessage sendMessage(SOAPMessage input) throws IOException,
			SOAPException {
		this.postMethod = new PostMethod(URL);
		byte[] dataAsBytes = null;

		if (input == null) {
			logger.debug("Send a empty post");
			dataAsBytes = new byte[0];
		} else {

			ByteArrayOutputStream data = new ByteArrayOutputStream();
			input.writeTo(data);
			dataAsBytes = data.toByteArray();
		}

		RequestEntity entity = new ByteArrayRequestEntity(dataAsBytes);
		this.postMethod.setRequestEntity(entity);

		if (this.sessionId != null) {
			this.postMethod.addParameter(this.sessionId);
		}

		this.httpClient.executeMethod(this.postMethod);

		sessionId = this.postMethod.getParameter("SessionID");

		InputStream in = this.postMethod.getResponseBodyAsStream();

		if (null == in) {
			return null;
		}

		return this.messageFactory.createMessage(null, in);
	}
	
	public static void send(String phoneNumber, String messageContent) {
		SendSMSMessage message = new SendSMSMessage();
		SOAPMessage soap;
		try {
			soap = message.createRequestMessage(phoneNumber, messageContent);
			soap = message.sendMessage(soap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendSMSMessage.send("15062294657","我是一个好人");
	}

}