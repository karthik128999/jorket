package in.co.jk.dao;

import java.util.List;

import in.co.jk.dto.FeedbackDTO;
import in.co.jk.dto.FeedbackDTO;


public interface FeedbackDAOInt {
	public long add(FeedbackDTO dto);

	public void delete(FeedbackDTO dto);

	public FeedbackDTO findBypk(long pk);

	public FeedbackDTO findByServiceName(String login);

	public void update(FeedbackDTO dto);

	public List<FeedbackDTO> list();

	public List<FeedbackDTO> list(int pageNo, int pageSize);

	public List<FeedbackDTO> search(FeedbackDTO dto);

	public List<FeedbackDTO> search(FeedbackDTO dto, int pageNo, int pageSize);

	

}
