package jms;

import handle.XMLValidation;
import handle.XmlJmsConverter;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import org.w3c.dom.Document;

public class SummaryCreator {
	
	Document doc;

	public static void main(String[] args) {
		
		try {
			new DurableTopicRecvClient().listen();
		} catch (JMSException | NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void gotNewMessage(TextMessage msg){
		try {
			doc = XmlJmsConverter.loadXMLFromString(msg.getText());
			XmlJmsConverter.createXML(doc, "latestnews.xml");
			if(XMLValidation.validateXMLSchema("src/main/resources/modelo.xsd", "latestnews.xml")){
				System.out.println("O XML recebido foi validado com sucesso!");
			} else {
				System.out.println("O XML recebido não é válido!");
			}
			
			System.out.println("nova mensagem: " );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
