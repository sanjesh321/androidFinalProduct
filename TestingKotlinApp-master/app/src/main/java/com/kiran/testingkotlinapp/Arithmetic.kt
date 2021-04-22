package com.kiran.testingkotlinapp

class Arithmetic {
    var first: Int = 0
    var second: Int = 0

    fun add(): Int {
        return first + second
    }

    fun subtract(): Int {
        return first - second
    }

    fun checkEven(num: Int): Boolean {
        return num % 2 == 0
    }

}