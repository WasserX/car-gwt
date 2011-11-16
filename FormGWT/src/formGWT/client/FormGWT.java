package formGWT.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import formGWT.client.i18n.TextTools;
import formGWT.client.i18n.Texts;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FormGWT implements EntryPoint {
	
	private Button prevButton = new Button();
	private Button nextButton = new Button();
	private int PAGE = 1;
	
	//First Page
	private Grid firstPageContent = new Grid(9, 2);
	private TextBox nameField = new TextBox();
	private TextBox lastNameField = new TextBox();
	private TextBox birthField = new TextBox();
	private ListBox birthCountryField = new ListBox();
	private ListBox birthPlaceField = new ListBox();
	private RadioButton maleRadioButton = new RadioButton("gender");
	private RadioButton femaleRadioButton = new RadioButton("gender");
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
		Texts constants = GWT.create(Texts.class);
		prevButton.setText(constants.previous());
		nextButton.setText(constants.next());
		navPanel.add(prevButton);
		navPanel.add(nextButton);
		RootPanel.get().add(navPanel);
	}


	
	/**
	 * Sets up the first page of the form.
	 */
	private void showFirstPage() {
		Texts constants = GWT.create(Texts.class);
		//Set up labels
		maleRadioButton.setText(constants.male());
		femaleRadioButton.setText(constants.female());
		//Form contents
		firstPageContent.clear();
		firstPageContent.setWidget(0, 0, new Label(constants.firstPage()));
		firstPageContent.setWidget(1, 0, new Label(constants.name()));
		firstPageContent.setWidget(1, 1, nameField);
		firstPageContent.setWidget(2, 0, new Label(constants.lastName()));
		firstPageContent.setWidget(2, 1, lastNameField);
		firstPageContent.setWidget(3, 0, new Label(constants.dateOfBirth()));
		firstPageContent.setWidget(3, 1, birthField);
		birthField.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event) {
				if(!birthField.getText().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)[0-9][0-9]"))
				{
					Texts constants = GWT.create(Texts.class);
					Window.alert(constants.invalidDate());
					birthField.setText("");		
				}
			}
        });
		firstPageContent.setWidget(4, 0, new Label(constants.birthCountry()));
		firstPageContent.setWidget(4, 1, TextTools.fillCountries(birthCountryField));
		birthCountryField.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selectedItem = birthCountryField.getSelectedIndex();
				if(selectedItem < 0) return;
				birthPlaceField.clear();
				Texts constants = GWT.create(Texts.class);
				//Because the items are in alphabetical order, we use their index that wont change
				switch(selectedItem){
				case 0: //Argentina
					birthPlaceField.addItem(constants.rosario()); break;
				case 1: //Brazil
					birthPlaceField.addItem(constants.sao()); break;
				case 2: //France
					birthPlaceField.addItem(constants.versailles()); break;
				case 3: //Italy
					birthPlaceField.addItem(constants.turin()); break;
				case 4: //USA
					birthPlaceField.addItem(constants.philadelphia()); break;
				default:
					birthPlaceField.addItem(constants.undefined());
				}
			}
		});
		firstPageContent.setWidget(5, 0, new Label(constants.birthPlace()));
		firstPageContent.setWidget(5, 1, birthPlaceField);
		//TODO Fire an event to make the default list linked to the countries list.
		firstPageContent.setWidget(6, 0, maleRadioButton);
		firstPageContent.setWidget(6, 1, femaleRadioButton);
		firstPageContent.setWidget(7, 0, new Label(constants.email()));
		firstPageContent.setWidget(7, 1, emailField);
		emailField.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event) {
				if(!emailField.getText().matches("[a-z0-9._%-]+@[a-z0-9.-]+"))
				{
					Texts constants = GWT.create(Texts.class);
					Window.alert(constants.invalidMail());
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
		Texts constants = GWT.create(Texts.class);
		//Form contents
		secondPageContent.clear();
		secondPageContent.setWidget(0, 0, new Label(constants.secondPage()));
		secondPageContent.setWidget(1, 0, new Label("Film Name:"));
		secondPageContent.setWidget(1, 1, filmNameField);
		
		RootPanel.get().add(secondPageContent);		
	}
	
	/**
	 * Sets up the first page of the form.
	 */
	private void showThirdPage() {
		Texts constants = GWT.create(Texts.class);
		//Form contents
		thirdPageContent.clear();
		thirdPageContent.setWidget(0, 0, new Label(constants.thirdPage()));
		thirdPageContent.setWidget(1, 0, new Label(constants.name()));
		thirdPageContent.setWidget(1, 1, new Label(nameField.getText()));
		thirdPageContent.setWidget(2, 0, new Label(constants.lastName()));
		thirdPageContent.setWidget(2, 1, new Label(lastNameField.getText()));
		thirdPageContent.setWidget(3, 0, new Label(constants.dateOfBirth()));
		thirdPageContent.setWidget(3, 1, new Label(birthField.getText()));
		thirdPageContent.setWidget(4, 0, new Label(constants.birthCountry()));
		
		int selectedCountryIndex = birthCountryField.getSelectedIndex();
		if(selectedCountryIndex > 0)
			thirdPageContent.setWidget(4, 1, new Label(birthCountryField.getItemText(selectedCountryIndex)));
		else
			thirdPageContent.setWidget(4, 1, new Label(constants.undefined()));
		
		thirdPageContent.setWidget(5, 0, new Label(constants.birthPlace()));
		
		int selectedPlaceIndex = birthPlaceField.getSelectedIndex();
		if(selectedPlaceIndex > 0)
			thirdPageContent.setWidget(5, 1, new Label(birthPlaceField.getItemText(selectedPlaceIndex)));
		else
			thirdPageContent.setWidget(5, 1, new Label(constants.undefined()));
		
		thirdPageContent.setWidget(6, 0, new Label(constants.gender()));
		
		if(maleRadioButton.getValue())
			thirdPageContent.setWidget(6, 1, new Label(constants.male()));
		else if(femaleRadioButton.getValue())
			thirdPageContent.setWidget(6, 1, new Label(constants.female()));
		else
			thirdPageContent.setWidget(6, 1, new Label(constants.undefined()));
		thirdPageContent.setWidget(7, 0, new Label(constants.email()));
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