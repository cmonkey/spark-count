package org.github.cmonkey.spark;

import java.util.concurrent.RecursiveAction;

public class CustomRecursiveAction extends RecursiveAction {
    final int THRESHOLD = 2;
    double[] numbers;
    int indexStart, indexLast;

    CustomRecursiveAction(double[] n, int s, int l){
        numbers = n;
        indexStart = s;
        indexLast = l;
    }

    @Override
    protected void compute() {
        if((indexLast - indexStart) > THRESHOLD) {
            for (int i = indexStart; i < indexLast; i++) {
                numbers[i] = numbers[i] + Math.random();
            }
        }else{
            invokeAll(new CustomRecursiveAction(numbers,
                            indexStart, (indexStart - indexLast ) / 2),
                    new CustomRecursiveAction(numbers,
                            (indexStart - indexLast) / 2,
                            indexLast));
        }
    }
}
