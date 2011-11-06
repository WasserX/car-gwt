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
	
	private Button prevButton = new Button("Previous");
	private Button nextButton = new Button("Next");
	private int PAGE = 1;
	//First Page
	private Grid firstPageContent = new Grid(7, 2);
	private TextBox nameField = new TextBox();
	private TextBox lastNameField = new TextBox();
	private TextBox birthField = new TextBox();
	
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
	 * Updates the wepage content and buttons according to the current page
	 * 
	 */
	private void updateView() {	
		switch(PAGE){
		case 1:
			prevButton.setEnabled(false);
			nextButton.setEnabled(true);
			nextButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					firstPage(false);
					//secondPage(true);
					
				}
			});
			break;
		case 2:
			prevButton.setEnabled(true);
			prevButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					firstPage(true);
					//secondPage(false);
				}
			});
			nextButton.setEnabled(true);
			nextButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					//secondPage(false);
					//thirdPage(true);
				}
			});
			break;
		case 3:
			//TODO: Case 3
			break;
		}
		
	}

	
	/**
	 * Sets up the first page of the form.
	 * @param state Shows or hides the content
	 */
	private void firstPage(boolean state) {
		Label cityLabel = new Label("Hometown:");
		Label genderLabel = new Label("Gender:");
		Label mailLabel = new Label("E-mail Address:");
	
		//Form contents
		firstPageContent.setWidget(0, 0, new Label("Name:"));
		firstPageContent.setWidget(0, 1, nameField);
		firstPageContent.setWidget(1, 0, new Label("Last Name:"));
		firstPageContent.setWidget(1, 1, lastNameField);
		firstPageContent.setWidget(2, 0, new Label("Date of Birth:"));
		firstPageContent.setWidget(2, 1, birthField);
		firstPageContent.setWidget(3, 0, new Label("Birth Country:"));
		
		RootPanel.get().add(firstPageContent);
		
		PAGE = 1;
		updateView();
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	
		navPanel.add(prevButton);
		navPanel.add(nextButton);
		
		firstPage(true);
		RootPanel.get().add(navPanel);
	}
}