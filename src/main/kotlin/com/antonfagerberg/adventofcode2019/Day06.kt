package com.antonfagerberg.adventofcode2019

object Day06 {
    private fun parse(input: List<String>): List<Pair<String, String>> =
            input.map { it.split(")") }.map { Pair(it[0], it[1]) }

    private fun orbitCount(map: Map<String, Set<String>>, positions: Set<String>, depth: Int = 1): Int {
        return if (positions.isEmpty()) {
            0
        } else {
            val next = positions.flatMap { map[it] ?: emptySet() }.toSet()
            next.size * depth + orbitCount(map, next, depth + 1)
        }
    }

    private fun distance(map: Map<String, Set<String>>, reach: Set<String>, destination: String): Int {
        return if (reach.contains(destination)) {
            0
        } else {
            val next = reach.flatMap { map[it] ?: emptySet() }.toSet()
            1 + distance(map, reach + next, destination)
        }
    }

    fun part1(input: List<String>, start: String): Int {
        val orbitMap = parse(input).foldRight(emptyMap<String, Set<String>>(), { pair, acc ->
            acc + Pair(pair.first, (acc[pair.first] ?: emptySet()) + pair.second)
        })

        return orbitCount(orbitMap, setOf(start))
    }

    fun part2(input: List<String>, start: String, destination: String): Int {
        val orbitMap = parse(input).foldRight(emptyMap<String, Set<String>>(), { orbit, acc ->
            acc +
                    Pair(orbit.first, (acc[orbit.first] ?: emptySet()) + orbit.second) +
                    Pair(orbit.second, (acc[orbit.second] ?: emptySet()) + orbit.first)
        })

        return distance(orbitMap, orbitMap[start]!!, orbitMap[destination]!!.first())
    }
}