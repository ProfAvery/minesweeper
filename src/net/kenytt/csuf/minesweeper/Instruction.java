package net.kenytt.csuf.minesweeper;

class Instruction {
    public static final int EXPOSE  = 0;
    public static final int FLAG    = 1;
    public static final int HELP    = 2;
    public static final int QUIT    = 3;
    public static final int INVALID = 4;

    private int instruction;
    private int x;
    private int y;

    public Instruction(String input) {
        instruction = INVALID;

        if (match(input, "quit")) {
            instruction = QUIT;
        } else if (match(input, "help")) {
            instruction = HELP;
        } else {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(
                    "^\\s*(f|flag)?\\s*(\\d+)\\s*,\\s*(\\d+)\\s*$"
            );
            java.util.regex.Matcher m = p.matcher(input);
            if (m.matches()) {
                x = Integer.parseInt(m.group(2));
                y = Integer.parseInt(m.group(3));

                if (x < MineField.WIDTH && y < MineField.HEIGHT) {
                    instruction = (m.group(1) == null)? EXPOSE : FLAG;
                }
            }
        }
    }

    private boolean match(String input, String instruction) {
        return input.equals(instruction) ||
               input.equals(instruction.substring(0, 1));
    }

    public int getInstruction() {
        return instruction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
