package org.github.cmonkey.spark;



import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SlidingWindowSpliterator<T> implements Spliterator<Stream<T>> {
    private final Queue<T> buffer;
    private final Iterator<T> sourceIterator;
    private final int windowSize;
    private final int size;

    private SlidingWindowSpliterator(Collection<T> source, int windowSize){

        this.buffer = null;
        this.sourceIterator = Objects.requireNonNull(source).iterator();
        this.windowSize = windowSize;
        this.size = calculateSize(source, windowSize);
    }

    private int calculateSize(Collection<T> source, int windowSize) {
        return source.size() < windowSize ? 0 : source.size() - windowSize + 1;
    }

    public static <T> Stream<Stream<T>> windowed(Collection<T> stream, int windowSize){

        return StreamSupport.stream(new SlidingWindowSpliterator<>(stream, windowSize), false);
    }

    @Override
    public boolean tryAdvance(Consumer<? super Stream<T>> action) {
        if(windowSize < 1){
            return false;
        }

        while(sourceIterator.hasNext()){

            buffer.add(sourceIterator.next());

            if(buffer.size() == windowSize){
                action.accept(Arrays.stream((T[])buffer.toArray(new Object[0])));

                buffer.poll();

                return sourceIterator.hasNext();
            }
        }
        return false;
    }

    @Override
    public Spliterator<Stream<T>> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return size;
    }

    @Override
    public int characteristics() {
        return ORDERED | NONNULL | SIZED;
    }
}
