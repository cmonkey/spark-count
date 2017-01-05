package com.sparkcount.test;

/**
 * Created by cmonkey on 1/5/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.sparkcount.CreateHighOrderFunction;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HighOrderFunctionTest {

    @BeforeClass public static void setUp(){
        CreateHighOrderFunction createHighOrderFunction =
                new CreateHighOrderFunction(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
    @Test public void testFindEvenNumbers() {
        //Predicate to filter out the list by even numbers

        Predicate<Integer> integerPredicate = element -> element % 2 == 0;
        List expectedList = new ArrayList();
        expectedList.add(2);
        expectedList.add(4);
        expectedList.add(6);
        List actualList = CreateHighOrderFunction.findEvenNumbers(integerPredicate);
        assertThat(expectedList, is(equalTo(actualList)));

    }
}