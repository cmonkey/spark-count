package com.sparkcount;

import scala.Int;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by cmonkey on 1/5/17.
 */
public class CreateHighOrderFunction {

    private static List<Integer> list;

    private static List<Integer> result = new ArrayList<>();

    public CreateHighOrderFunction(List<Integer> list){
        CreateHighOrderFunction.list = list;
    }

    // find Even Number is a function which takes a Prodicate

    public static List findEvenNumbers(Predicate predicateToApply){

        BiFunction<List, Predicate, List> filter;

        filter = (listofIntegers, predicate) -> {
            //result.addAll(list.stream().filter(predicate).collect(Collectors.toList()));
            result.addAll((Collection<? extends Integer>) list.stream().filter(predicate).collect(Collectors.toList()));
            return result;
        };

        filter.apply(list, predicateToApply);

        return result;
    }
}
