package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day13Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_13")!!.toURI()))[0]

    "part 1" {
        Day13.part1(input) shouldBe 291
    }
})
