package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: There is something wrong here. A few things actually... 
 */
public class NaivePyramidSolver implements PyramidSolver {
    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
    }

    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        if (row == 0) return pyramid.get(row, column); // it should take values from highest row, not Zeros; 

        int myValue = pyramid.get(row, column);
        long left  = myValue + getTotalAbove(row - 1, column, pyramid);
        long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
        /* here are two problems: 
        1. one row decition is not enough, we need to consider all paths above 
        in my code I take up to MAX_SIZE Paths (limit the number of paths to avoid OutOfMemory issues)
        2. eficiency: I do not use recursion of method call, but rather calculating best paths (called SubPath) every row.
        */
        return Math.max(left, right);
    }
}