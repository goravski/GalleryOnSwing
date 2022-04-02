package com.vizor.test;

public enum ConstantMy {

    WIDTH(1024),
    HEIGHT(768),

    SCALE_WIDTH(120),

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
