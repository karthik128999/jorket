package in.co.jk.form;

import javax.validation.constraints.NotEmpty;

import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ForgotPasswordForm extends BaseForm {

	@NotEmpty(message = "Email is required")
	private String login;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setLogin(login);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)bDto;
		id = bean.getId();
		login = bean.getLogin();
	}

}
