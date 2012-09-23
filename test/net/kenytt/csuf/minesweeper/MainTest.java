package net.kenytt.csuf.minesweeper;

public class MainTest extends junit.framework.TestCase
{
    private Main m;

    protected void setUp()
    {
        m = new Main(0, null);
    }

    public void testGamePlay()
    {
        Instruction i = new Instruction("0, 8");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("flag 4, 6");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("4, 6");
        assertEquals("Remove flag first", m.action(i));
        
        i = new Instruction("8, 8");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("8, 0");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("2, 0");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("flag 4, 0");
        assertEquals("Already exposed", m.action(i));
        
        i = new Instruction("flag 2, 1");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("flag 4, 2");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("5, 7");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("flag 5, 8");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("6, 8");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("8, 4");
        assertEquals("OK", m.action(i));
        
        i = new Instruction("8, 2");
        assertEquals("BOOM!", m.action(i));
    }
}

