package com.sbz.projekat.SBZProjekat.simulation;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
public class OxigenLevelEvent implements Serializable{
	private static final long serialVersionUID = 1L;

	private int level;
	
	public OxigenLevelEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OxigenLevelEvent(int level) {
		super();
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
