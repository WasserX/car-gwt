package formGWT.client;

import formGWT.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FormGWT implements EntryPoint {
	
	private Button sendButton = new Button("Send");
	private Button prevButton = new Button("Previous");
	private Button nextButton = new Button("Next");
	/*First Page*/
	private TextBox nameField = new TextBox();
	private TextBox lastNameField = new TextBox();
	private DatePicker birthPicker = new DatePicker();
	
	private HorizontalPanel navPanel = new HorizontalPanel();
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	/**
	 * Updates the navigation panel after a change on the website (enables/disables buttons).
	 * TODO: Only allows sending after all fields are full and we are at the last page.
	 */
	private void updateNavPanel() {	
	}

	
	//TODO: Deal with the change of pages.
	private void drawFirstPage() {
		Label birthLabel = new Label("Date of Birth:");
		Label countryLabel = new Label("Birth Country:");
		Label cityLabel = new Label("Hometown:");
		Label genderLabel = new Label("Gender:");
		Label mailLabel = new Label("E-mail Address:");

		Grid content = new Grid(7, 2);
	
		content.setWidget(0, 0, new Label("Name:"));
		content.setWidget(0, 1, nameField);
		content.setWidget(1, 0, new Label("Last Name:"));
		content.setWidget(1, 1, lastNameField);
		RootPanel.get().add(content);		
		
		/*
		 * Version Using Panels
		VerticalPanel firstPagePanel = new VerticalPanel();
		
		HorizontalPanel namePanel = new HorizontalPanel();
		namePanel.add(new Label("Name:"));
		namePanel.add(nameField);
		firstPagePanel.add(namePanel);
		
		HorizontalPanel lastNamePanel = new HorizontalPanel();
		lastNamePanel.add(new Label("Last Name:"));
		lastNamePanel.add(lastNameField);
		firstPagePanel.add(lastNamePanel);
		
		RootPanel.get().add(firstPagePanel);
		 */
	
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	
		navPanel.add(prevButton);
		navPanel.add(sendButton);
		navPanel.add(nextButton);
		
		drawFirstPage();					
	}
}
