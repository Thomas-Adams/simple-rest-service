package org.adams.cxf.rest.dto;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class UserDTO extends BaseDTO<Long, Date, Long> {

	@XmlElement
	private String username;

	@XmlElement
	private String password;

	@XmlElement
	private boolean enabled;

	@XmlElement
	private boolean expired;

	@XmlElement
	private boolean credentialsExpired;

	@XmlElement
	private boolean locked;

	@XmlElement
	private String email;

	@XmlElement
	private String phone;

	@XmlElement
	private String mobile;

	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	public boolean isAccountNonLocked() {
		return !locked;
	}

	public boolean isAccountNonExpired() {
		return !expired;
	}

}
