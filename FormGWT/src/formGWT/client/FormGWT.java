package formGWT.client;


import formGWT.client.internationalization.TextTools;
import formGWT.client.internationalization.Texts;
import formGWT.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FocusListener;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FormGWT implements EntryPoint {
	
	private Button prevButton = new Button("Previous");
	private Button nextButton = new Button("Next");
	private int PAGE = 1;
	
	//First Page
	private Grid firstPageContent = new Grid(9, 2);
	private TextBox nameField = new TextBox();
	private TextBox lastNameField = new TextBox();
	private TextBox birthField = new TextBox();
	private ListBox birthCountryField = new ListBox();
	private ListBox birthPlaceField = new ListBox();
	private RadioButton maleRadioButton = new RadioButton("gender","Male");
	private RadioButton femaleRadioButton = new RadioButton("gender","Female");
	private TextBox emailField = new TextBox();
	
	//Second Page
	private Grid secondPageContent = new Grid(3,2);
	private ListBox filmNameField = new ListBox();
	
	//Third Page
	private Grid thirdPageContent = new Grid(9,2);
	
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
					PAGE++;
					RootPanel.get().remove(firstPageContent);
					updateView();
				}
			});
			showFirstPage();
			break;
		case 2:
			prevButton.setEnabled(true);
			prevButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					PAGE--;
					RootPanel.get().remove(secondPageContent);
					updateView();
				}
			});
			nextButton.setEnabled(true);
			nextButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					PAGE++;
					RootPanel.get().remove(secondPageContent);
					updateView();
				}
			});
			showSecondPage();
			break;
		case 3:
			prevButton.setEnabled(true);
			prevButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					PAGE--;
					RootPanel.get().remove(thirdPageContent);
					updateView();
				}
			});
			nextButton.setEnabled(false);
			showThirdPage();
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
	@SuppressWarnings("deprecation")
	private void showFirstPage() {
		Label cityLabel = new Label("Hometown:");
		Label genderLabel = new Label("Gender:");
		Label mailLabel = new Label("E-mail Address:");
		
		Texts constants = GWT.create(Texts.class);	
	
		//Form contents
		firstPageContent.clear();
		firstPageContent.setWidget(0, 0, new Label("First Page"));
		firstPageContent.setWidget(1, 0, new Label("Name:"));
		firstPageContent.setWidget(1, 1, nameField);
		firstPageContent.setWidget(2, 0, new Label("Last Name:"));
		firstPageContent.setWidget(2, 1, lastNameField);
		firstPageContent.setWidget(3, 0, new Label("Date of Birth:"));
		firstPageContent.setWidget(3, 1, birthField);
		birthField.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event) {
				if(!birthField.getText().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)[0-9][0-9]"))
				{
					Window.alert("Date format is invalid!");
					birthField.setText("");		
				}
			}
        });
		firstPageContent.setWidget(4, 0, new Label("Birth Country:"));
		firstPageContent.setWidget(4, 1, TextTools.fillCountries(birthCountryField));
		firstPageContent.setWidget(5, 0, new Label("Birth Place:"));
		firstPageContent.setWidget(5, 1, birthPlaceField);
		firstPageContent.setWidget(6, 0, maleRadioButton);
		firstPageContent.setWidget(6, 1, femaleRadioButton);
		firstPageContent.setWidget(7, 0, new Label("E-mail:"));
		firstPageContent.setWidget(7, 1, emailField);
		emailField.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event) {
				if(!emailField.getText().matches("[a-z0-9._%-]+@[a-z0-9.-]+"))
				{
					Window.alert("Email format is invalid!");
					emailField.setText("");		
				}
			}
        });

		RootPanel.get().add(firstPageContent);
		
	}
	
	/**
	 * Sets up the second page of the form.
	 */
	private void showSecondPage() {
	
		//Form contents
		secondPageContent.clear();
		secondPageContent.setWidget(0, 0, new Label("Second Page"));
		secondPageContent.setWidget(1, 0, new Label("Film Name:"));
		secondPageContent.setWidget(1, 1, filmNameField);
		
		RootPanel.get().add(secondPageContent);		
	}
	
	/**
	 * Sets up the first page of the form.
	 */
	@SuppressWarnings("deprecation")
	private void showThirdPage() {
	
		//Form contents
		thirdPageContent.clear();
		thirdPageContent.setWidget(0, 0, new Label("Third Page"));
		thirdPageContent.setWidget(1, 0, new Label("Name:"));
		thirdPageContent.setWidget(1, 1, new Label(nameField.getText()));
		thirdPageContent.setWidget(2, 0, new Label("Last Name:"));
		thirdPageContent.setWidget(2, 1, new Label(lastNameField.getText()));
		thirdPageContent.setWidget(3, 0, new Label("Date of Birth:"));
		thirdPageContent.setWidget(3, 1, new Label(birthField.getText()));
		thirdPageContent.setWidget(4, 0, new Label("Birth Country:"));
		
		int selectedCountryIndex = birthCountryField.getSelectedIndex();
		if(selectedCountryIndex > 0)
			thirdPageContent.setWidget(4, 1, new Label(birthCountryField.getItemText(selectedCountryIndex)));
		else
			thirdPageContent.setWidget(4, 1, new Label("Undefined"));
		
		thirdPageContent.setWidget(5, 0, new Label("Birth Place:"));
		
		int selectedPlaceIndex = birthPlaceField.getSelectedIndex();
		if(selectedPlaceIndex > 0)
			thirdPageContent.setWidget(5, 1, new Label(birthPlaceField.getItemText(selectedPlaceIndex)));
		else
			thirdPageContent.setWidget(5, 1, new Label("Undefined"));
		
		thirdPageContent.setWidget(6, 0, new Label("Gender:"));
		
		if(maleRadioButton.getValue())
			thirdPageContent.setWidget(6, 1, new Label("Male"));
		else if(femaleRadioButton.getValue())
			thirdPageContent.setWidget(6, 1, new Label("Female"));
		else
			thirdPageContent.setWidget(6, 1, new Label("Undefined"));
		thirdPageContent.setWidget(7, 0, new Label("E-mail:"));
		thirdPageContent.setWidget(7, 1, new Label(emailField.getText()));

		RootPanel.get().add(thirdPageContent);
		
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		PAGE = 1;
		updateView();
	}
}