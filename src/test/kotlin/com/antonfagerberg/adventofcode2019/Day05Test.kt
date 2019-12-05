package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day05Test : StringSpec({

    "part 1" {
        val input =
                Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_05").toURI()))[0]
                        .split(",").map { it.toInt() }.toTypedArray()

        Day05.run(input, 0, mutableListOf(), 1).last() shouldBe 4511442
    }

    "part 2" {
        val input =
                Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_05").toURI()))[0]
                        .split(",").map { it.toInt() }.toTypedArray()

        Day05.run(input, 0, mutableListOf(), 5).last() shouldBe 12648139
    }

})
