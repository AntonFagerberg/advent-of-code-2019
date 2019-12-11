package com.antonfagerberg.adventofcode2019

import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

object Day10 {
    fun buildMap(input: Iterable<String>): Set<Pair<Int, Int>> {
        return input.withIndex().fold(setOf()) { acc, indexedRow ->
            indexedRow.value.withIndex().fold(acc) { accInner, indexedValue ->
                when (indexedValue.value) {
                    '#' -> accInner + setOf(Pair(indexedValue.index, indexedRow.index))
                    '.' -> accInner
                    else -> throw IllegalStateException()
                }
            }
        }
    }

    fun maxLineOfSight(asteroidMap: Set<Pair<Int, Int>>): Triple<Int, Int, Int>? =
            asteroidMap.map { (x, y) ->
                val angles = asteroidMap.fold(setOf<Double>()) { acc, (xx, yy) ->
                    if (x == xx && y == yy) acc
                    else acc + atan2(y.toDouble() - yy, x.toDouble() - xx)
                }
                Triple(x, y, angles.size)
            }.maxBy { it.third }

    fun part1(input: Iterable<String>): Triple<Int, Int, Int>? = maxLineOfSight(buildMap(input))

    fun part2(input: Iterable<String>): List<Pair<Int, Int>> {
        val asteroidMap = buildMap(input)
        val (targetX, targetY, _) = maxLineOfSight(asteroidMap)!!

        val targetsByAngle =
                asteroidMap
                        .fold(mapOf<Double, List<Pair<Int, Int>>>()) { acc, (x, y) ->
                            if (x == targetX && y == targetY) acc
                            else {
                                val angle = atan2(y.toDouble() - targetY, x.toDouble() - targetX)
                                acc + Pair(angle, (acc[angle] ?: listOf()) + Pair(x, y))
                            }
                        }
                        .toList()
                        .sortedBy { it.first }

        val start = targetsByAngle.dropWhile { (angle, _) -> angle < -Math.PI / 2 }
        val end = targetsByAngle.takeWhile { (angle, _) -> angle < -Math.PI / 2 }

        val fireOrder =
                (start + end)
                        .map {
                            it.second
                                    .sortedBy { (x, y) -> sqrt((x.toDouble() - targetX).pow(2) + (y.toDouble() - targetY).pow(2)) }
                                    .toMutableList()
                        }
                        .toMutableList()

        val result = mutableListOf<Pair<Int, Int>>()

        while (fireOrder.isNotEmpty()) {
            fireOrder.forEach { result.add(it.removeAt(0)) }
            fireOrder.removeIf { it.isEmpty() }
        }

        return result
    }
}