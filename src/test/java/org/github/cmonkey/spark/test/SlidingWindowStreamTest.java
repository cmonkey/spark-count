package org.github.cmonkey.spark.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.github.cmonkey.spark.SlidingWindowSpliterator.windowed;

public class SlidingWindowStreamTest {
    @Test
    public void shouldApplySlidingWindow(){

        List<Integer> source = Lists.newArrayList(1,2,3,4);

        List<List<Integer>> result  = windowed(source, 3)
                .map(s -> s.collect(Collectors.toList()))
        .collect(Collectors.toList());

        assertThat(result).containsExactly(
                Lists.newArrayList(1,2,3),
                Lists.newArrayList(2,3,4));
    }

    @Test
    public void sholudApplySlidingWindowToStreamSmallerThanWindow(){
        List<Integer> source = Lists.newArrayList(1, 2);

        List<List<Integer>> result = windowed(source, 3)
            .map(s -> s.collect(Collectors.toList()))
            .collect(Collectors.toList());

        assertThat(result).isEmpty();

    }

    @Test
    public void shouldApplySlidingWindowToEmptyStream(){
        List<Integer> source = Collections.emptyList();

        List<List<Integer>> result = windowed(source, 3)
            .map(s -> s.collect(Collectors.toList()))
            .collect(Collectors.toList());

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldApplyZeroSlidingWindow(){
        List<Integer> source = Lists.newArrayList(1,2,3,4);

        List<List<Integer>> result = windowed(source, 0)
            .map(s -> s.collect(Collectors.toList()))
            .collect(Collectors.toList());

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldOnNotLateBindToInternalBuffer(){
        List<Integer> source = Lists.newArrayList(1,2,3,4);

        List<Stream<Integer>> result = windowed(source, 2)
            .collect(Collectors.toList());

        Stream<Integer> s3 = result.get(2);

        assertThat(s3.collect(Collectors.toList())).containsExactly(3, 4);


    }
}
