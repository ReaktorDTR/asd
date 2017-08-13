package com.asd.model.location;

import com.asd.model.common.Identifier;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location extends Identifier {

	private String locationName;

	private Country country;

	private double latitude;

	private double longitude;

}
