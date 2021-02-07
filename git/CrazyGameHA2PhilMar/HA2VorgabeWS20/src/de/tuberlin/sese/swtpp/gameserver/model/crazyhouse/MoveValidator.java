package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.util.Arrays;
import java.util.List;

import de.tuberlin.sese.swtpp.gameserver.model.Move;

public class MoveValidator {
	
	public List<TupelPos> getPossibleMoves(String board, Move move ){
		TupelPos location = getStartPosition(move);
		char figure = getFigure(location, board);
		switch (Character.toLowerCase(figure)) {
			case 'p': break;
			case 'k': break;
			case 'q': break;
			case 'b': break;
			case 'n': break;
			case 'r': break;
		}
		return null;
		
	}

	public TupelPos getStartPosition(Move move){
		int x = move.getMove().charAt(0) - 'a' + 1;
		int y = move.getMove().charAt(1);
		return new TupelPos(x, y);
	}
	
	public TupelPos getFinishPosition(Move move){
		int x = move.getMove().charAt(3) - 'a' + 1;
		int y = move.getMove().charAt(4);
		return new TupelPos(x, y);
	}
	
	public char getFigure(TupelPos location,  String board) {
		return getFormatedString(board).split("/")[location.x].charAt(location.y);
	}
	
	public String getFormatedString(String str) {
		String result = "";
		String temp = "";
		String [] arr = str.split("/");
		for (String cur : arr) {
			for (int i = 0; i < cur.length(); i++) {
				

				char c = cur.charAt(i);
				if (c >= '0' && c <= '9') {
					for (int j = 0; j < ((int)(c - '0')) ; j ++) {
						temp += "l";
					}
				}
				else
					temp += c;
				
				}
			result += temp +  "/";
			temp = "";
			}
		
		return result;
	}
	
	public List<TupelPos> getPawnMoves(TupelPos location) {
		
		List <TupelPos> result = Arrays.asList(new TupelPos(location.x, location.y+1));
		if (location.y == 2)
			result.add(new TupelPos(location.x, location.y+2));
		if (location.x > 1)
			result.add(new TupelPos(location.x-1, location.y+1));
		if (location.x < 8)
			result.add(new TupelPos(location.x+1, location.y+1));
		return result;
	}
		
	public List<TupelPos> getRookMoves(TupelPos location) {
			
			List <TupelPos> result = null;
			for (int i = 1; i < 9; i ++) {
				if (i != location.x)
					result.add(new TupelPos(i, location.y));
				if (i != location.y)
					result.add(new TupelPos(location.x, i));
			}
			return result;
	}
	

}
