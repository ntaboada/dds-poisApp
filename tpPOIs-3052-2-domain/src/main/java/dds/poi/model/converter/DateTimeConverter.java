package dds.poi.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Converter
public class DateTimeConverter implements AttributeConverter<DateTime, String> {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("MM/dd/yyyy");

	@Override
	public String convertToDatabaseColumn(DateTime attribute) {
		return DATE_TIME_FORMATTER.print(attribute);
	}

	@Override
	public DateTime convertToEntityAttribute(String dbData) {
		return DATE_TIME_FORMATTER.parseDateTime(dbData);
	}

}
