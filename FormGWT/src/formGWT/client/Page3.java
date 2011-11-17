package formGWT.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

import formGWT.client.i18n.Texts;

public class Page3 extends Grid{
	//Third Page
	Page1 page1;
	Texts constants;
	
	public Page3(int rows, int columns, Page1 page)
	{
		super(rows,columns);
		page1 = page;
		createPage();

	}
	
	/**
	 * Sets up the first page of the form.
	 */
	private void createPage() {
		constants = GWT.create(Texts.class);
		//Form contents
		clear();
		setWidget(0, 0, new Label(constants.thirdPage()));
		setWidget(1, 0, new Label(constants.name()));
		setWidget(1, 1, new Label(page1.getName()));
		setWidget(2, 0, new Label(constants.lastName()));
		setWidget(2, 1, new Label(page1.getLastName()));
		setWidget(3, 0, new Label(constants.dateOfBirth()));
		setWidget(3, 1, new Label(page1.getBirth()));
		setWidget(4, 0, new Label(constants.birthCountry()));
		setWidget(4, 1, new Label(page1.getBirthCountry()));
		setWidget(5, 0, new Label(constants.birthPlace()));
		setWidget(5, 1, new Label(page1.getBirthPlace()));
		setWidget(6, 0, new Label(constants.gender()));
		setWidget(6, 1, new Label(page1.getGender()));
		setWidget(7, 0, new Label(constants.email()));
		setWidget(7, 1, new Label(page1.getEmail()));		
	}
	
	public void updatePage() {
		//Form contents
		setWidget(0, 0, new Label(constants.thirdPage()));
		setWidget(1, 0, new Label(constants.name()));
		setWidget(1, 1, new Label(page1.getName()));
		setWidget(2, 0, new Label(constants.lastName()));
		setWidget(2, 1, new Label(page1.getLastName()));
		setWidget(3, 0, new Label(constants.dateOfBirth()));
		setWidget(3, 1, new Label(page1.getBirth()));
		setWidget(4, 0, new Label(constants.birthCountry()));
		setWidget(4, 1, new Label(page1.getBirthCountry()));
		setWidget(5, 0, new Label(constants.birthPlace()));
		setWidget(5, 1, new Label(page1.getBirthPlace()));
		setWidget(6, 0, new Label(constants.gender()));
		setWidget(6, 1, new Label(page1.getGender()));
		setWidget(7, 0, new Label(constants.email()));
		setWidget(7, 1, new Label(page1.getEmail()));		
	}
}
