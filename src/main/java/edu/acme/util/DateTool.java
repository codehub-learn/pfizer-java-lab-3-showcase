package edu.acme.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateTool {
	public static Date parseDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
		try {
			return new java.sql.Date(dateFormat.parse(date).getTime());
		} catch (ParseException e) {
			log.error("Date could not be parsed.", e);
		}
		return null;
	}
}
