package com.antonfagerberg.adventofcode2019

object Day08 {

    fun part1(input: List<Int>, width: Int, height: Int): Int {
        val chunkSize = width * height
        val frame = input.windowed(chunkSize, chunkSize).minBy { it.count { it == 0 } }!!
        return frame.filter { it == 1 }.count() * frame.filter { it == 2 }.count()
    }

    fun part2(input: List<Int>, width: Int, height: Int): String {
        val chunkSize = width * height
        val image = Array(chunkSize) { 2 }

        input.windowed(chunkSize, chunkSize).forEach { row ->
            row.withIndex().forEach { indexedValue ->
                if (image[indexedValue.index] == 2) {
                    image[indexedValue.index] = indexedValue.value
                }
            }
        }

        return image.toList()
                .windowed(width, width)
                .joinToString("\n") {
                    it.joinToString(separator = "")
                            .replace('1', '#')
                            .replace('0', ' ')
                }
    }

}