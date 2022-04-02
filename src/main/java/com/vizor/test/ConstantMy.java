package com.vizor.test;

public enum ConstantMy {
    /**
     * Frame size
     */
    WIDTH(1024),
    HEIGHT(768),
    /**
     * Icon size
     */
    SCALE_WIDTH(120),
    SCALE_HEIGHT(120),
    /**
     * Pagination params
     */
    ROWS(3),
    COLUMNS(4),
    GAPS(10);

    private int constant;

    ConstantMy(int i) {
        constant = i;
    }

    public int getConstant() {
        return constant;
    }
}
