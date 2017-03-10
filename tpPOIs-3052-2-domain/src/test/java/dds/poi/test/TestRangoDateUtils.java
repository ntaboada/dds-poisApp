package dds.poi.test;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.poi.builder.RangoBuilder;
import dds.poi.model.Rango;
import dds.poi.utils.DateUtils;

public class TestRangoDateUtils {

	Rango rangoDeFechaPrueba;

	@Before
	public void init() {
		rangoDeFechaPrueba = new RangoBuilder().dia(1).horaDesde(10)
				.minutosDesde(0).horaHasta(12).minutosHasta(30).build();
	}

	@Test
	public void testMomentoDentroRangoConUtils() {
		DateTime dateTime = new DateTime(2016, 4, 18, 11, 30);
		boolean estaDentro = DateUtils.momentIsInInterval(dateTime,
				rangoDeFechaPrueba);
		Assert.assertTrue(estaDentro);
	}

	@Test
	public void testMomentoFueraRangoConUtils() {
		DateTime dateTime = new DateTime(2016, 4, 19, 11, 30);
		boolean estaDentro = DateUtils.momentIsInInterval(dateTime,
				rangoDeFechaPrueba);
		Assert.assertFalse(estaDentro);
	}
}
