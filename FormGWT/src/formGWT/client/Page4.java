package formGWT.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;

public class Page4 extends Grid{
	TextBox textBox;
	Button refreshButton;
	private final Page4ServiceAsync service = GWT
			.create(formGWT.client.Page4Service.class);
	
	public Page4(int rows, int columns)
	{
		super(rows,columns);
		createPage();
		textBox = new TextBox();
		textBox.setWidth("400px");
		refreshButton = new Button("Refresh");
		refreshButton.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event) {
				
				service.getRandomString(new AsyncCallback<String> () {
					@Override
					public void onFailure (Throwable caught) {
						try {
						       throw caught;
						     } catch (Exception e) {
						    	 e.printStackTrace();
						     } catch (Throwable e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}

					@Override
					public void onSuccess(String result) {
						textBox.setText(result);					
					}
				});
			}
		});
	}
	private void createPage() {
		this.setWidget(0, 0, textBox);
		this.setWidget(1, 0, refreshButton);
	}
	public void updatePage() {
		this.setWidget(0, 0, textBox);
		this.setWidget(1, 0, refreshButton);
	}
}
