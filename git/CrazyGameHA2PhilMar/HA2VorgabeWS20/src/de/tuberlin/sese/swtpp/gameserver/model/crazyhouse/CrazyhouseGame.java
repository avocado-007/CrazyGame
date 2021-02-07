package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

import de.tuberlin.sese.swtpp.gameserver.model.Board;
import de.tuberlin.sese.swtpp.gameserver.model.Game;
import de.tuberlin.sese.swtpp.gameserver.model.Move;
import de.tuberlin.sese.swtpp.gameserver.model.Player;

public class CrazyhouseGame extends Game implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5424778147226994452L;
	
	private static Board boardFields = null;
	
	private static String board = null;
	private static String valChar = "abcdefgh";
	private static String valNum = "12345678";
	private static String valFigW = "RNBQK";
	private static String valFigB = "rnbqk";
	
	/************************
	 * member
	 ***********************/

	// just for better comprehensibility of the code: assign white and black player
	private Player blackPlayer;
	private Player whitePlayer;

	// internal representation of the game state
	// TODO: insert additional game data here

	/************************
	 * constructors
	 ***********************/

	public CrazyhouseGame() {
		super();
		board = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/";
		// TODO: initialize internal model if necessary 
	}

	public String getType() {
		return "crazyhouse";
	}

	/*******************************************
	 * Game class functions already implemented
	 ******************************************/

	@Override
	public boolean addPlayer(Player player) {
		if (!started) {
			players.add(player);

			// game starts with two players
			if (players.size() == 2) {
				started = true;
				this.whitePlayer = players.get(0);
				this.blackPlayer= players.get(1);
				nextPlayer = whitePlayer;
			}
			return true;
		}

		return false;
	}

	@Override
	public String getStatus() {
		if (error)
			return "Error";
		if (!started)
			return "Wait";
		if (!finished)
			return "Started";
		if (surrendered)
			return "Surrendered";
		if (draw)
			return "Draw";

		return "Finished";
	}

	@Override
	public String gameInfo() {
		String gameInfo = "";

		if (started) {
			if (blackGaveUp())
				gameInfo = "black gave up";
			else if (whiteGaveUp())
				gameInfo = "white gave up";
			else if (didWhiteDraw() && !didBlackDraw())
				gameInfo = "white called draw";
			else if (!didWhiteDraw() && didBlackDraw())
				gameInfo = "black called draw";
			else if (draw)
				gameInfo = "draw game";
			else if (finished)
				gameInfo = blackPlayer.isWinner() ? "black won" : "white won";
		}

		return gameInfo;
	}

	@Override
	public String nextPlayerString() {
		return isWhiteNext() ? "w" : "b";
	}

	@Override
	public int getMinPlayers() {
		return 2;
	}

	@Override
	public int getMaxPlayers() {
		return 2;
	}

	@Override
	public boolean callDraw(Player player) {

		// save to status: player wants to call draw
		if (this.started && !this.finished) {
			player.requestDraw();
		} else {
			return false;
		}

		// if both agreed on draw:
		// game is over
		if (players.stream().allMatch(Player::requestedDraw)) {
			this.draw = true;
			finish();
		}
		return true;
	}

	@Override
	public boolean giveUp(Player player) {
		if (started && !finished) {
			if (this.whitePlayer == player) {
				whitePlayer.surrender();
				blackPlayer.setWinner();
			}
			if (this.blackPlayer == player) {
				blackPlayer.surrender();
				whitePlayer.setWinner();
			}
			surrendered = true;
			finish();

			return true;
		}

		return false;
	}

	/* ******************************************
	 * Helpful stuff
	 ***************************************** */

	/**
	 *
	 * @return True if it's white player's turn
	 */
	public boolean isWhiteNext() {
		return nextPlayer == whitePlayer;
	}

	/**
	 * Ends game after regular move (save winner, finish up game state,
	 * histories...)
	 *
	 * @param winner player who won the game
	 * @return true if game was indeed finished
	 */
	public boolean regularGameEnd(Player winner) {
		// public for tests
		if (finish()) {
			winner.setWinner();
			winner.getUser().updateStatistics();
			return true;
		}
		return false;
	}

	public boolean didWhiteDraw() {
		return whitePlayer.requestedDraw();
	}

	public boolean didBlackDraw() {
		return blackPlayer.requestedDraw();
	}

	public boolean whiteGaveUp() {
		return whitePlayer.surrendered();
	}

	public boolean blackGaveUp() {
		return blackPlayer.surrendered();
	}

	/*******************************************
	 * !!!!!!!!! To be implemented !!!!!!!!!!!!
	 ******************************************/

	@Override
	public void setBoard(String state) {
		// Note: This method is for automatic testing. A regular game would not start at some artificial state.
		//       It can be assumed that the state supplied is a regular board that can be reached during a game.
		// TODO: implement
		if (state != null)
			if ( countChar('k', state) == 1
			&& countChar('K', state) == 1
			&& countChar('q', state) 
				+ countChar('Q', state) 
				+ countChar('p', state)
				+ countChar('P', state) == 18
			&& countChar('b', state) + countChar('B', state) == 4
			&& countChar('n', state) + countChar('N', state) == 4
			&& countChar('r', state) + countChar('R', state) == 4
			&& countChar('0', state) == 0
			&& stateSizeCheck(state)
			)
		
			board = state;
	}

	@Override
	public String getBoard() {
		// TODO: implement
		
		// replace with real implementation
		
		return board;
	}

	@Override
	public boolean tryMove(String moveString, Player player) {
		// TODO: implement
		if (!this.isPlayersTurn(player) || !commandHasValidFormat(moveString))
			return false;

		Move move = new Move(moveString,this.getBoard(), player);
		
		if(this.getBoard() == "8/8/8/8/8/8/8/8/" )
			return false;
		
		
		// replace with real implementation
		return false;
	}
	
	public boolean commandHasValidFormat(String move) {
		if (valChar.contains(move.substring(0,0)) 
				&& valNum.contains(move.substring(1,1)) 
				&& move.substring(2,2) == "-"
				&& valChar.contains(move.substring(3,3)) 
				&& valNum.contains(move.substring(4,4)))
			return true;
		else if ((valFigW + valFigB).contains(move.substring(0,0)) 
				&&  move.substring(1,1) == "-"
				&& valChar.contains(move.substring(2,2)) 
				&& valNum.contains(move.substring(3,3)))
			return true;
		else
			return false;
	}
	public int countChar(char someChar, String someString) {
		int count = 0;
	
		for (int i = 0; i < someString.length(); i++) {
		    if (someString.charAt(i) == someChar) {
		        count++;
		    }
		}
	    return count;
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
	
	public boolean stateSizeCheck(String state) {
		String [] arr = getFormatedString(state).split("/");
		for (String s : arr ) {
			if (s.length() != 8)
				return false;
		}
		return true;

	}
	
	
}
