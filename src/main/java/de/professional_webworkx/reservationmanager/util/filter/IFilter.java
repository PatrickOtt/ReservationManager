package de.professional_webworkx.reservationmanager.util.filter;

public interface IFilter<T> {

	public boolean accpet(T reservation);
}
