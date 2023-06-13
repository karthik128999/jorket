package in.co.jk.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "j_service")
@Setter
@Getter
public class ServicesDTO extends BaseDTO {

	private String serviceName;
	
	private byte[] serviceImage;
	
	private String city;
	
//	@OneToMany(mappedBy = "service", cascade = { CascadeType.ALL })
//	private List<RequestServiceDTO> reqServiceList;
	
//	@OneToMany(mappedBy = "service", cascade = { CascadeType.ALL })
//	private List<AddToCartDTO> addToCartDTOList;
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
