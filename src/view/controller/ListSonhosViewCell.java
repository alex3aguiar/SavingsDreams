package view.controller;

import javafx.scene.control.ListCell;
import model.Sonho;

public class ListSonhosViewCell extends ListCell<Sonho> {

	@Override
	public void updateItem(Sonho sonho, boolean empty) {
		super.updateItem(sonho, empty);
		if (sonho != null) {
			SonhoFXController sonhoFXController = new SonhoFXController();
			sonhoFXController.setSonho(sonho);
			setGraphic(sonhoFXController.getLayout());
		}
	}
}
