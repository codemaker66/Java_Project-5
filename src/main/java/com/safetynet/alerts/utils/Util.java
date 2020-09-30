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

	public boolean isValid(String dateStr) {

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		Date currentDate = new Date();
		Date enteredDate = null;
		boolean valid = true;

		try {
			enteredDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}

		if (enteredDate.after(currentDate)) {
			valid = false;
		}

		return valid;

	}

	public int toLocalDate(String birthdate) {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		Date date = null;
		LocalDate local = null;
		try {
			date = format.parse(birthdate);
			local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LocalDate now = LocalDate.now();
		return Period.between(local, now).getYears();

	}

}
