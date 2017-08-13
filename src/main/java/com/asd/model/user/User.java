package com.asd.model.user;

import com.asd.model.common.Identifier;
import com.asd.model.location.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Identifier {

	private String userName;

	private Location location;

	private Group group;

}
