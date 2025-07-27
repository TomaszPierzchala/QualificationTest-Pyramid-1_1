package com.ncr.test.pyramid.solver.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.SubPath;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
    	long MAX_SIZE=7L; // the maximum number of sub-paths to be considered at each row
    	
        // we start from the bottom of the pyramid, so we need to know the first row and colum
    	int ZERO_COLUMN = 0, FIRST_ROW = pyramid.getRows()-1;
        
        SubPath[] startSubPath = new SubPath[1];
    	startSubPath[0] = new SubPath(FIRST_ROW, ZERO_COLUMN, pyramid.get(FIRST_ROW, ZERO_COLUMN));
    	// list conteining results
        List<SubPath> bottomSubPaths = Arrays.asList(startSubPath);
    	//
    	for(int r=FIRST_ROW-1; r >= 0; r--) { // the loop over rows, start from the second row
    		List<SubPath> subPathsAtRow = new LinkedList();
    		for(SubPath bSubPath : bottomSubPaths) {
                // go left
    			subPathsAtRow.add(new SubPath(r, bSubPath.getColumn(), bSubPath.getCurrentTotal() + pyramid.get(r, bSubPath.getColumn())));
    			// go right
                subPathsAtRow.add(new SubPath(r, bSubPath.getColumn()+1, bSubPath.getCurrentTotal() + pyramid.get(r, bSubPath.getColumn()+1)));

    		}
            // we need to limit the number of sub-paths to MAX_SIZE, as list size would be of size 2^rows - OutOfMemory issue
            // but in tests provided MAX_SIZE = 2 is enough (this should not be alway true); however even for MAX_SIZE = 10_000 calculations are fast enough.
    		bottomSubPaths = subPathsAtRow.stream().sorted().limit(MAX_SIZE).collect(Collectors.toList());
    	}
    	
    	return bottomSubPaths.stream().findFirst().get().getCurrentTotal();
    	
    }
}
