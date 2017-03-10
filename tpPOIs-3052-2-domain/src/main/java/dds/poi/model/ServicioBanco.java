package dds.poi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.joda.time.DateTime;

public class ServicioBanco extends Servicio {

	private static final ArrayList<Integer> diasDisponibles = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
	private static final int horaDesdeDisponible = 10;
	private static final int minutoDesdeDisponible = 0;
	private static final int horaHastaDisponible = 15;
	private static final int minutoHastaDisponible = 15;

	@Override
	public boolean estaDisponible(DateTime momento) {
		Predicate<Integer> predicate = dia -> dia == momento.getDayOfWeek();
		boolean incluyeElMomento = diasDisponibles.stream().anyMatch(predicate);
		incluyeElMomento &= momento.getHourOfDay() >= horaDesdeDisponible && momento.getHourOfDay() <= horaHastaDisponible;
		incluyeElMomento &= momento.getMinuteOfHour() >= minutoDesdeDisponible && momento.getMinuteOfHour() <= minutoHastaDisponible;		
		return incluyeElMomento;
	}

}
