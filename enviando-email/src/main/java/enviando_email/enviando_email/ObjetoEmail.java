package enviando_email.enviando_email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEmail {

		private String userName = "luizfelipeandradedev@gmail.com";
		private String Senha = "rydqmopktghneqpa"; //senha criada para autenticação de dois fatores//
		private String listaDestinatario = ""; //Espaço em Branco -  o nome das strings já dizem o que elas vão fazer.
		private String nomeRemetente = ""; //Espaço em Branco - o nome das strings já dizem o que elas vão fazer.
		private String assuntoEmail = ""; //Espaço em Branco - o nome das strings já dizem o que elas vão fazer.
		private String textoEmail = ""; //Espaço em Branco - o nome das strings já dizem o que elas vão fazer.
		
		public ObjetoEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textoEmail) {
			this.listaDestinatario = listaDestinatario;
			this.nomeRemetente = nomeRemetente;
			this.assuntoEmail = assuntoEmail;
			this.textoEmail = textoEmail;
		}
		
		public void enviarEmail(boolean envioHtml) throws Exception{
			
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");/*autorização*/
			properties.put("mail.smtp.starttls", "true");//Autenticação//
			properties.put("mail.smtp.host", "smtp.gmail.com");//Servidor Gmail//
			properties.put("mail,smtp.port", "465");//Porta do servidor//
			properties.put("mail.smtp.socketFactory.port", "465");//Especifica a porta a ser conectada pelo socket//
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Classe do Socket de conexão ao SMTP//
			
			Session session = Session.getInstance(properties, new Authenticator() {
				
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, Senha);
			}
				});
			
			Address[] toUser = InternetAddress.parse(listaDestinatario);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assuntoEmail);
			
			if(envioHtml) {
				message.setContent(textoEmail, "text/html; charset=utf-8"); // Aqui seria a entrada do html, mas o Eclipse não está aceitando o caractere barra ao contrário e fiquei sem saco de continuar.//
			}else {
				message.setText(textoEmail);
			}
			
			
			message.setText(textoEmail);
			
			Transport.send(message);	
			
		}
		
		
		
public void enviarEmailAnexo(boolean envioHtml) throws Exception{
			
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");/*autorização*/
			properties.put("mail.smtp.starttls", "true");//Autenticação//
			properties.put("mail.smtp.host", "smtp.gmail.com");//Servidor Gmail//
			properties.put("mail,smtp.port", "465");//Porta do servidor//
			properties.put("mail.smtp.socketFactory.port", "465");//Especifica a porta a ser conectada pelo socket//
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Classe do Socket de conexão ao SMTP//
			
			Session session = Session.getInstance(properties, new Authenticator() {
				
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, Senha);
			}
				});
			
			Address[] toUser = InternetAddress.parse(listaDestinatario);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assuntoEmail);
			
			
			//Parte 1 do e-mail que é o texto e a descrição do e-mail//
			
			MimeBodyPart corpoEmail = new MimeBodyPart();
			if(envioHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
			}else {
				corpoEmail.setText(textoEmail);
			}
			
			java.util.List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			
			int index = 0;
			for (FileInputStream fileInputStream : arquivos) {
			
			
			
			//Parte 2 do e-mail que são os anexos em pdf//
			MimeBodyPart anexoEmail = new MimeBodyPart();
			
			//Onde é passado o simulador de PDF//
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			anexoEmail.setFileName("anexoemail.pdf");
			
			
			multipart.addBodyPart(anexoEmail);
			index++;
			
			
			}
			
			message.setContent(multipart);
			Transport.send(message);	
			
		}
			
	//Criação do PDF e adição do texto//
	private FileInputStream simuladorDePDF() throws Exception{
	Document document = new Document();
	File file = new File("fileanexo.pdf");
	file.createNewFile();
	PdfWriter.getInstance(document, new FileOutputStream(file));
	document.open();
	document.add(new Paragraph("Conteúdo do PDF anexo com Java Mail, arquivo em PDF")); // Já descrevi o que estará escrito no pdf e que ele será criado aqui//
	document.close();
	return new FileInputStream(file);
	
}
	}


