package com.antonfagerberg.adventofcode2019

import java.lang.IllegalStateException

object Day11 {

    val translation = mapOf(
            Pair(Pair(0, 1), Pair(Pair(-1, 0), Pair(1, 0))),
            Pair(Pair(0, -1), Pair(Pair(1, 0), Pair(-1, 0))),
            Pair(Pair(1, 0), Pair(Pair(0, 1), Pair(0, -1))),
            Pair(Pair(-1, 0), Pair(Pair(0, -1), Pair(0, 1)))
    )

    fun part1(input: String): Int {
        val computer = Day09.Computer(Day09.parse(input))
        return travel(computer, Pair(0, 0), Pair(0, 1), mapOf()).size
    }

    fun part2(input: String): String {
        val computer = Day09.Computer(Day09.parse(input))
        val result = travel(computer, Pair(0, 0), Pair(0, 1), mapOf(Pair(Pair(0, 0), listOf(1))))

        val minX = result.keys.map { it.first }.min()!!
        val maxX = result.keys.map { it.first }.max()!!
        val minY = result.keys.map { it.second }.min()!!
        val maxY = result.keys.map { it.second }.max()!!

        return (minY..maxY).reversed().fold("") { accY, y ->
            (minX..maxX).fold(accY) { accX, x ->
                accX + if ((result[Pair(x, y)]?.last() ?: 0) == 1) "█" else " "
            } + "\n"
        }
    }


    tailrec fun travel(computer: Day09.Computer, coordinate: Pair<Int, Int>, direction: Pair<Int, Int>, state: Map<Pair<Int, Int>, List<Int>>): Map<Pair<Int, Int>, List<Int>> {
        val color = state[coordinate]?.last() ?: 0
        val newState =
                when (computer.addInput(color.toLong()).getOutput()) {
                    null -> return state
                    0L -> state + Pair(coordinate, (state[coordinate] ?: listOf()) + 0)
                    1L -> state + Pair(coordinate, (state[coordinate] ?: listOf()) + 1)
                    else -> throw IllegalStateException()
                }

        val newDirection =
                when (computer.getOutput()) {
                    0L -> translation[direction]!!.first
                    1L -> translation[direction]!!.second
                    else -> throw IllegalStateException()
                }

        val newCoordinate = Pair(coordinate.first + newDirection.first, coordinate.second + newDirection.second)

        return travel(computer, newCoordinate, newDirection, newState)
    }

}