package net.kenytt.csuf.minesweeper;

public class Main {
    private MineField field;
    private java.io.Console console;

    public Main(long gameNumber) {
        this(gameNumber, System.console());
    }

    public Main(long gameNumber, java.io.Console console) {
        this.console = console;
        field = new MineField(console, gameNumber);
    }

    public String action(Instruction i) {
        switch (i.getInstruction()) {

            case Instruction.QUIT:
                return quit();

            case Instruction.HELP:
                return help();

            case Instruction.FLAG:
                return flag(i.getX(), i.getY());

            case Instruction.EXPOSE:
                return expose(i.getX(), i.getY());

            default:
                return "Invalid command";

        }
    }

    public void run() {
        field.print();
        console.printf(" %s\n", help());

        console.printf("Action? ");
        String line = console.readLine();
        while (line != null) {
            Instruction i = new Instruction(line);
            String response = action(i);
            if (response.equals("OK")) {
                field.print();
            } else {
                console.printf("%s\n", response);
            }
            if (response.equals("BOOM!")) {
                quit();
            }
            console.printf("Action? ");
            line = console.readLine();
        }
    }

    private String quit() {
        System.exit(0);
        return "OK";
    }

    private String help() {
        return  " To expose a tile: x, y\n" +
                "  To flag a tile:   flag x, y\n" +
                "  To quit:          quit";
    }

    private String flag(int x, int y) {
        Tile t = field.getTile(x, y);
        if (t.isExposed())
            return "Already exposed";

        t.setFlagged(!t.isFlagged());
        return "OK";
    }

    private String expose(int x, int y) {
        Tile t = field.getTile(x, y);
        if (t.isExposed())
            return "Already exposed";

        if (t.isFlagged()) {
            return "Remove flag first";
        }

        if (t.isMined()) {
            t.setExposed();
            if (this.console != null) {
                field.printCleared();
            }
            return "BOOM!";
        }

        field.expose(x, y);
        return "OK";
    }

    public static void main(String[] args) {
        long gameNumber = 0L;

        try {
            if (args.length == 0) {
                gameNumber = System.currentTimeMillis();
            } else if (args.length == 1) {
                gameNumber = Long.parseLong(args[0]);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.err.format("Usage: java [options] net.kenytt.csuf.minesweeper.Main [GameNumber]");
            System.exit(1);
        }

        Main game = new Main(gameNumber);
        game.run();
    }
}

