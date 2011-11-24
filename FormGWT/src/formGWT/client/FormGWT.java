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
public class FormGWT implements EntryPoint, ClickHandler {
	
	private Button prevButton = new Button();
	private Button nextButton = new Button();
	private int PAGE = 1;
	
	private Page1 page1;
	private Page2 page2;
	private Page3 page3;
	private Page4 page4;
	

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
			page1.updatePage();
			RootPanel.get().add(page1);
			break;
		case 2:
			prevButton.setEnabled(true);
			nextButton.setEnabled(true);
			page2.updatePage();
			RootPanel.get().add(page2);
			break;
		case 3:
			prevButton.setEnabled(true);
			nextButton.setEnabled(true);
			page3.updatePage();
			RootPanel.get().add(page3);
			break;
		case 4:
			prevButton.setEnabled(true);
			nextButton.setEnabled(false);
			page4.updatePage();
			RootPanel.get().add(page4);
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

	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource() == prevButton)
		{
			switch(PAGE){
			case 1:
				break;
			case 2:
				PAGE--;
				RootPanel.get().remove(page2);
				break;
			case 3:
				PAGE--;
				RootPanel.get().remove(page3);
				break;
			case 4:
				PAGE--;
				RootPanel.get().remove(page4);
				break;
			}		
		}
		else //nextButton
		{
			switch(PAGE){
			case 1:
				PAGE++;
				RootPanel.get().remove(page1);
				break;
			case 2:
				PAGE++;
				RootPanel.get().remove(page2);
				break;
			case 3:
				PAGE++;
				RootPanel.get().remove(page3);
				break;
			case 4:
				break;
			}	
		}
		updateView();		
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		PAGE = 1;
		
		prevButton.addClickHandler(this);
		nextButton.addClickHandler(this);
		
		page1 = new Page1(9,2);
		page2 = new Page2(2,1);
		page3 = new Page3(9,2,page1);
		page4 = new Page4(2,1);
		
		updateView();
	}
}