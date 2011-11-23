package formGWT.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import formGWT.client.i18n.TextTools;
import formGWT.client.i18n.Texts;

public class Page2 extends Grid{
	private final String lordOfTheRingsURL = "http://ia.media-imdb.com/images/M/MV5BMjE4MjA1NTAyMV5BMl5BanBnXkFtZTcwNzM1NDQyMQ@@._V1_.jpg";
	private final String hangoverURL = "http://ia.media-imdb.com/images/M/MV5BMTU1MDA1MTYwMF5BMl5BanBnXkFtZTcwMDcxMzA1Mg@@._V1_.jpg";
	private final String tronURL = "http://ia.media-imdb.com/images/M/MV5BMTk4NTk4MTk1OF5BMl5BanBnXkFtZTcwNTE2MDIwNA@@._V1_.jpg";
	
	private Grid topGrid = new Grid(2,2);
	private ListBox filmNameField = new ListBox();
	private Image filmImage = new Image();
	
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
		topGrid.clear();
		
		filmImage.setSize("700px", "500px");
		filmImage.setVisible(false);
		
		topGrid.setWidget(0, 0, new Label(constants.secondPage()));
		topGrid.setWidget(1, 0, new Label(constants.filmName()));
		topGrid.setWidget(1, 1, TextTools.fillMovies(filmNameField));
		filmNameField.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selectedItem = filmNameField.getSelectedIndex();
				if(selectedItem < 0) return;
				//Texts constants = GWT.create(Texts.class);
				selectedItem--; //we take away the contribution of the empty string
				//Because the items are in alphabetical order, we use their index that wont change
				switch(selectedItem){
				case 0: //Lord of the Rings
					filmImage.setVisible(true);
					filmImage.setUrl(lordOfTheRingsURL);
					break;
				case 1: //Hangover
					filmImage.setVisible(true);
					filmImage.setUrl(hangoverURL);
					break;
				case 2: //Tron
					filmImage.setVisible(true);
					filmImage.setUrl(tronURL);
					break;
				default:
					filmImage.setVisible(false);
					break;
				}
			}
		});
		setWidget(0,0, topGrid);
		setWidget(1,0, filmImage);
	}
	
	public void updatePage() {
		Texts constants = GWT.create(Texts.class);
		//Form contents
		topGrid.setWidget(0, 0, new Label(constants.secondPage()));
		topGrid.setWidget(1, 0, new Label(constants.filmName()));
	}

}
