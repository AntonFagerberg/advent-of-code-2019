package com.antonfagerberg.adventofcode2019

object Day07 {

    tailrec fun run(program: Array<Int>, position: Int, output: MutableList<Int>, input: List<Int>): List<Int> {
        val instruction = program[position]
        val operation = instruction % 100
        val paramMode1 = instruction / 100 % 10 == 1
        val paramMode2 = instruction / 1000 % 10 == 1

        return when (operation) {
            1, 2 -> {
                val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]
                program[program[position + 3]] = if (operation == 1) param1 + param2 else param1 * param2
                run(program, position + 4, output, input)
            }

            3 -> {
                program[program[position + 1]] = input[0]
                run(program, position + 2, output, input.drop(1))
            }

            4 -> {
                val value = if (paramMode1) program[position + 1] else program[program[position + 1]]
                output.add(value)
                run(program, position + 2, output, input)
            }

            5, 6 -> {
                val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]

                if ((operation == 5 && param1 != 0) || (operation == 6 && param1 == 0)) {
                    run(program, param2, output, input)
                } else {
                    run(program, position + 3, output, input)
                }
            }

            7, 8 -> {
                val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]

                if ((operation == 7 && param1 < param2) || (operation == 8 && param1 == param2)) {
                    program[program[position + 3]] = 1
                } else {
                    program[program[position + 3]] = 0
                }

