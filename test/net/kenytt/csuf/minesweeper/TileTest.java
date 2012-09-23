package net.kenytt.csuf.minesweeper;

public class TileTest extends junit.framework.TestCase
{
    private Tile t;

    protected void setUp()
    {
        t = new Tile();
    }

    public void testExposed()
    {
        assertEquals(false, t.isExposed());
        t.setExposed();
        assertEquals(true, t.isExposed());
    }

    public void testFlagged()
    {
        assertEquals(false, t.isFlagged());
        t.setFlagged(true);
        assertEquals(true, t.isFlagged());
        t.setFlagged(false);
        assertEquals(false, t.isFlagged());
    }

    public void testMined()
    {
        assertEquals(false, t.isMined());
        t.setMined();
        assertEquals(true, t.isMined());
    }
}



