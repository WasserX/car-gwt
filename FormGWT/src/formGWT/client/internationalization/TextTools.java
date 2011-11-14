package formGWT.client.internationalization;

import com.google.gwt.core.client.GWT;

public class TextTools {

	private static Texts constants = GWT.create(Texts.class);

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
		inputList.addItem(constants.argentina());
		inputList.addItem(constants.brazil());
		inputList.addItem(constants.france());
		inputList.addItem(constants.italy());
		inputList.addItem(constants.usa());

		return inputList;
	}

}
