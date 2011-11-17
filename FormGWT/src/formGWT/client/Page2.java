package formGWT.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import formGWT.client.i18n.Texts;

public class Page2 extends Grid{
	private ListBox filmNameField = new ListBox();
	
	public Page2(int rows, int columns)
	{
		super(rows,columns);
		createPage();
	}
	
	/**
	 * Sets up the second page of the form.
	 */
	private void createPage() {
		Texts constants = GWT.create(Texts.class);
		//Form contents
		clear();
		setWidget(0, 0, new Label(constants.secondPage()));
		setWidget(1, 0, new Label(constants.filmName()));
		setWidget(1, 1, filmNameField);
	}
	
	public void updatePage() {
		Texts constants = GWT.create(Texts.class);
		//Form contents
		setWidget(0, 0, new Label(constants.secondPage()));
		setWidget(1, 0, new Label(constants.filmName()));
	}

}
