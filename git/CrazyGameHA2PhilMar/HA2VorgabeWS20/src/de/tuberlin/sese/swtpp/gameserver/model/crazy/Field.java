package de.tuberlin.sese.swtpp.gameserver.model.crazy;

import java.io.Serializable;

public class Field implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -919180649009130113L;
	
	
	public int x;
	public int y;
	public Figur f;
	
	public Field(int x, int y, Figur f) {
		this.x = x;
		this.y = y;
		this.f = f;
	}
}
