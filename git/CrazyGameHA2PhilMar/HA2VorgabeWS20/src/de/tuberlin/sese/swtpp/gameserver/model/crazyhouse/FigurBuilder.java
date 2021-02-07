package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

public class FigurBuilder {
	public Figur buildFigur(char c) {
		switch (c) {
			case 'K': return new King(c)
		}
	}

}
