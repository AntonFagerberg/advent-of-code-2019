package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day15Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_15")!!.toURI()))[0]

    "part 1" {
        Day15.part1(input) shouldBe 228
    }

    "part 2" {
        Day15.part2(input) shouldBe 348
    }
})
