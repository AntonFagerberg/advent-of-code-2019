package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day07Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_07")!!.toURI()))[0].split(",").map(String::toInt).toTypedArray()

    "part 1 example 1" {
        val program = arrayOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
        Day07.part1(program) shouldBe 43210
    }

    "part 1 example 2" {
        val program = arrayOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        Day07.part1(program) shouldBe 54321
    }

    "part 1 example 3" {
        val program = arrayOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)
        Day07.part1(program) shouldBe 65210
    }

    "part 1" {
        Day07.part1(input) shouldBe 17440
    }

    "part 2 example 1" {
        val program = arrayOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        Day07.part2(program) shouldBe 139629729
    }

    "part 2 example 2" {
        val program = arrayOf(3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54, -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4, 53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10)
        Day07.part2(program) shouldBe 18216
    }

    "part 2" {
        Day07.part2(input) shouldBe 27561242
    }

})
