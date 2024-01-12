package enviando_email.enviando_email;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.callback.TextOutputCallback;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	 
	
	
	
	@org.junit.Test
	public void TesteEmail() throws Exception{
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("Estou utilizando código em HTML para visualização <br/><br/>");
		stringBuilderTextoEmail.append("Para fazer uma pesquisa no Google, clique no Botão Abaixo:<br/><br/>");
		stringBuilderTextoEmail.append("Aqui teria um HTML, mas como está dando Erro e estou cansado. DEU!");
		
		
	ObjetoEmail enviaEmail = 
			new ObjetoEmail("emaildestino@live.com",  //Nessa String, você encurtará o código para ir mais direto e só
			"Nome do Remetente - Dev",
			"Assunto do E-mail",
			stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmailAnexo(true);
		
	}
}
