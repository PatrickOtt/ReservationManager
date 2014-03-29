package de.professional_webworkx.reservationmanager.util.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * We need this to apply our different filters.
 * @author ottp
 *
 * @param <T>
 */
public class FilterUtils {

	public static <T> List<T> applyFilter(final List<T> list, final IFilter<T> filter) {
		
		List<T> filteredList = new ArrayList<T>();
		for(T currentValue : list) {
			if(filter.accpet(currentValue)) {
				filteredList.add(currentValue);
			}
		}
		return filteredList;
	}
}
