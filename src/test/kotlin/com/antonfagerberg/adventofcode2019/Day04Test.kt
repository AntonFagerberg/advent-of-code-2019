package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day04Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_04")!!.toURI()))

    "part 1" {
        Day04.solve(input[0], Day04::validatePart1) shouldBe 511
    }

    "part 2" {
        Day04.solve(input[0], Day04::validatePart2) shouldBe 316
    }

})
