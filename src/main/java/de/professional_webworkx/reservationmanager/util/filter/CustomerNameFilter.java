package de.professional_webworkx.reservationmanager.util.filter;

import de.professional_webworkx.reservationmanager.model.Reservation;

public class CustomerNameFilter implements IFilter<Reservation> {

	private String acceptedName;
	
	public CustomerNameFilter(final String query) {
		this.acceptedName	= query;
	}
	
	public boolean accpet(Reservation reservation) {
		
		return reservation.getCustomer().contains(acceptedName);
	}

}
