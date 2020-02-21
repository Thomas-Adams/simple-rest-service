package org.adams.cxf.rest.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.adams.cxf.rest.xml.DateXmlAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class BaseDTO<P extends Serializable, D extends Serializable, V extends Number> {

	@XmlTransient
	Class<D> clazz;

	@XmlElement(type=Long.class)
	private P id;

	@XmlElement(type=Date.class)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private D created;

	@XmlElement
	private String createdBy;

	@XmlElement(type=Date.class)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private D modified;

	@XmlElement
	private String modifiedBy;

	@XmlElement(type=Long.class)
	private V version;


}
