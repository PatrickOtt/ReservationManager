package de.professional_webworkx.reservationmanager.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import de.professional_webworkx.reservationmanager.broker.DataBroker;
import de.professional_webworkx.reservationmanager.model.Reservation;
import de.professional_webworkx.reservationmanager.util.CustomerNameComparatorASC;
import de.professional_webworkx.reservationmanager.util.CustomerNameComparatorDESC;

public class MainController implements Initializable {

	@FXML
	private TextField name;
	
	@FXML
	private DatePicker checkIn;
	
	@FXML
	private DatePicker checkOut;
	
	@FXML
	private Button reserveBtn;
	
	@FXML
	private Button resetBtn;
	
	// Menu
	@FXML
	private MenuItem exitMenuItem;
	
	@FXML
	private MenuItem sortASC;
	
	@FXML
	private MenuItem sortDESC;
	
	// Reservation Table
	@FXML
	private TableView<Reservation> reservationTable;
	
	@FXML
	private TableColumn<Reservation, IntegerProperty> reservationId;
	
	@FXML
	private TableColumn<Reservation, StringProperty> reservationCustomer;
	
	@FXML
	private TableColumn<Reservation, ObjectProperty<LocalDate>> reservationCheckIn;
	
	@FXML
	private TableColumn<Reservation, ObjectProperty<LocalDate>> reservationCheckOut;
	
	@FXML
	private TableColumn<Reservation, BooleanProperty> reservationApproved;
	
	
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	private DataBroker dataBroker;
	
	public void initialize(URL location, ResourceBundle rb) {
		
		dataBroker = DataBroker.getInstance();
		List<Reservation> allReservations = dataBroker.getAllReservations();
		reservationTable.getItems().addAll(allReservations);
		
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				// reset input field name
				name.clear();
				
				// reset the datepicker
				checkIn.setValue(null);
				checkOut.setValue(null);
			}
		});
		
		reserveBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				String customerName = name.getText();
				if(customerName != null && !customerName.trim().isEmpty()) {
					
					org.joda.time.LocalDate checkInDate = new org.joda.time.LocalDate(checkIn.getValue().getYear(), checkIn.getValue().getMonth().getValue(), checkIn.getValue().getDayOfMonth());
					org.joda.time.LocalDate checkOutDate = new org.joda.time.LocalDate(checkIn.getValue().getYear(), checkIn.getValue().getMonth().getValue(), checkIn.getValue().getDayOfMonth());
					
					Reservation reservation = new Reservation(customerName, checkInDate, checkOutDate);
					reservations.add(reservation);
					reservationTable.getItems().add(reservation);
					
					dataBroker.save(reservation);
				}
			}
		});
		
		reservationId.setCellValueFactory(new PropertyValueFactory<Reservation, IntegerProperty>("reservationId"));
		
		reservationCustomer.setCellValueFactory(new PropertyValueFactory<Reservation, StringProperty>("customer"));
		
		reservationCheckIn.setCellValueFactory(new PropertyValueFactory<Reservation, ObjectProperty<LocalDate>>("checkIn"));
		
		reservationCheckOut.setCellValueFactory(new PropertyValueFactory<Reservation, ObjectProperty<LocalDate>>("checkOut"));
		
		reservationApproved.setCellValueFactory(new PropertyValueFactory<Reservation, BooleanProperty>("reservationApproved"));

		sortASC.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				ObservableList<Reservation> items = reservationTable.getItems();
				Collections.sort(items, new CustomerNameComparatorASC());
			}
		});
		
		sortDESC.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				// get all entries from our table
				ObservableList<Reservation> items = reservationTable.getItems();
				
				// sort them
				Collections.sort(items, new CustomerNameComparatorDESC());
			}
		});
		
		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				dataBroker.close();
				System.exit(0);
			}
		});
	}

}
