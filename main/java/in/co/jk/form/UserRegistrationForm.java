package in.co.jk.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$", message = "First Name is Invalid")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$", message = "Last Name is Invalid")
	private String lastName;
	@NotEmpty(message = "Email is required")
	@Pattern(regexp="(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$",message = "Email id is invalid")
	private String login;
	@NotEmpty(message = "Contact No is required")
	@Pattern(regexp="(^[1-9][0-9]{9})*$",message = "Contact No is Invalid")
	private String contactNo;
	@NotEmpty(message = "Date Of Birth is required")
	private String dob;
	@NotEmpty(message = "Gender is required")
	private String gender;
	@NotEmpty(message = "Zip Code is required")
	@Pattern(regexp="(^[0-9]{5})*$",message = "Zip Code is Invalid")
	private String zipCode;
	@NotEmpty(message = "Address is required")
	private String address;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;
	@NotEmpty(message = "Skill is required")
	private String skill1;
	@NotEmpty(message = "Skill is required")
	private String skill2;
	@NotEmpty(message = "Skill is required")
	private String skill_3;
	@NotEmpty(message = "Skill is required")
	private String skill_4;
	@NotEmpty(message = "Skill is required")
	private String skill_5;

	private Long roleId;

	private String roleName;
	@NotEmpty(message = "City is required")
	private String city;
	@NotEmpty(message = "State is required")
	private String state;
	
	private MultipartFile image;
	
	private MultipartFile kyc;
	
	@NotEmpty(message = "Payment Link is required")
	private String paymentLink;

	@Override
	public BaseDTO getDTO() {
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setAddress(address);
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setConfirmPassword(confirmPassword);
		bean.setContactNo(contactNo);
		bean.setDob(dob);
		bean.setRoleId(roleId);
		bean.setRoleName(roleName);
		bean.setSkill1(skill1);
		bean.setSkill2(skill2);
		bean.setSkill_3(skill_3);
		bean.setSkill_4(skill_4);
		bean.setSkill_5(skill_5);		
		bean.setGender(gender);
		bean.setZipCode(zipCode);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCity(city);
		bean.setState(state);
		bean.setPaymentLink(paymentLink);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		UserDTO bean = (UserDTO)bDto;
		id = bean.getId();
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		gender = bean.getGender();
		address = bean.getAddress();
		zipCode = bean.getZipCode();
		dob = bean.getDob();
		login = bean.getLogin();
		skill1 = bean.getSkill1();
		skill2 = bean.getSkill2();
		skill_3 = bean.getSkill_3();
		skill_4 = bean.getSkill_4();
		skill_4 = bean.getSkill_4();
		skill_5 = bean.getSkill_5();
		contactNo = bean.getContactNo();
		password = bean.getPassword();
		confirmPassword = bean.getConfirmPassword();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
		city = bean.getCity();
		state = bean.getState();
		paymentLink = bean.getPaymentLink();
	}

}
