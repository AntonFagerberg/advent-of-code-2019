package com.antonfagerberg.adventofcode2019

object Day05 {
    tailrec fun run(program: Array<Int>, position: Int, output: MutableList<Int>, input: Int? = null): List<Int> {
        val instruction = program[position]
        val operation = instruction % 100
        val paramMode1 = instruction / 100 % 10 == 1
        val paramMode2 = instruction / 1000 % 10 == 1

        return when (operation) {
            1, 2 -> {
                val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]
                program[program[position + 3]] = if (operation == 1) param1 + param2 else param1 * param2
                run(program, position + 4, output)
            }

            3 -> {
                program[program[position + 1]] = input!!
                run(program, position + 2, output)
            }

            4 -> {
                val value = if (paramMode1) program[position + 1] else program[program[position + 1]]
                output.add(value)
                run(program, position + 2, output)
            }

            5, 6 -> {
                val param1 = if (paramMode1) program[position + 1] else program[program[position + 1]]
                val param2 = if (paramMode2) program[position + 2] else program[program[position + 2]]

                if ((operation == 5 && param1 != 0) || (operation == 6 && param1 == 0)) {
                    run(program, param2, output)
                } else {
                    run(program, position + 3, output)
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

                run(program, position + 4, output)
            }

            99 -> output

            else -> throw RuntimeException("oh no!")
        }
    }
}