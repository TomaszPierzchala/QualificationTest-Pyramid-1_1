package com.ncr.test.pyramid.data;

import java.util.Objects;

public class SubPath implements Comparable<SubPath> {
    // Contains information about a current sub-path in the pyramid
    // with its total value and position in the pyramid (row and column).

    // For sorting purposes, it implements Comparable interface comparing by currentTotal value.
	private int column;
	private int row;
	private long currentTotal;
	
	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
	public long getCurrentTotal() {
		return currentTotal;
	}
	
	public SubPath(int row, int column, long currentTotal) {
		super();
		this.column = column;
		this.row = row;
		this.currentTotal = currentTotal;
	}
	@Override
	public int compareTo(SubPath o) {
		if( this.currentTotal < o.currentTotal ) {
			return +1;
		} else if( this.currentTotal > o.currentTotal ) {
			return -1;
		} else {
            return 0;
        }
	}
	@Override
	public int hashCode() {
		return Objects.hash(column, currentTotal, row);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubPath other = (SubPath) obj;
		return column == other.column && currentTotal == other.currentTotal && row == other.row;
	}
	
}
