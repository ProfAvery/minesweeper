package net.kenytt.csuf.minesweeper;

class MineField {
    public static final int WIDTH  = 9;
    public static final int HEIGHT = 9;
    public static final int MINES  = 10;

    private Tile[][] field;
    private java.util.Random random;
    private java.io.Console console;

    public MineField(java.io.Console console, long seed) {
        this.console = console;

        field = new Tile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                field[x][y] = new Tile();
            }
        }

        random = new java.util.Random(seed);
        dropMines();
    }

    private void dropMines() {
        int minesDropped = 0;
        while (minesDropped < MINES) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            if (!field[x][y].isMined()) {
                field[x][y].setMined();
                minesDropped++;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return field[x][y];
    }

    private void printColumnLabels() {
        console.printf("   ");
        for (int i = 0; i < WIDTH; i++) {
            console.printf("%2d ", i);
        }
        console.printf("\n");
    }

    private void printRowLabel(int row) {
        console.printf("%2d ", row);
    }

    private char getChar(int x, int y, boolean showMines) {
        Tile t = field[x][y];
        char c = '.';

        if (t.isFlagged()) {
            if (showMines) {
                c = t.isMined()? 'F' : 'X';
            } else {
                c = 'F';
            }
        } else if (showMines && t.isMined()) {
            c = t.isExposed()? '!' : '*';
        } else if (t.isExposed()){
            int adjacentMines = countAdjacentMines(x, y);
            if (adjacentMines == 0) {
                c = ' ';
            } else {
                c = Character.forDigit(adjacentMines, 10);
            }
        }

        return c;
    }

    private void printField(boolean showMines) {
        printColumnLabels();
        for (int y = 0; y < HEIGHT; y++) {
            printRowLabel(y);

            for (int x = 0; x < WIDTH; x++) {
                console.printf(" %c ", getChar(x, y, showMines));
            }
            console.printf("\n");
        }
    }

    public void print() {
        printField(false);
    }

    public void printCleared() {
        printField(true);
    }

    public int countAdjacentMines(int x, int y) {
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= WIDTH ||
                    j < 0 || j >= HEIGHT ||
                    (i == x && j == y)) {
                    continue;
                }
                if (field[i][j].isMined()) {
                    count++;
                }
            }
        }

        return count;
    }

    public void expose(int x, int y) {
        Tile t = field[x][y];
        t.setExposed();
        exposeNearbyTiles(x, y);
    }

    private void exposeNearbyTiles(int x, int y) {
        if (countAdjacentMines(x, y) != 0)
            return;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= WIDTH ||
                    j < 0 || j >= HEIGHT ||
                    (i == x && j == y)) {
                    continue;
                }
                if (!field[i][j].isMined()) {
                    if (!field[i][j].isExposed()) {
                        field[i][j].setExposed();
                        exposeNearbyTiles(i, j);
                    }
                }
            }
        }
    }
}
