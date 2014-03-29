package de.professional_webworkx.reservationmanager.model;

import java.io.Serializable;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

import org.joda.time.LocalDate;

/**
 * Entity implementation class for Entity: Reservation
 *
 */

// namedQueries
@NamedQueries({
	@NamedQuery(name = Reservation.GET_ALL_RESERVATIONS, query = "SELECT r FROM Reservation r")
})
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

	public static final String GET_ALL_RESERVATIONS		= "Reservation.get_all_reservations";
	
	private static final long serialVersionUID = 1L;
	private long id;
	
	/*
	private String customer;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private boolean reservationApproved;
	*/
	
	// Properties
	/**
	 * ReservationsID
	 */
	private int reservationId;
	
	/**
	 * Customer 
	 */
	private String customer;
	
	/**
	 * CheckIn
	 */
	private LocalDate checkIn;

	/**
	 * CheckOut
	 */
	private LocalDate checkOut;
	
	/**
	 * Reservation approved?
	 */
	private boolean reservationApproved;
	
	
	
	public Reservation() {
		super();
	}
	
	
	
	public Reservation(String customer, LocalDate checkIn, LocalDate checkOut) {
		super();
		this.reservationId = generateReservationId(System.currentTimeMillis());
		this.customer = customer;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservationApproved = false;
	}



//	public Reservation(String customerName, LocalDate checkInDate, LocalDate checkOutDate) {
//		
//		this.reservationId.set(generateReservationId(System.currentTimeMillis()));
//		this.customer.set(customerName);
//		this.checkIn.set(checkInDate);
//		this.checkOut.set(checkOutDate);
//		this.reservationApproved.set(false);
//	
//	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(final long id) {
		this.id = id;
	}
	
	
	
	/**
	 * Get ReservationId's value
	 */
	public int getReservationId() {
		return reservationId;
	}
	
	/**
	 * set a new reservationId
	 */
	public void setReservationId(final int reservationId) {
		this.reservationId = reservationId;
	}
   
	
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	

	public LocalDate getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public boolean isReservationApproved() {
		return reservationApproved;
	}

	public void setReservationApproved(boolean reservationApproved) {
		this.reservationApproved = reservationApproved;
	}

	
	/**
	 * get reservationId Property
	 */
	@Transient
	public IntegerProperty getReservationIdProperty() {
		return new SimpleIntegerProperty(reservationId);
	}
	@Transient
	public StringProperty getCustomerProperty() {
		return new SimpleStringProperty(customer);
	}
	
	@Transient
	public ObjectProperty<LocalDate> getCheckInProperty() {
		return new SimpleObjectProperty<LocalDate>(checkIn);
	}
	
	@Transient
	public ObjectProperty<LocalDate> getCheckOutProperty() {
		return new SimpleObjectProperty<LocalDate>(checkOut);
	}
	
	@Transient
	public BooleanProperty getReservationApprovedProperty() {
		return new SimpleBooleanProperty(reservationApproved);
	}

	/**
	 * Generates a random <b>reservationId</b>
	 * This will be printed on the booking confirmation of the customer
	 * @param currentTimeMillis
	 * @return
	 */
	private int generateReservationId(final long currentTimeMillis) {
		Random random = new Random(currentTimeMillis);
		int nextInt = random.nextInt();
		return (nextInt < 0 ? (nextInt*-1) : nextInt);
	}
	
}
