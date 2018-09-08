package org.github.cmonkey.spark.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

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
}
