package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;
import java.util.List;

public class Board implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1495624789044150764L;
	/**
	 * 
	 */
	
	protected Field [][] fields = new Field[8][8];
	protected List<Figur> pocket = null;
    
    public Board(String board) {
    	
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
    public Field [][] boardToArr(String str){
    	Field [][] result = new Field[8][8];
    	String [] arr = getFormatedString(str).split("/");
    	for (int i = 0; i < arr.length; i++) {
    		for (int j = 0; j < arr[i].length(); j++) {
    			char c = arr[i].charAt(j);
    			if (c == 'l')
    				result[i][j] = new Field(i, j, null);
    			else
        			result[i][j] = new Field(i, j, new Figur(c));
    		}
    	}
    	return result;
    }
}
