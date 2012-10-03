package net.kenytt.csuf.minesweeper;

public class RandomGameGenerator implements GameGenerator {

	public long getGameNumber() {
		return System.currentTimeMillis();
	}
	
}