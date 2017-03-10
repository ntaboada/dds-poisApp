package dds.poi.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.uqbar.geodds.Point;

@Converter
public class PointConverter implements AttributeConverter<Point, String>{

	 private static final String SEPARATOR = "&";

	@Override
	public String convertToDatabaseColumn(Point attribute) {
		  StringBuilder sb = new StringBuilder();
		  sb.append(attribute.getX()).append(SEPARATOR)
		     .append(attribute.getY());
		  return sb.toString();
	}

	@Override
	public Point convertToEntityAttribute(String dbData) {
		  String[] point = dbData.split(SEPARATOR);
		  return new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
	}

}
