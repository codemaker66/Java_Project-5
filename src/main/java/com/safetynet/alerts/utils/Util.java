package com.safetynet.alerts.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class Util {

	/**
	 * This method convert string to date then check for it validity.
	 * 
	 * @param dateStr represent the date in string format.
	 * @return true if the date is valid.
	 */
	public boolean isValid(String dateStr) {

		DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		Date currentDate = new Date();
		Date enteredDate = null;
		boolean validity = true;

		try {
			enteredDate = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}

		if (enteredDate.after(currentDate)) {
			validity = false;
		}

		return validity;

	}

	/**
	 * This method convert the date in string format to local date.
	 * 
	 * @param birthdate represent the date in string format.
	 * @return the period between the date passed to the method and the actual date.
	 */
	public int toLocalDate(String birthdate) {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		Date date = null;
		LocalDate localDate = null;
		
		try {
			date = format.parse(birthdate);
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		LocalDate now = LocalDate.now();
		
		return Period.between(localDate, now).getYears();

	}

}
