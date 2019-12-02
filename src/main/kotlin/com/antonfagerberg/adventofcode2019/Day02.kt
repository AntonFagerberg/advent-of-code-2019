package com.antonfagerberg.adventofcode2019

object Day02 {
    fun run(input: MutableList<Int>, position: Int = 0): List<Int> {
        return if (input[position] == 1 || input[position] == 2) {
            val a = input[input[position + 1]]
            val b = input[input[position + 2]]

            input[input[position + 3]] = if (input[position] == 1) a + b else a * b

            run(input, position + 4)
        } else if (input[position] == 99) {
            input
        } else {
            throw RuntimeException("oh no!")
        }
    }

    fun part2(input: List<Int>, target: Int): Pair<Int, Int>? {
        return (0..99)
                .asSequence()
                .flatMap { noun -> (0..99).asSequence().map { Pair(noun, it) } }
                .find { (noun, verb) ->
                    val mutableInput = input.toMutableList()
                    mutableInput[1] = noun
                    mutableInput[2] = verb
                    run(mutableInput)[0] == target
                }
    }
}