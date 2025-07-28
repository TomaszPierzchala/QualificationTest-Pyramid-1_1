package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.PyramidGenerator;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import com.ncr.test.pyramid.solver.impl.YourSolver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YourSolverTest {
    private static final int MAX_DEPTH = 100;

    private static final int[][] SAMPLE_DATA = {
            { 5, 9, 8, 4 },
            { 6, 4, 5, 0 },
            { 6, 7, 0, 0 },
            { 3, 0, 0, 0 }
    };
    private static final int[][] DEMO_DATA = {
            { 59, 207, 98, 95 },
            { 87,   1, 70,  0 },
            { 36,  41,  0,  0 },
            { 23,   0,  0,  0 }
    };

    private static final int[][] MAX_SIZE_AT_LEAST_7 = {
            { 23,  1,  2,  1,  32,  1 },
            {  1,  2,  1,  3,  30,  0 },
            {  3,  2,  5,  1,   0,  0 },
            { 25,  1,  2,  0,   0,  0 },
            { 15,  3,  0,  0,   0,  0 },
            {  7,  0,  0,  0,   0,  0 }
    };

    private static final int SIZE = 14; // the SIZE of the pyramid in the test below
    private static final int[][] BIG_DATA = new int[SIZE][];
    static {
        // Create a semi-uniform pyramid with 1s and increasing values in the first row
        for(int i = SIZE-1; i > 0; i--) {
            BIG_DATA[i] = new int[SIZE];
            for (int j = 0; j < SIZE; j++) {
                BIG_DATA[i][j] = 1;
            }
        }
        BIG_DATA[0] = new int[SIZE];
        for(int j = 0; j < SIZE; j++) {    
            BIG_DATA[0][j] = j + 1;
        }
    }

    protected PyramidSolver solver;

    @Before
    public void setUp() {
        solver = new YourSolver();
    }

    @Test
    public void solverHandlesSampleData() {
        Pyramid pyramid = new Pyramid(SAMPLE_DATA);
        assertEquals("Max path in Sample pyramid", 24, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverHandlesDemoData() {
        Pyramid pyramid = new Pyramid(DEMO_DATA);
        assertEquals("Max path in Demo pyramid", 353, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void maxSizeAtLeast7() {
        // We need at least 7 sub-paths to be considered at each row
        // to ensure that the solver works correctly with the provided data.
        Pyramid pyramid = new Pyramid(MAX_SIZE_AT_LEAST_7);
        assertEquals("Max path in MAX_SIZE >= 7 pyramid", 75, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void BigSemiUniformDataTest() {
        // in this test the Pyramid is quite big - 14 rows.
        // moreover is almost uniform (only the last row contains the difference),
        // so any max value search algorithm should keep many, if not all sub-paths
        // Here we have 2^14 = 16,384 sub-paths different paths and MAX_SIZE needs to be at least ~4100 
        Pyramid pyramid = new Pyramid(BIG_DATA);
        System.out.println(pyramid);
        assertEquals("Max path in MAX_SIZE ofis too small", 27, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverSurvivesLargeData() {
        PyramidGenerator generator = new RandomPyramidGenerator(MAX_DEPTH, 1000);
        Pyramid pyramid = generator.generatePyramid();
        assertTrue("Max path in a large pyramid not positive", solver.pyramidMaximumTotal(pyramid) > 0L);
    }

    @Test
    public void solverHandlesRandomData() {
        RandomPyramidGenerator.setRandSeed(25321L);  // ensure pyramid contents
        final PyramidGenerator generator = new RandomPyramidGenerator(5, 99);
        final Pyramid pyramid = generator.generatePyramid();

        assertEquals("Max path in 'random' pyramid", 398, this.solver.pyramidMaximumTotal(pyramid));
    }
}
