package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day01Test : StringSpec({

    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_01").toURI())).map { it.toInt() }

    "as mass of 12 (part 1)" {
        Day01.calculateFuelPart1(12) shouldBe 2
    }

    "as mass of 14 (part 1)" {
        Day01.calculateFuelPart1(14) shouldBe 2
    }

    "as mass of 1969 (part 1)" {
        Day01.calculateFuelPart1(1969) shouldBe 654
    }

    "as mass of 100756 (part 1)" {
        Day01.calculateFuelPart1(100756) shouldBe 33583
    }

    "solution (part 1)" {
        Day01.solve(input, Day01::calculateFuelPart1) shouldBe 3342050
    }

    "as mass of 14 (part 2)" {
        Day01.calculateFuelPart2(14) shouldBe 2
    }

    "as mass of 654 (part 2)" {
        Day01.calculateFuelPart2(1969) shouldBe 966
    }

    "as mass of 100756 (part 2)" {
        Day01.calculateFuelPart2(100756) shouldBe 50346
    }

    "solution (part 2)" {
        Day01.solve(input, Day01::calculateFuelPart2) shouldBe 5010211
    }

})
