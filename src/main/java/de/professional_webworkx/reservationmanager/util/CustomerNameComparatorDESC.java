package de.professional_webworkx.reservationmanager.util;

import java.util.Comparator;

import de.professional_webworkx.reservationmanager.model.Reservation;

/**
 * CustomerNameComparatorDESC
 * sort the names of customes in DESC Order
 * @author ottp
 *
 */
public class CustomerNameComparatorDESC implements Comparator<Reservation>{

	public int compare(Reservation o1, Reservation o2) {
		
		int ret = o1.getCustomer().compareTo(o2.getCustomer());
		
		if(ret < 0) 
			return 1;
		if(ret > 0) 
			return -1;
		else
			return 0;
	}

}
