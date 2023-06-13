package in.co.jk.services;

import java.util.List;

import in.co.jk.dto.FeedbackDTO;
import in.co.jk.exception.DuplicateRecordException;

public interface FeedbackServiceInt {

	public long add(FeedbackDTO dto) throws DuplicateRecordException;

	public void delete(FeedbackDTO dto);	

	public FeedbackDTO findBypk(long pk);

	public FeedbackDTO findByClassName(String login);

	public void update(FeedbackDTO dto) throws DuplicateRecordException;

	public List<FeedbackDTO> list();

	public List<FeedbackDTO> list(int pageNo, int pageSize);

	public List<FeedbackDTO> search(FeedbackDTO dto);

	public List<FeedbackDTO> search(FeedbackDTO dto, int pageNo, int pageSize);

	public FeedbackDTO findByServiceName(String service);

}
