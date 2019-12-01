package day01

import java.nio.file.Files
import java.nio.file.Path

object Day01 {
    fun calculatePart1(value: Int): Int = value / 3 - 2

    fun calculatePart2(value: Int): Int {
        val result = calculatePart1(value)
        return if (result <= 0) 0 else result + calculatePart2(result);
    }

    fun part1(): Int {
        return Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_01").toURI()))
                .map { it.toInt() }
                .map { calculatePart1(it) }
                .sum()
    }

    fun part2(): Int {
        return Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_01").toURI()))
                .map { it.toInt() }
                .map { calculatePart2(it) }
                .sum()
    }
}

fun main() {
    println(Day01.calculatePart1(12))
    println(Day01.calculatePart1(14))
    println(Day01.calculatePart1(1969))
    println(Day01.calculatePart1(100756))
    println(Day01.part1())
    println("---")
    println(Day01.calculatePart2(14))
    println(Day01.calculatePart2(1969))
    println(Day01.calculatePart2(100756))
    println(Day01.part2())
}