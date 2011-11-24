package formGWT.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("Page4")
public interface Page4Service extends RemoteService{
	public String getRandomString();
}
