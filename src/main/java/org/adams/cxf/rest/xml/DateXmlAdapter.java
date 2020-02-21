package org.adams.cxf.rest.xml;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.format.DateTimeFormat;

public class DateXmlAdapter extends XmlAdapter<String, Date> {

	private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public String marshal(final Date v) throws Exception {
		if (v != null) {
			return DateTimeFormat.forPattern(DATE_FORMAT_PATTERN).print(v.getTime());
		} else {
			return "";
		}
	}

	@Override
	public Date unmarshal(final String v) throws Exception {
		if (v != null) {
			return DateTimeFormat.forPattern(DATE_FORMAT_PATTERN).parseDateTime(v).toDate();
		} else {
			return null;
		}
	}

}
