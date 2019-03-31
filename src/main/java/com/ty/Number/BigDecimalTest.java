package com.ty.Number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * TODO
 *
 * @ClassName BigDecimalTest
 * @Author tianye
 * @Date 2019/3/22 23:19
 * @Version 1.0
 */
public class BigDecimalTest extends BigDecimal {


    public BigDecimalTest(char[] in, int offset, int len) {
        super(in, offset, len);
    }

    public BigDecimalTest(char[] in, int offset, int len, MathContext mc) {
        super(in, offset, len, mc);
    }

    public BigDecimalTest(char[] in) {
        super(in);
    }

    public BigDecimalTest(char[] in, MathContext mc) {
        super(in, mc);
    }

    public BigDecimalTest(String val) {
        super(val);
    }

    public BigDecimalTest(String val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalTest(double val) {
        super(val);
    }

    public BigDecimalTest(double val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalTest(BigInteger val) {
        super(val);
    }

    public BigDecimalTest(BigInteger val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalTest(BigInteger unscaledVal, int scale) {
        super(unscaledVal, scale);
    }

    public BigDecimalTest(BigInteger unscaledVal, int scale, MathContext mc) {
        super(unscaledVal, scale, mc);
    }

    public BigDecimalTest(int val) {
        super(val);
    }

    public BigDecimalTest(int val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalTest(long val) {
        super(val);
    }

    public BigDecimalTest(long val, MathContext mc) {
        super(val, mc);
    }

    public static BigDecimal overrideAdd(BigDecimal decimal,BigDecimal decimal1){
        return decimal.add(decimal1);
    }

}
