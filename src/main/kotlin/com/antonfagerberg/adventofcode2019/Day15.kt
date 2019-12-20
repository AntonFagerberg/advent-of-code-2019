package com.antonfagerberg.adventofcode2019

import java.lang.IllegalStateException

object Day15 {

    fun part1(input: String) =
            findOxygen(setOf(Pair(Day09.Computer(Day09.parse(input)), Pair(0, 0))), mutableSetOf(Pair(0, 0))).first

    fun part2(input: String): Int {
        val (_, oxygenPosition) = findOxygen(setOf(Pair(Day09.Computer(Day09.parse(input)), Pair(0, 0))), mutableSetOf(Pair(0, 0)))
        val oxygenMap = createMap(setOf(Pair(Day09.Computer(Day09.parse(input)), Pair(0, 0))), mutableSetOf(Pair(0, 0)))

        return spreadOxygen(mutableSetOf(oxygenPosition), oxygenMap)
    }

    fun move(computers: Set<Pair<Day09.Computer, Pair<Int, Int>>>, movementMap: MutableSet<Pair<Int, Int>>) =
            computers
                    .flatMap { (computer, position) ->
                        (1L..4L).flatMap {
                            // north (1), south (2), west (3), and east (4)
                            val newPosition = when (it) {
                                1L -> Pair(position.first, position.second + 1)
                                2L -> Pair(position.first, position.second - 1)
                                3L -> Pair(position.first - 1, position.second)
                                4L -> Pair(position.first + 1, position.second)
                                else -> throw IllegalStateException()
                            }

                            if (movementMap.contains(newPosition)) {
                                listOf()
                            } else {
                                val newComputer = Day09.Computer(computer.program.toMutableMap())
                                newComputer.position = computer.position
                                newComputer.relativeBase = computer.relativeBase
                                newComputer.addInput(it)
                                val output = newComputer.getOutput()!!
                                if (output == 1L || output == 2L) {
                                    movementMap.add(newPosition)
                                }
                                listOf(Pair(output, Pair(newComputer, newPosition)))
                            }
                        }
                    }
                    .groupBy { (output, _) -> output }
                    .map { (key, values) -> Pair(key.toInt(), values.map { it.second }) }
                    .toMap()

    private fun findOxygen(computers: Set<Pair<Day09.Computer, Pair<Int, Int>>>, movementMap: MutableSet<Pair<Int, Int>>, steps: Int = 1): Pair<Int, Pair<Int, Int>> {
        val result = move(computers, movementMap)

        return if (result.containsKey(2)) Pair(steps, result[2]!!.first().second)
        else findOxygen(result[1]!!.toSet(), movementMap, steps + 1)
    }

    private fun createMap(computers: Set<Pair<Day09.Computer, Pair<Int, Int>>>, movementMap: MutableSet<Pair<Int, Int>>): MutableSet<Pair<Int, Int>> {
        val result = move(computers, movementMap)

        return if (!result.containsKey(1)) movementMap
        else createMap(result[1]!!.toSet(), movementMap)
    }

    private fun spreadOxygen(filled: MutableSet<Pair<Int, Int>>, notFilled: MutableSet<Pair<Int, Int>>): Int {
        return if (filled.containsAll(notFilled)) 0
        else {
            filled
                    .flatMap { (x, y) ->
                        setOf(
                                Pair(x + 1, y),
                                Pair(x - 1, y),
                                Pair(x, y + 1),
                                Pair(x, y - 1)
                        )
                    }
                    .filter(notFilled::contains)
                    .forEach { filled.add(it) }

            1 + spreadOxygen(filled, notFilled)
        }
    }

}