package org.github.cmonkey.spark;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SlidingWindowSpliterator<T> implements Spliterator<Stream<T>> {
    @Override
    public boolean tryAdvance(Consumer<? super Stream<T>> action) {
        return false;
    }

    @Override
    public Spliterator<Stream<T>> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
