package in.co.jk.form;

import javax.validation.constraints.NotEmpty;

import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class LoginForm extends BaseForm{
	@NotEmpty(message = "Login is required")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setLogin(login);
		bean.setPassword(password);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)getDTO();
		login = bean.getLogin();
		password = bean.getPassword();
	}

}
