package com.kiran.testingkotlinapp

import org.junit.Assert
import org.junit.Test

class MyTest {

    private lateinit var arithmetic: Arithmetic

    // Addition lai check garu
    @Test
    fun checkAddition() {
        val maileExpectGareko = 5
        arithmetic = Arithmetic()
        arithmetic.first = 3
        arithmetic.second = 2

        val usleDeko = arithmetic.add()

        Assert.assertEquals(maileExpectGareko, usleDeko)
    }

    // Addition lai check garu
    @Test
    fun checkSub() {
        val maileExpectGareko = 1
        arithmetic = Arithmetic()
        arithmetic.first = 3
        arithmetic.second = 2

        val usleDeko = arithmetic.subtract()

        Assert.assertEquals(maileExpectGareko, usleDeko)
    }
}