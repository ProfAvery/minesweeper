package net.kenytt.csuf.minesweeper;

public class MineFieldTest extends junit.framework.TestCase
{
    private MineField f;

    protected void setUp()
    {
        // Passing the same number to the constructor
        // results in the same mine field arrangement
        // (by seeding the random number generator)
        
        f = new MineField(null, 0);
    }

    public void testMinedTile()
    {
        Tile t = f.getTile(5, 2);
        assertEquals(false, t.isExposed());
        assertEquals(true, t.isMined());
    }

    public void testExposeSingleTile()
    {
        Tile t = f.getTile(1, 0);
        assertEquals(false, t.isExposed());
        f.expose(1, 0);
        assertEquals(true, t.isExposed());
        assertEquals(1, f.countAdjacentMines(1, 0));
    }

    private boolean isExposed(int x, int y) {
        Tile t = f.getTile(x, y);
        return t.isExposed();
    }
    
    public void testExposeMultipleTiles()
    {
        Tile t = f.getTile(8, 8);
        assertEquals(false, t.isExposed());
        f.expose(8, 8);
        t = f.getTile(8, 8);
        assertEquals(true, t.isExposed());

        t = f.getTile(8, 7);
        assertEquals(true, t.isExposed());
        t = f.getTile(8, 6);
        assertEquals(true, t.isExposed());
        
        t = f.getTile(7, 8);
        assertEquals(true, t.isExposed());
        t = f.getTile(7, 7);
        assertEquals(true, t.isExposed());
        t = f.getTile(7, 6);
        assertEquals(true, t.isExposed());
    }
    
    public void testCountAdjacentMines()
    {
        assertEquals(1, f.countAdjacentMines(1, 0));
        assertEquals(1, f.countAdjacentMines(1, 1));
        assertEquals(1, f.countAdjacentMines(1, 2));
        assertEquals(1, f.countAdjacentMines(2, 2));
        assertEquals(2, f.countAdjacentMines(3, 2));
        assertEquals(1, f.countAdjacentMines(3, 3));
        assertEquals(2, f.countAdjacentMines(4, 3));
        assertEquals(3, f.countAdjacentMines(5, 3));
        assertEquals(3, f.countAdjacentMines(7, 3));
        assertEquals(2, f.countAdjacentMines(7, 4));
        assertEquals(1, f.countAdjacentMines(7, 5));
        assertEquals(2, f.countAdjacentMines(7, 6));
        assertEquals(1, f.countAdjacentMines(6, 6));
        assertEquals(2, f.countAdjacentMines(5, 6));
        assertEquals(1, f.countAdjacentMines(5, 5));
        assertEquals(1, f.countAdjacentMines(4, 5));
        assertEquals(1, f.countAdjacentMines(3, 5));
        assertEquals(1, f.countAdjacentMines(3, 6));
        assertEquals(1, f.countAdjacentMines(3, 7));
        assertEquals(2, f.countAdjacentMines(4, 7));
        assertEquals(1, f.countAdjacentMines(4, 8));
    }
}


