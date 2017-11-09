package org.github.cmonkey.spark.test;

import org.junit.jupiter.api.*;

public class TestJunit5 {

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll executed");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach executed");
    }

    @Tag("dev")
    @Test
    void testDevCalc(){

        System.out.println("test dev executed");
        Assertions.assertEquals(4, add(2, 2));
    }

    @Tag("prod")
    @Disabled
    @Test
    void testProdCalc(){
        System.out.println("prod dev executed");
        Assertions.assertEquals(4, add(2, 2));
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All executed");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each executed");
    }

    private int add(int x, int y) {
        return x + y;
    }
}
