package pt.uc.dei.aor.paj.jms;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import org.w3c.dom.Document;

import pt.uc.dei.aor.paj.handle.XMLValidation;
import pt.uc.dei.aor.paj.handle.XmlJmsConverter;

public class SummaryCreator {
	
	Document doc;

	public static void main(String[] args) {
		
					
		try {
			new DurableTopicRecvClient().listen();
		} catch (JMSException | NamingException | IOException e) {
			
			System.out.println("Falha na escuta de mensagens");
		}
		
	}
	
	public void gotNewMessage(TextMessage msg){
		URL url = SummaryCreator.class.getResource("/modelo.xsd");
		try {
			String tmsg = msg.getText();
			System.out.println("mensagem recebida com " + tmsg.length());
			doc = XmlJmsConverter.loadXMLFromString(tmsg);
			XmlJmsConverter.createXML(doc, "latestnews.xml");
			if(XMLValidation.validateXMLSchema(url, "latestnews.xml")){
				System.out.println("O XML recebido foi validado com sucesso!");
			} else {
				System.out.println("O XML recebido não é válido!");
			}
			System.out.println("Nova mensagem recebida\n"
					+ "Ficheiro \"latestnews.xml\" atualizado!");
			
			
		} catch (Exception e) {
			System.out.println("Erro ao criar o XML");
		}
		
		ClassLoader classLoader = getClass().getClassLoader();
		File XslFile = new File(classLoader.getResource("text.xsl").getFile());
		
		copyFile(XslFile, "text.xsl");
		
	}
	
	private static void copyFile(File source, String finalFileName) {
		 File dest = new File(finalFileName); 
		try {
	    	if (dest.exists()) {
				dest.delete();
			}
	    		    	
			Files.copy(source.toPath(), dest.toPath());
		} catch (IOException e) {
			System.out.println("Falha ao copiar o ficheiro");
		}
	}
	


}
