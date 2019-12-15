package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day12Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_12")!!.toURI()))

    "part 1 example 1" {
        val exampleInput = "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>"

        Day12.part1(exampleInput, 10) shouldBe 179
    }

    "part 1 example 2" {
        val exampleInput = "<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>"

        Day12.part1(exampleInput, 100) shouldBe 1940
    }

    "part 1" {
        Day12.part1(input.joinToString("\n"), 1000) shouldBe 7758
    }

    "part 2 example 1" {
        val exampleInput = "<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>"

        Day12.part2(exampleInput) shouldBe 2772
    }

    "part 2" {
        Day12.part2(input.joinToString("\n")) shouldBe 2772
    }

})
