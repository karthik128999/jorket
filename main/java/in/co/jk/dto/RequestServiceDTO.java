package in.co.jk.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "j_request")
@Setter
@Getter
public class RequestServiceDTO extends BaseDTO{

	private BigDecimal costPerHour;
	
	private String timeSlotsStart;
	
	private String timeSlotsEnds;
	
	private byte[] image;
	
	@Column(name = "booking_date",length = 255)
	private String dateOfBooking;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user", nullable = false)
	private UserDTO user;
	
	private Long userId;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "service")
//	private ServicesDTO service;
//	
//	private Long serviceId;
	
	@Column(name = "service_name", length = 255)
	private String serviceName;
	
	@Column(name = "city", length = 255)
	private String city;
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
