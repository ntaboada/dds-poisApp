package dds.poi.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.DateTime;

import dds.poi.model.converter.DateTimeConverter;
import dds.poi.utils.DateUtils;

@Entity
public class Rango {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Convert(converter = DateTimeConverter.class)
	private DateTime dateTimeFrom;
	
	@Column
	@Convert(converter = DateTimeConverter.class)
	private DateTime dateTimeTo;

	public Rango() {
		this.dateTimeFrom = new DateTime();
		this.dateTimeTo = new DateTime();
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dateTimeFrom = this.dateTimeFrom.withDayOfWeek(dayOfWeek);
		this.dateTimeTo = this.dateTimeTo.withDayOfWeek(dayOfWeek);
	}

	public int getDayOfWeek() {
		return this.dateTimeFrom.getDayOfWeek();
	}

	public void setOpenHour(int openHour) {
		this.dateTimeFrom = this.dateTimeFrom.withHourOfDay(openHour);
	}

	public int getOpenHour() {
		return this.dateTimeFrom.getHourOfDay();
	}

	public void setOpenHourMinutes(int minutes) {
		this.dateTimeFrom = this.dateTimeFrom.withMinuteOfHour(minutes);
	}

	public int getOpenHourMinutes() {
		return this.dateTimeFrom.getMinuteOfHour();
	}

	public void setCloseHour(int closeHour) {
		this.dateTimeTo = this.dateTimeTo.withHourOfDay(closeHour);
	}

	public int getCloseHour() {
		return this.dateTimeTo.getHourOfDay();
	}

	public void setCloseHourMinutes(int minutes) {
		this.dateTimeTo = this.dateTimeTo.withMinuteOfHour(minutes);
	}

	public int getCloseHourMinutes() {
		return this.dateTimeTo.getMinuteOfHour();
	}

	public boolean incluyeElMomento(DateTime moment) {
		return DateUtils.momentIsInInterval(moment, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
