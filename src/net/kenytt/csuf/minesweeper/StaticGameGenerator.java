package net.kenytt.csuf.minesweeper;

public class StaticGameGenerator implements GameGenerator {

	private long gameNumber;
	
	public void setGameNumber(long gameNumber) {
		this.gameNumber = gameNumber;
	}
	
	public long getGameNumber() {
		return this.gameNumber;
	}
	
}