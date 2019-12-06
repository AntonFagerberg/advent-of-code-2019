package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day03Test : StringSpec({

    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_03")!!.toURI()))

    "part1 example 0" {
        Day03.part1(
                listOf(
                        "R8,U5,L5,D3",
                        "U7,R6,D4,L4"
                )
        ) shouldBe 6
    }

    "part1 example 1" {
        Day03.part1(
                listOf(
                        "R75,D30,R83,U83,L12,D49,R71,U7,L72",
                        "U62,R66,U55,R34,D71,R55,D58,R83"
                )
        ) shouldBe 159
    }

    "part1 example 2" {
        Day03.part1(
                listOf(
                        "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
                        "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
                )
        ) shouldBe 135
    }

    "part1" {
        Day03.part1(input) shouldBe 731
    }

    "part2 example 0" {
        Day03.part2(
                listOf(
                        "R8,U5,L5,D3",
                        "U7,R6,D4,L4"
                )
        ) shouldBe 30
    }

    "part2 example 1" {
        Day03.part2(
                listOf(
                        "R75,D30,R83,U83,L12,D49,R71,U7,L72",
                        "U62,R66,U55,R34,D71,R55,D58,R83"
                )
        ) shouldBe 610
    }

    "part2 example 2" {
        Day03.part2(
                listOf(
                        "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
                        "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
                )
        ) shouldBe 410
    }

    "part2" {
        Day03.part2(input) shouldBe 5672
    }

})
