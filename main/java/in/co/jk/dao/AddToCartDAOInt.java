package in.co.jk.dao;

import java.util.List;

import in.co.jk.dto.AddToCartDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.dto.AddToCartDTO;


public interface AddToCartDAOInt {
	public long add(AddToCartDTO dto);

	public void delete(AddToCartDTO dto);

	public AddToCartDTO findBypk(long pk);

	public AddToCartDTO findByServiceName(String login);

	public void accept(AddToCartDTO dto);
	
	public void update(AddToCartDTO dto);

	public List<AddToCartDTO> list();

	public List<AddToCartDTO> list(int pageNo, int pageSize);

	public List<AddToCartDTO> search(AddToCartDTO dto);

	public List<AddToCartDTO> search(AddToCartDTO dto, int pageNo, int pageSize);

	List<AddToCartDTO> searchBooking(AddToCartDTO dto);

	List<AddToCartDTO> searchBooking(AddToCartDTO dto, int pageNo, int pageSize);
 
	List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto);

	List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto, int pageNo, int pageSize);
	
	List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto);

	List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto, int pageNo, int pageSize);

	List<AddToCartDTO> showBookingsToCustomer(AddToCartDTO dto);

	List<AddToCartDTO> showBookingsToCustomer(AddToCartDTO dto, int pageNo, int pageSize);

	

}
