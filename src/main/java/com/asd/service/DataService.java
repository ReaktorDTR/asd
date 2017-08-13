package com.asd.service;

import com.asd.mapper.session.RequestMapper;
import com.asd.model.session.Request;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiresPermissions("data")
public class DataService {

	@Autowired
	private RequestMapper requestMapper;

	/**
	 * Returns list of user requests.
	 *
	 * @return list of user requests.
	 */
	public List<Request> getRequests(Integer limit) {
		return requestMapper.getRequests(limit);
	}

}
