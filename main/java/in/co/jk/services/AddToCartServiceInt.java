package in.co.jk.services;

import java.util.List;

import in.co.jk.dto.AddToCartDTO;
import in.co.jk.exception.DuplicateRecordException;

public interface AddToCartServiceInt {

	public long add(AddToCartDTO dto) throws DuplicateRecordException;

	public void delete(AddToCartDTO dto);	

	public AddToCartDTO findBypk(long pk);

	public AddToCartDTO findByClassName(String login);
	
	public void update(AddToCartDTO dto);
	
	List<AddToCartDTO> searchBooking(AddToCartDTO dto);

	List<AddToCartDTO> searchBooking(AddToCartDTO dto, int pageNo, int pageSize);

	public void accept(AddToCartDTO dto) throws DuplicateRecordException;

	public List<AddToCartDTO> list();

	public List<AddToCartDTO> list(int pageNo, int pageSize);

	public List<AddToCartDTO> search(AddToCartDTO dto);

	public List<AddToCartDTO> search(AddToCartDTO dto, int pageNo, int pageSize);

	public AddToCartDTO findByServiceName(String service);
	
	List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto);

	List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto, int pageNo, int pageSize);

	List<AddToCartDTO> showBookingsToCutomer(AddToCartDTO dto, int pageNo, int pageSize);

	List<AddToCartDTO> showBookingsToCustomer(AddToCartDTO dto);
	
	List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto);

	List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto, int pageNo, int pageSize);

}
