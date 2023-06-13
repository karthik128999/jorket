package in.co.jk.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "j_user")
@Setter
@Getter
public class UserDTO extends BaseDTO{

	@Column(name = "first_name",length = 255)
	private String firstName;
	@Column(name = "last_name",length = 255)
	private String lastName;
	@Column(name = "login",length = 255)
	private String login;
	@Column(name = "contact_no",length = 255)
	private String contactNo;
	@Column(name = "dob",length = 255)
	private String dob;
	@Column(name = "gender",length = 255)
	private String gender;
	@Column(name = "zip_code",length = 255)
	private String zipCode;
	@Column(name = "address",length = 255)
	private String address;
	@Column(name = "password",length = 255)
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name = "skill_1",length = 255)
	private String skill1;
	@Column(name = "skill_2",length = 255)
	private String skill2;
	@Column(name = "skill_3",length = 255)
	private String skill_3;
	@Column(name = "skill_4",length = 255)
	private String skill_4;
	@Column(name = "skill_5",length = 255)
	private String skill_5;
	@Column(name = "role_id",length = 255)
	private Long roleId;
	@Column(name = "role_name",length = 255)
	private String roleName;
	@Column(name = "city",length = 255)
	private String city;
	@Column(name = "state", length = 255)
	private String state;
	private byte[] image;
	private byte[] kyc;
	@Column(name = "payment_link", length = 500)
	private String paymentLink;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<RequestServiceDTO> reqServiceList;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<AddToCartDTO> addToCartDTOList;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<FeedbackDTO> feedbackDTOList;
	
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
