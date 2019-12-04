package com.antonfagerberg.adventofcode2019

object Day04 {
    fun increment(value: Array<Int>, index: Int = value.size - 1): Array<Int> {
        return if (value[index] == 9) {
            if (index == 0) {
                value
            } else {
                increment(value, index - 1)
            }
        } else {
            val newValue = value[index] + 1
            (index until value.size).forEach { value[it] = newValue }
            value
        }
    }

    fun validatePart1(value: Array<Int>): Boolean {
        var doubleDigit = false

        (1 until value.size).forEach {
            if (value[it] == value[it - 1]) {
                doubleDigit = true
            }

            if (value[it] < value[it - 1]) {
                return false
            }
        }

        return doubleDigit
    }

    fun validatePart2(value: Array<Int>): Boolean {
        var doubleDigit = false

        (1 until value.size).forEach {

            val next = if (it == value.size - 1) -1 else value[it + 1]
            val previous = if (it < 2) -1 else value[it - 2]

            if (value[it] == value[it - 1] && next != value[it] && previous != value[it]) {
                doubleDigit = true
            }

            if (value[it] < value[it - 1]) {
                return false
            }
        }

        return doubleDigit
    }

    fun solve(input: String, validate: (Array<Int>) -> Boolean): Int {
        val parts = input.split("-")
        val start = parts[0].map { it.toString().toInt() }.toTypedArray()
        val end = parts[1].map { it.toString().toInt() }.toTypedArray()

        var count = 0

        while (true) {
            if (validate(start)) {
                count++
            }

            increment(start)

            if (!start.indices.any { start[it] < end[it] }) {
                return count
            }

        }
    }
}