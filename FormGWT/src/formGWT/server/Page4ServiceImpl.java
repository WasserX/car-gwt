package formGWT.server;

import java.util.Random; 
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import formGWT.client.Page4Service;
import formGWT.client.Page4;

@SuppressWarnings("serial")
public class Page4ServiceImpl extends RemoteServiceServlet implements Page4Service {
	String[] words = {"car", "computer", "bicycle","monkey", "stairs", "is", "a", "the", "computes", "this"};
	int qtdWord;

	
	@Override
	public String getRandomString() {
		Random rand = new Random();
		qtdWord = rand.nextInt(10);
		
		String text = new String();
		for(int i = 0; i < qtdWord; i++)
		{
		        text += words[(rand.nextInt(words.length))];
		        text += " ";
		}
	    return text;
	}
}