package de.professional_webworkx.reservationmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main( String[] args )
    {
        Application.launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
		stage.setTitle("Hotel Reservation Manager v1.0");
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
}
