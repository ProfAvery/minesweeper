package net.kenytt.csuf.minesweeper;

public class InstructionTest extends junit.framework.TestCase
{
    public void testExpose()
    {
        Instruction i = new Instruction("0,0");
        assertEquals(Instruction.EXPOSE, i.getInstruction());
        assertEquals(0, i.getX());
        assertEquals(0, i.getY());

        i = new Instruction("0, 8");
        assertEquals(Instruction.EXPOSE, i.getInstruction());
        assertEquals(0, i.getX());
        assertEquals(8, i.getY());

        i = new Instruction("8, 0");
        assertEquals(Instruction.EXPOSE, i.getInstruction());
        assertEquals(8, i.getX());
        assertEquals(0, i.getY());
    }

    public void testFlag()
    {
        Instruction i = new Instruction("f0,0");
        assertEquals(Instruction.FLAG, i.getInstruction());
        assertEquals(0, i.getX());
        assertEquals(0, i.getY());
        
        i = new Instruction("f 0,8");
        assertEquals(Instruction.FLAG, i.getInstruction());
        assertEquals(0, i.getX());
        assertEquals(8, i.getY());
        
        i = new Instruction("flag 8,0");
        assertEquals(Instruction.FLAG, i.getInstruction());
        assertEquals(8, i.getX());
        assertEquals(0, i.getY());
    }
    
    public void testHelp()
    {
        Instruction i = new Instruction("h");
        assertEquals(Instruction.HELP, i.getInstruction());
        
        i = new Instruction("help");
        assertEquals(Instruction.HELP, i.getInstruction());
    }
    
    public void testQuit()
    {
        Instruction i = new Instruction("q");
        assertEquals(Instruction.QUIT, i.getInstruction());
        
        i = new Instruction("quit");
        assertEquals(Instruction.QUIT, i.getInstruction());
    }
    
    public void testInvalid()
    {
        Instruction i = new Instruction("he");
        assertEquals(Instruction.INVALID, i.getInstruction());
        
        i = new Instruction("qu");
        assertEquals(Instruction.INVALID, i.getInstruction());

        i = new Instruction("wtf");
        assertEquals(Instruction.INVALID, i.getInstruction());

        i = new Instruction("-1, 0");
        assertEquals(Instruction.INVALID, i.getInstruction());
        
        i = new Instruction("0,9");
        assertEquals(Instruction.INVALID, i.getInstruction());

        i = new Instruction("0,-1");
        assertEquals(Instruction.INVALID, i.getInstruction());
        
        i = new Instruction("9, 0");
        assertEquals(Instruction.INVALID, i.getInstruction());
    }
}

