package com.antonfagerberg.adventofcode2019

import com.antonfagerberg.adventofcode2019.Day10.buildMap
import java.lang.IllegalStateException
import kotlin.math.abs
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

    fun normalizedPoint(x: Int, y: Int): Triple<Double, Boolean, Boolean> {
        return Triple(
                when {
                    x == 0 -> 0.toDouble()
                    y == 0 -> 0.toDouble()
                    else -> x / y.toDouble()
                },
                x >= 0,
                y >= 0
        )
    }


    fun lineOfSight(originX: Int, originY: Int, asteroidMap: Set<Pair<Int, Int>>): Int =
            asteroidMap.fold(setOf<Triple<Double, Boolean, Boolean>>()) { acc, (x, y) ->
                if (x == originX && y == originY) acc
                else acc + normalizedPoint(x - originX, y - originY)
            }.size

    fun part1(input: Iterable<String>): Unit {
        val asteroidMap = buildMap(input)
        println(asteroidMap.map { (x, y) -> Pair(Pair(x, y), lineOfSight(x, y, asteroidMap)) }.maxBy { it.second })
    }

    fun part2(targetX: Int, targetY: Int, input: Iterable<String>): Int? {
        val asteroidMap = buildMap(input)
        val quadMap = mutableMapOf<Int, MutableMap<Double, List<Pair<Int, Int>>>>()

        (0..3).forEach { quadMap[it] = mutableMapOf() }

        asteroidMap
                .forEach { (x, y) ->
                    if (x != targetX || y != targetY) {

//                        val (angle, xPlus, yPlus) = normalizedPoint(x - targetX, y - targetY)
//                        if (angle.isInfinite()) {
//                            println(angle)
//                        }
                        val angleMap = quadMap[0]!!
//                                if (xPlus && yPlus) quadMap[1]!!
//                                else if (xPlus && !yPlus) quadMap[0]!!
//                                else if (!xPlus && !yPlus) quadMap[3]!!
//                                else quadMap[2]!!
                        val angle = atan2(y.toDouble() - targetY, x.toDouble() - targetX)

                        angleMap[angle] = (angleMap[angle] ?: listOf()) + Pair(x, y)
                    }
                }

        val aimPlan =
                quadMap.toList()
                        .sortedBy { it.first }
                        .flatMap { (quad, angleMap) ->
                            val yo = angleMap
                                    .toList()
                                    .sortedBy { it.first }
                                    .map {
                                        it.second.sortedBy { (x, y) -> sqrt((x.toDouble() - targetX).pow(2) + (y.toDouble() - targetY).pow(2)) }.toMutableList()
                                    }
                            val i = yo.indexOfFirst { it.contains(Pair(11, 12)) }
                            yo.takeLast(yo.size - i) + yo.take(i - 1)
                        }
                        .toMutableList()

        aimPlan.forEach { println(it) }

        val aimPlan2 =
                quadMap.toList()
                        .sortedBy { it.first }
                        .map {
                            it.second
                                    .toList()
                                    .sortedBy { it.first }
                                    .map {
                                        Pair(it.first,
                                        it.second.sortedBy { (x, y) ->
                                            sqrt((x.toDouble() - targetX).pow(2) + (y.toDouble() - targetY).pow(2)) }.toMutableList())
                                    }
                        }
                        .toMutableList()

        var x = 1
        while (aimPlan.isNotEmpty()) {
            aimPlan.forEach { targets ->
                //                println(targets)
                val fired = targets.removeAt(0)
                println("$x -> $fired")
                x++
            }

            aimPlan.removeIf { it.isEmpty() }
        }

        return 1
    }
}

fun main() {
    val exampleInput = ".#..##.###...#######\n" +
            "##.############..##.\n" +
            ".#.######.########.#\n" +
            ".###.#######.####.#.\n" +
            "#####.##.#.##.###.##\n" +
            "..#####..#.#########\n" +
            "####################\n" +
            "#.####....###.#.#.##\n" +
            "##.#################\n" +
            "#####.##.###..####..\n" +
            "..######..##.#######\n" +
            "####.##.####...##..#\n" +
            ".#####..#.######.###\n" +
            "##...#.##########...\n" +
            "#.##########.#######\n" +
            ".####.#.###.###.#.##\n" +
            "....##.##.###..#####\n" +
            ".#.#.###########.###\n" +
            "#.#.#.#####.####.###\n" +
            "###.##.####.##.#..##\n"

    val t = ".##.#.#....#.#.#..##..#.#.\n" +
            "#.##.#..#.####.##....##.#.\n" +
            "###.##.##.#.#...#..###....\n" +
            "####.##..###.#.#...####..#\n" +
            "..#####..#.#.#..#######..#\n" +
            ".###..##..###.####.#######\n" +
            ".##..##.###..##.##.....###\n" +
            "#..#..###..##.#...#..####.\n" +
            "....#.#...##.##....#.#..##\n" +
            "..#.#.###.####..##.###.#.#\n" +
            ".#..##.#####.##.####..#.#.\n" +
            "#..##.#.#.###.#..##.##....\n" +
            "#.#.##.#.##.##......###.#.\n" +
            "#####...###.####..#.##....\n" +
            ".#####.#.#..#.##.#.#...###\n" +
            ".#..#.##.#.#.##.#....###.#\n" +
            ".......###.#....##.....###\n" +
            "#..#####.#..#..##..##.#.##\n" +
            "##.#.###..######.###..#..#\n" +
            "#.#....####.##.###....####\n" +
            "..#.#.#.########.....#.#.#\n" +
            ".##.#.#..#...###.####..##.\n" +
            "##...###....#.##.##..#....\n" +
            "..##.##.##.#######..#...#.\n" +
            ".###..#.#..#...###..###.#.\n" +
            "#..#..#######..#.#..#..#.#"

//    println(Day10.part1(t.split('\n')))
    Day10.part2(11, 13, exampleInput.split('\n'))
//    Day10.part2(19, 14, t.split('\n'))
    // 200 -> (5, 6) (506 too high)
}