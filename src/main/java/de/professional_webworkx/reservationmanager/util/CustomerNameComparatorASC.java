package de.professional_webworkx.reservationmanager.util;

import java.util.Comparator;

import de.professional_webworkx.reservationmanager.model.Reservation;

/**
 * CustomerNameComparatorASC
 * sort the names of customers in ASC order
 * @author ottp
 *
 */
public class CustomerNameComparatorASC implements Comparator<Reservation> {

	public int compare(Reservation o1, Reservation o2) {
		
		int ret = o1.getCustomer().compareTo(o2.getCustomer());
		
		if(ret < 0) 
			return -1;
		if(ret > 0) 
			return 1;
		else
			return 0;
	}

}
