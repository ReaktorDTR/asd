package com.asd.model.session;

import com.asd.model.common.Identifier;
import com.asd.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Session extends Identifier {

	private User user;

	private Timestamp dateOpened;

	private Timestamp dateClosed;

}
