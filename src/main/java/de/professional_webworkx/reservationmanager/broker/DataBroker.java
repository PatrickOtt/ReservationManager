package de.professional_webworkx.reservationmanager.broker;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.professional_webworkx.reservationmanager.model.Reservation;

public class DataBroker {

	private static EntityManager em;
	
	private static DataBroker INSTANCE = new DataBroker();
	
	private DataBroker() {
		
	}
	
	public static DataBroker getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new DataBroker();
		}
		
		if(em == null)
			em = Persistence.createEntityManagerFactory("ReservationManagerPU").createEntityManager();
		
		return INSTANCE;
	}
	
	/**
	 * save a reservation
	 * @param reservation
	 */
	public void save(Reservation reservation) {
		em.getTransaction().begin();
		em.merge(reservation);
		em.getTransaction().commit();
	}
	
	/**
	 * Load all Reservations from Database
	 * @return List<Reservation>
	 */
	public List<Reservation> getAllReservations() {
		List<Reservation> result = new ArrayList<Reservation>();
		
		TypedQuery<Reservation> query = em.createNamedQuery(Reservation.GET_ALL_RESERVATIONS, Reservation.class);
		result = query.getResultList();
		return result;
	}
	
	public void close() {
		if(em != null) {
			em.close();
		}
	}
}
