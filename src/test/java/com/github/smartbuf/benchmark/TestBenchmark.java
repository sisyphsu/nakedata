package com.github.smartbuf.benchmark;

import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sulin
 * @since 2019-10-30 20:21:38
 */
@Warmup(iterations = 2, time = 2)
@Fork(2)
@Measurement(iterations = 3, time = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TestBenchmark {

    static Object[] arr  = new Object[1024];
    static List     list = new ArrayList<>();

    static {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RandomUtils.nextDouble();
            list.add(arr[i]);
        }
    }

    @Benchmark
    public void test() {
        Class<?> prevCls = null;
        Class<?> currCls = null;
        int i = 0;
        // 674ns
        for (Object o : list) {
            currCls = o == null ? null : o.getClass();
            if (prevCls == currCls) {
                i++;
            }
            prevCls = currCls;
        }
    }

    @Benchmark
    public void test2() {
        Class<?> cls = Object.class;
        cls.isEnum();
        cls.isArray();
        cls.getName();
    }

}
