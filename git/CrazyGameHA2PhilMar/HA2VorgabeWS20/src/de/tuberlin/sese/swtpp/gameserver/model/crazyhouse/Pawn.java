package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.util.Arrays;
import java.util.List;

import de.tuberlin.sese.swtpp.gameserver.model.Move;


public class Pawn extends Figur{

	public Pawn(boolean iswhite) {
		super(iswhite);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6056632164334304318L;
	

	
	public List< TupelPos<Integer,Integer>> getPossibleMoves(Move move) {
		int x = move.getMove().charAt(0) - 'a' + 1;
		int y = move.getMove().charAt(1);
		List <TupelPos<Integer, Integer>> result = Arrays.asList(new TupelPos<Integer, Integer>(x, y+1));
		if (y == 2)
			result.add(new TupelPos<Integer, Integer>(x, y+2));
		if (x > 1)
			result.add(new TupelPos<Integer, Integer>(x-1, y+1));
		if (x < 8)
			result.add(new TupelPos<Integer, Integer>(x+1, y+1));
		return result;
		
		}
	public boolean isValid(Move move) {
		List <TupelPos<Integer, Integer>> result = getPossibleMoves(move);
		int x = move.getMove().charAt(3) - 'a' + 1;
		int y = move.getMove().charAt(4);
		return false;
		
	}
}
