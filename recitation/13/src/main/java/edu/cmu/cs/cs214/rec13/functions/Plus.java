package edu.cmu.cs.cs214.rec13.functions;

import java.io.Serializable;
import java.util.function.BinaryOperator;


public class Plus implements BinaryOperator<Integer>, Serializable {
    @Override
    public Integer apply(Integer integer, Integer integer2) {
        return integer + integer2;
    }
}
