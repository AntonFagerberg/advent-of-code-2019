package com.antonfagerberg.adventofcode2019

object Day01 {
    fun calculateFuelPart1(value: Int): Int = value / 3 - 2

    fun calculateFuelPart2(value: Int): Int {
        val result = calculateFuelPart1(value)
        return if (result <= 0) 0 else result + calculateFuelPart2(result)
    }

    fun solve(input: List<Int>, f: (Int) -> Int): Int = input.foldRight(0) { next, acc -> f(next) + acc }
}