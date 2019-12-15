package com.antonfagerberg.adventofcode2019

import kotlin.math.abs

object Day12 {
    data class Point(val x: Int, val y: Int, val z: Int)

    fun part1(input: String, steps: Int) =
            simulate(parse(input))
                    .take(steps + 1)
                    .last()
                    .fold(0) { acc, (position, vector) ->
                        acc + (abs(position.x) + abs(position.y) + abs(position.z)) *
                                (abs(vector.x) + abs(vector.y) + abs(vector.z))
                    }

    fun part2(input: String): Long? {
        val target = parse(input)

        return generateSequence(1L) { it + 1 }
                .zip(simulate(target))
                .drop(1)
                .find { (_, value) ->
                    value.map { it.first } == target
                }
                ?.first
    }

    private fun simulate(input: List<Point>) =
            generateSequence(input.map { Pair(it, Point(0, 0, 0)) }) { previousVector ->
                previousVector.map { (previousPosition, previousVelocity) ->
                    val velocity = previousVector.map { (other, _) ->
                        Point(gravity(previousPosition.x - other.x),
                                gravity(previousPosition.y - other.y),
                                gravity(previousPosition.z - other.z))
                    }.fold(previousVelocity) { v1, v2 -> Point(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z) }

                    val position =
                            Point(previousPosition.x + velocity.x,
                                    previousPosition.y + velocity.y,
                                    previousPosition.z + velocity.z)

                    Pair(position, velocity)
                }
            }

    private fun gravity(diff: Int) =
            if (diff < 0) 1
            else if (diff > 0) -1
            else 0

    private fun parse(input: String): List<Point> =
            input.split('\n')
                    .map {
                        it.split(',').map {
                            it.filter { it.toString().matches(Regex("(-|[0-9])")) }.toInt()
                        }
                    }
                    .map { Point(it[0], it[1], it[2]) }
}