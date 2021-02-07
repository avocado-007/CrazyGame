package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Figur implements Serializable {
	
	private boolean iswhite;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6609164339030703477L;
	
	public Figur(char c) {
		iswhite = java.lang.Character.isUpperCase(c);
	}
	
	public Figur(boolean iswhite) {
		this.iswhite = iswhite;
	}
	
	public boolean isWhite(){
		return iswhite;
	}
	public void setIsWhite(boolean iswhite) {
		this.iswhite = iswhite;
	}
	
	
	public String getPossibleMoves() {
		return "";
	}
}
