package net.kenytt.csuf.minesweeper;

class Tile {
    private boolean exposed;
    private boolean mined;
    private boolean flagged;

    public Tile() {
        exposed = false;
        flagged = false;
        mined = false;
    }

    public boolean isExposed() {
        return exposed;
    }
    
    public void setExposed() {
        this.exposed = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isMined() {
        return mined;
    }
    
    public void setMined() {
        this.mined = true;
    }
}
