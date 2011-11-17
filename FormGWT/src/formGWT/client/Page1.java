package formGWT.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

import formGWT.client.i18n.TextTools;
import formGWT.client.i18n.Texts;



public class Page1 extends Grid{
		private TextBox nameField = new TextBox();
		private TextBox lastNameField = new TextBox();
		private TextBox birthField = new TextBox();
		private ListBox birthCountryField = new ListBox();
		private ListBox birthPlaceField = new ListBox();
		private RadioButton maleRadioButton = new RadioButton("gender");
		private RadioButton femaleRadioButton = new RadioButton("gender");
		private TextBox emailField = new TextBox();
		
		Texts constants;
		
		public Page1(int rows, int columns)
		{
			super(rows,columns);
			createPage();
		}
		
		/**
		 * Sets up the first page of the form.
		 */
		private void createPage() {
			constants = GWT.create(Texts.class);
			//Set up labels
			maleRadioButton.setText(constants.male());
			femaleRadioButton.setText(constants.female());
			//Form contents
			clear();
			setWidget(0, 0, new Label(constants.firstPage()));
			setWidget(1, 0, new Label(constants.name()));
			setWidget(1, 1, nameField);
			setWidget(2, 0, new Label(constants.lastName()));
			setWidget(2, 1, lastNameField);
			setWidget(3, 0, new Label(constants.dateOfBirth()));
			setWidget(3, 1, birthField);
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
			setWidget(4, 0, new Label(constants.birthCountry()));
			setWidget(4, 1, TextTools.fillCountries(birthCountryField));
			birthCountryField.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					int selectedItem = birthCountryField.getSelectedIndex();
					if(selectedItem < 0) return;
					birthPlaceField.clear();
					Texts constants = GWT.create(Texts.class);
					selectedItem--; //we take away the contribution of the empty string
					//Because the items are in alphabetical order, we use their index that wont change
					birthPlaceField.addItem("");
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
			setWidget(5, 0, new Label(constants.birthPlace()));
			setWidget(5, 1, birthPlaceField);
			//TODO Fire an event to make the default list linked to the countries list.
			setWidget(6, 0, maleRadioButton);
			setWidget(6, 1, femaleRadioButton);
			setWidget(7, 0, new Label(constants.email()));
			setWidget(7, 1, emailField);
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
		}
		
		public void updatePage() {
			//Set up labels
			maleRadioButton.setText(constants.male());
			femaleRadioButton.setText(constants.female());
			//Form contents
			setWidget(0, 0, new Label(constants.firstPage()));
			setWidget(1, 0, new Label(constants.name()));
			setWidget(2, 0, new Label(constants.lastName()));
			setWidget(3, 0, new Label(constants.dateOfBirth()));
			setWidget(4, 0, new Label(constants.birthCountry()));
			setWidget(5, 0, new Label(constants.birthPlace()));
			setWidget(7, 0, new Label(constants.email()));
		}
		
		public String getName()
		{
			return nameField.getText();
		}
		
		public String getLastName()
		{
			return lastNameField.getText();
		}
		
		public String getBirth()
		{
			return birthField.getText();
		}
		
		public String getBirthCountry()
		{
			int selectedCountryIndex = birthCountryField.getSelectedIndex();
			if(selectedCountryIndex > 0)
				return birthCountryField.getItemText(selectedCountryIndex);
			else
				return constants.undefined();
		}

		public String getBirthPlace()
		{
			int selectedPlaceIndex = birthPlaceField.getSelectedIndex();
			if(selectedPlaceIndex > 0)
				return birthPlaceField.getItemText(selectedPlaceIndex);
			else
				return constants.undefined();
		}
		
		public String getGender()
		{
			if(maleRadioButton.getValue())
				return constants.male();
			else if(femaleRadioButton.getValue())
				return constants.female();
			else
				return constants.undefined();
		}
		
		public String getEmail()
		{
			return emailField.getText();
		}
}
