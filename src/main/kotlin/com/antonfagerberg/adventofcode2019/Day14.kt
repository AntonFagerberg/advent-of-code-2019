package com.antonfagerberg.adventofcode2019

object Day14 {

    fun part1(input: List<String>): Long {
        val reactions = parse(input)

        val factories = mutableMapOf(Pair("ORE", Factory { 1 }))
        reactions.forEach { (inputs, outputs) ->
            factories.put(outputs.second,
                    Factory { inputs.map { factories[it.second]!!.allocate(it.first) }; outputs.first })
        }

        factories["FUEL"]!!.allocate(1)
        return factories["ORE"]!!.total
    }

    fun part2(input: List<String>): Int {
        val reactions = parse(input)

        val factories = mutableMapOf(Pair("ORE", Factory { 1 }))
        reactions.forEach { (inputs, outputs) ->
            factories.put(outputs.second,
                    Factory { inputs.map { factories[it.second]!!.allocate(it.first) }; outputs.first })
        }
        var fuel = 0

        while (true) {
            factories["FUEL"]!!.allocate(1)
            if (factories["ORE"]!!.total <= 1000000000000L) {
                fuel++
            } else {
                return fuel
            }
        }
    }

    private fun parse(input: List<String>) =
            input.map { it.split(" => ") }
                    .map {
                        val input = it[0]!!.split(", ")
                        val output = it[1]!!.split(" ")
                        Pair(
                                input.map { it.split(" ") }.map { Pair(it[0]!!.toInt(), it[1]!!) },
                                Pair(output[0]!!.toInt(), output[1]!!)
                        )
                    }

    private class Factory(private val produce: () -> Int) {
        var total = 0L
        var units = 0

        fun allocate(required: Int) {
            units -= required

            while (units < 0) {
                val allocation = produce()
                units += allocation
                total += allocation
            }
        }
    }

}