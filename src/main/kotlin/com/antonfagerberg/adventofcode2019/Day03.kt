package com.antonfagerberg.adventofcode2019

import kotlin.math.abs

object Day03 {

    fun parse(line: String): List<Pair<Char, Int>> =
            line.split(",").map { Pair(it[0], it.drop(1).toInt()) }

    fun walk(instructions: List<Pair<Char, Int>>, position: Pair<Int, Int> = Pair(0, 0), stepCount: Int = 0, trail: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): MutableMap<Pair<Int, Int>, Int> {
        return if (instructions.isEmpty()) {
            trail
        } else {
            val (direction, steps) = instructions[0]

            val newSteps = when (direction) {
                'U' -> sequence {
                    (1..steps).forEach {
                        yield(Triple(position.first, position.second + it, stepCount + it))
                    }
                }

                'D' -> sequence {
                    (1..steps).forEach {
                        yield(Triple(position.first, position.second - it, stepCount + it))
                    }
                }

                'L' -> sequence {
                    (1..steps).forEach {
                        yield(Triple(position.first - it, position.second, stepCount + it))
                    }
                }

                'R' -> sequence {
                    (1..steps).forEach {
                        yield(Triple(position.first + it, position.second, stepCount + it))
                    }
                }

                else -> throw RuntimeException("Unknown direction")
            }

            val (x, y, newStepCount) = newSteps.last()

            newSteps.forEach { (x, y, c) ->
                trail.putIfAbsent(Pair(x, y), c)
            }

            walk(instructions.drop(1), Pair(x, y), newStepCount, trail)
        }
    }

    fun part1(input: List<String>): Int? {
        val line1 = walk(parse(input[0])).keys
        val line2 = walk(parse(input[1])).keys
        return line1.filter(line2::contains).map { abs(it.first) + abs(it.second) }.min()
    }

    fun part2(input: List<String>): Int? {
        val line1 = walk(parse(input[0]))
        val line2 = walk(parse(input[1]))
        return line1.filter { line2.containsKey(it.key) }.map { it.value + line2.getOrDefault(it.key, 0) }.min()
    }

}