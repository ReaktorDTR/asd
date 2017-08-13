package com.asd.model.session;

import com.asd.model.common.Identifier;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request extends Identifier {

	private String url;

	private String method;

	private String params;

	private Session session;

}
