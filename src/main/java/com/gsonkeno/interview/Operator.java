package com.gsonkeno.interview;
/**
 * https://blog.csdn.net/niuzhucedenglu/article/details/79540799
 * @author gaosong
 * @since 2019-02-27
 */
public enum Operator {
    ADD ("+") {
        @Override
        public int calculate(int a, int b) {
            return a + b;
        }
    } ;

    Operator (String operator) {
        this.operator = operator;
    }

    private String operator;

    public abstract int calculate(int a, int b);

    public String getOperator() {
        return operator;
    }
}
