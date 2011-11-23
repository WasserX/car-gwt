package formGWT.client.i18n;

import com.google.gwt.core.client.GWT;

public class TextTools {

	/**
	 * Fills a listBox with the countries included in the internationalization
	 * files. If the component is not empty, it appends the countries to the current elements
	 * 
	 * @param inputList
	 *            List to be modified
	 * @return the modified list
	 */
	public static com.google.gwt.user.client.ui.ListBox fillCountries(
			com.google.gwt.user.client.ui.ListBox inputList) {
		Texts constants = GWT.create(Texts.class);
		inputList.addItem("");
		inputList.addItem(constants.argentina());
		inputList.addItem(constants.brazil());
		inputList.addItem(constants.france());
		inputList.addItem(constants.italy());
		inputList.addItem(constants.usa());

		return inputList;
	}
	
	public static com.google.gwt.user.client.ui.ListBox fillMovies(
			com.google.gwt.user.client.ui.ListBox inputList) {
		Texts constants = GWT.create(Texts.class);
		inputList.addItem("");
		inputList.addItem(constants.lordOfTheRings());
		inputList.addItem(constants.hangover());
		inputList.addItem(constants.tron());
		return inputList;
	}

}