                run(program, position + 4, output, input)
            }

            99 -> output

            else -> throw RuntimeException("oh no!")
        }
    }

    fun part1(program: Array<Int>): Int? {
        return permutations
                .map { phasePermutation ->
                    phasePermutation.fold(0) { input, phase ->
                        run(program.copyOf(), 0, mutableListOf(), listOf(phase, input)).last()
                    }
                }
                .max()
    }

    class Amplifier(private val program: Array<Int>) {
        var position: Int = 0

        val input = mutableListOf<Int>()

        fun addInput(value: Int): Amplifier {
            input.add(value)
            return this
        }

        fun getOutput(): Int? = execute()

        private tailrec fun execute(): Int? {
            val instruction = program[position]
            val operation = instruction % 100
            val paramMode1 = instruction / 100 % 10 == 1
            val paramMode2 = instruction / 1000 % 10 == 1

            return when (operation) {
                1, 2 -> {
                    val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                    val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]
                    program[program[position + 3]] = if (operation == 1) param1 + param2 else param1 * param2
                    position += 4
                    execute()
                }

                3 -> {
                    program[program[position + 1]] = input.removeAt(0)
                    position += 2
                    execute()
                }

                4 -> {
                    val value = if (paramMode1) program[position + 1] else program[program[position + 1]]
                    position += 2
                    value
                }

                5, 6 -> {
                    val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                    val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]

                    position =
                            if ((operation == 5 && param1 != 0) || (operation == 6 && param1 == 0)) param2
                            else position + 3

                    execute()
                }

                7, 8 -> {
                    val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                    val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]

                    if ((operation == 7 && param1 < param2) || (operation == 8 && param1 == param2)) {
                        program[program[position + 3]] = 1
                    } else {
                        program[program[position + 3]] = 0
                    }

                    position += 4
                    execute()
                }

                99 -> null

                else -> throw RuntimeException("oh no!")
            }
        }
    }

    fun part2(program: Array<Int>): Int? {
        return permutations
                .map { phases -> phases.map { it + 5 } }
                .map { phasePermutation ->
                    val amplifiers = phasePermutation.map { Amplifier(program.copyOf()).addInput(it) }

                    var lastOutputNotFound = true
                    var lastOutput: Int? = 0

                    while (lastOutputNotFound) {
                        val output = amplifiers.fold(lastOutput) { input, amp ->
                            if (input == null) null
                            else amp.addInput(input).getOutput()
                        }

                        if (output == null) lastOutputNotFound = false
                        else lastOutput = output
                    }

                    lastOutput!!
                }
                .max()
    }

    // Generated with List(0,1,2,3,4).permutations in Scala
    private val permutations: List<List<Int>> = listOf(
            listOf(0, 1, 2, 3, 4),
            listOf(0, 1, 2, 4, 3),
            listOf(0, 1, 3, 2, 4),
            listOf(0, 1, 3, 4, 2),
            listOf(0, 1, 4, 2, 3),
            listOf(0, 1, 4, 3, 2),
            listOf(0, 2, 1, 3, 4),
            listOf(0, 2, 1, 4, 3),
            listOf(0, 2, 3, 1, 4),
            listOf(0, 2, 3, 4, 1),
            listOf(0, 2, 4, 1, 3),
            listOf(0, 2, 4, 3, 1),
            listOf(0, 3, 1, 2, 4),
            listOf(0, 3, 1, 4, 2),
            listOf(0, 3, 2, 1, 4),
            listOf(0, 3, 2, 4, 1),
            listOf(0, 3, 4, 1, 2),
            listOf(0, 3, 4, 2, 1),
            listOf(0, 4, 1, 2, 3),
            listOf(0, 4, 1, 3, 2),
            listOf(0, 4, 2, 1, 3),
            listOf(0, 4, 2, 3, 1),
            listOf(0, 4, 3, 1, 2),
            listOf(0, 4, 3, 2, 1),
            listOf(1, 0, 2, 3, 4),
            listOf(1, 0, 2, 4, 3),
            listOf(1, 0, 3, 2, 4),
            listOf(1, 0, 3, 4, 2),
            listOf(1, 0, 4, 2, 3),
            listOf(1, 0, 4, 3, 2),
            listOf(1, 2, 0, 3, 4),
            listOf(1, 2, 0, 4, 3),
            listOf(1, 2, 3, 0, 4),
            listOf(1, 2, 3, 4, 0),
            listOf(1, 2, 4, 0, 3),
            listOf(1, 2, 4, 3, 0),
            listOf(1, 3, 0, 2, 4),
            listOf(1, 3, 0, 4, 2),
            listOf(1, 3, 2, 0, 4),
            listOf(1, 3, 2, 4, 0),
            listOf(1, 3, 4, 0, 2),
            listOf(1, 3, 4, 2, 0),
            listOf(1, 4, 0, 2, 3),
            listOf(1, 4, 0, 3, 2),
            listOf(1, 4, 2, 0, 3),
            listOf(1, 4, 2, 3, 0),
            listOf(1, 4, 3, 0, 2),
            listOf(1, 4, 3, 2, 0),
            listOf(2, 0, 1, 3, 4),
            listOf(2, 0, 1, 4, 3),
            listOf(2, 0, 3, 1, 4),
            listOf(2, 0, 3, 4, 1),
            listOf(2, 0, 4, 1, 3),
            listOf(2, 0, 4, 3, 1),
            listOf(2, 1, 0, 3, 4),
            listOf(2, 1, 0, 4, 3),
            listOf(2, 1, 3, 0, 4),
            listOf(2, 1, 3, 4, 0),
            listOf(2, 1, 4, 0, 3),
            listOf(2, 1, 4, 3, 0),
            listOf(2, 3, 0, 1, 4),
            listOf(2, 3, 0, 4, 1),
            listOf(2, 3, 1, 0, 4),
            listOf(2, 3, 1, 4, 0),
            listOf(2, 3, 4, 0, 1),
            listOf(2, 3, 4, 1, 0),
            listOf(2, 4, 0, 1, 3),
            listOf(2, 4, 0, 3, 1),
            listOf(2, 4, 1, 0, 3),
            listOf(2, 4, 1, 3, 0),
            listOf(2, 4, 3, 0, 1),
            listOf(2, 4, 3, 1, 0),
            listOf(3, 0, 1, 2, 4),
            listOf(3, 0, 1, 4, 2),
            listOf(3, 0, 2, 1, 4),
            listOf(3, 0, 2, 4, 1),
            listOf(3, 0, 4, 1, 2),
            listOf(3, 0, 4, 2, 1),
            listOf(3, 1, 0, 2, 4),
            listOf(3, 1, 0, 4, 2),
            listOf(3, 1, 2, 0, 4),
            listOf(3, 1, 2, 4, 0),
            listOf(3, 1, 4, 0, 2),
            listOf(3, 1, 4, 2, 0),
            listOf(3, 2, 0, 1, 4),
            listOf(3, 2, 0, 4, 1),
            listOf(3, 2, 1, 0, 4),
            listOf(3, 2, 1, 4, 0),
            listOf(3, 2, 4, 0, 1),
            listOf(3, 2, 4, 1, 0),
            listOf(3, 4, 0, 1, 2),
            listOf(3, 4, 0, 2, 1),
            listOf(3, 4, 1, 0, 2),
            listOf(3, 4, 1, 2, 0),
            listOf(3, 4, 2, 0, 1),
            listOf(3, 4, 2, 1, 0),
            listOf(4, 0, 1, 2, 3),
            listOf(4, 0, 1, 3, 2),
            listOf(4, 0, 2, 1, 3),
            listOf(4, 0, 2, 3, 1),
            listOf(4, 0, 3, 1, 2),
            listOf(4, 0, 3, 2, 1),
            listOf(4, 1, 0, 2, 3),
            listOf(4, 1, 0, 3, 2),
            listOf(4, 1, 2, 0, 3),
            listOf(4, 1, 2, 3, 0),
            listOf(4, 1, 3, 0, 2),
            listOf(4, 1, 3, 2, 0),
            listOf(4, 2, 0, 1, 3),
            listOf(4, 2, 0, 3, 1),
            listOf(4, 2, 1, 0, 3),
            listOf(4, 2, 1, 3, 0),
            listOf(4, 2, 3, 0, 1),
            listOf(4, 2, 3, 1, 0),
            listOf(4, 3, 0, 1, 2),
            listOf(4, 3, 0, 2, 1),
            listOf(4, 3, 1, 0, 2),
            listOf(4, 3, 1, 2, 0),
            listOf(4, 3, 2, 0, 1),
            listOf(4, 3, 2, 1, 0)
    )

}