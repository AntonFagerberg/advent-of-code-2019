package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day06Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_06").toURI()))

    "part 1 example 1" {
        val example = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L"

        Day06.part1(example.split("\n"), "COM") shouldBe 42
    }

    "part 1" {
        Day06.part1(input, "COM") shouldBe 322508
    }


    "part 2 example 1" {
        val example = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L\n" +
                "K)YOU\n" +
                "I)SAN"

        Day06.part2(example.split("\n"), "YOU", "SAN") shouldBe 4
    }

    "part 2" {
        Day06.part2(input, "YOU", "SAN") shouldBe 496
    }

})
