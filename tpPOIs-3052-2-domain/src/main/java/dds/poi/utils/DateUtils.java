package dds.poi.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import dds.poi.model.Rango;

public class DateUtils {

	public static boolean momentIsInInterval(DateTime moment, Rango interval) {
		boolean incluyeElMomento = moment.getDayOfWeek() == interval
				.getDayOfWeek();
		incluyeElMomento &= moment.getHourOfDay() >= interval.getOpenHour()
				&& moment.getHourOfDay() <= interval.getCloseHour();
		if (incluyeElMomento) {
			boolean minutosEnIntervalo = moment.getMinuteOfHour() >= interval
					.getOpenHourMinutes()
					&& moment.getMinuteOfHour() <= interval
							.getCloseHourMinutes();
			if (!minutosEnIntervalo) {
				incluyeElMomento &= moment.getHourOfDay() < interval
						.getCloseHour();
			}
		}
		return incluyeElMomento;
	}
	
	public static String dateTimeToString(DateTime date) {
		DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("YYYY/MM/dd");
		return dateTimeFormat.print(date);
	}

}
