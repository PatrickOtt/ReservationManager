package de.professional_webworkx.reservationmanager.util.filter;

import de.professional_webworkx.reservationmanager.model.Reservation;

public class ReservationIDFilter implements IFilter<Reservation> {

	private Integer acceptedResId;
	
	public ReservationIDFilter(final String query) {
		
		try {
			this.acceptedResId = Integer.parseInt(query);
		}catch (NumberFormatException e) {
			System.err.println("Parse exception");
		}
	}
	public boolean accpet(Reservation reservation) {
		return new Integer(reservation.getReservationId()).equals(acceptedResId);
	}

}
