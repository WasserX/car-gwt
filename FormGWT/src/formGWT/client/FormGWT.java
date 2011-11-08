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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
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
	private Grid pageContent = new Grid(9, 2);
	private TextBox nameField = new TextBox();
	private TextBox lastNameField = new TextBox();
	private TextBox birthField = new TextBox();
	private ListBox birthCountryField = new ListBox();
	private ListBox birthPlaceField = new ListBox();
	private RadioButton maleRadioButton = new RadioButton("gender","Male");
	private RadioButton femaleRadioButton = new RadioButton("gender","Female");
	private TextBox emailField = new TextBox();
	
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
					updateView();
					PAGE++;			
				}
			});
			showFirstPage();
			break;
		case 2:
			prevButton.setEnabled(true);
			prevButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					updateView();
					PAGE--;
				}
			});
			nextButton.setEnabled(false);
			nextButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					updateView();
					//PAGE++;
				}
			});
			showSecondPage();
			break;
		case 3:
			//TODO: Case 3
			break;
		}
		showNavigation();		
	}
	
	private void showNavigation() {
		navPanel.add(prevButton);
		navPanel.add(nextButton);
		RootPanel.get().add(navPanel);
	}


	
	/**
	 * Sets up the first page of the form.
	 */
	private void showFirstPage() {
		Label cityLabel = new Label("Hometown:");
		Label genderLabel = new Label("Gender:");
		Label mailLabel = new Label("E-mail Address:");
	
		//Form contents
		pageContent.clear();
		pageContent.setWidget(0, 0, new Label("First Page"));
		pageContent.setWidget(1, 0, new Label("Name:"));
		pageContent.setWidget(1, 1, nameField);
		pageContent.setWidget(2, 0, new Label("Last Name:"));
		pageContent.setWidget(2, 1, lastNameField);
		pageContent.setWidget(3, 0, new Label("Date of Birth:"));
		pageContent.setWidget(3, 1, birthField);
		pageContent.setWidget(4, 0, new Label("Birth Country:"));
		pageContent.setWidget(4, 1, birthCountryField);
		pageContent.setWidget(5, 0, new Label("Birth Place:"));
		pageContent.setWidget(5, 1, birthPlaceField);
		pageContent.setWidget(6, 0, maleRadioButton);
		pageContent.setWidget(6, 1, femaleRadioButton);
		pageContent.setWidget(7, 0, new Label("E-mail:"));
		pageContent.setWidget(7, 1, emailField);
		
		RootPanel.get().add(pageContent);
		
	}
	
	/**
	 * Sets up the second page of the form.
	 */
	private void showSecondPage() {
		Label cityLabel = new Label("Hometown:");
		Label genderLabel = new Label("Gender:");
		Label mailLabel = new Label("E-mail Address:");
	
		//Form contents
		pageContent.clear();
		pageContent.setWidget(0, 0, new Label("Second Page"));
		pageContent.setWidget(1, 0, new Label("Name:"));
		pageContent.setWidget(1, 1, nameField);
		pageContent.setWidget(2, 0, new Label("Last Name:"));
		pageContent.setWidget(2, 1, lastNameField);
		pageContent.setWidget(3, 0, new Label("Date of Birth:"));
		pageContent.setWidget(3, 1, birthField);
		//pageContent.setWidget(3, 0, new Label("Birth Country:"));
		
		RootPanel.get().add(pageContent);
		
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		PAGE = 1;
		updateView();
	}
}