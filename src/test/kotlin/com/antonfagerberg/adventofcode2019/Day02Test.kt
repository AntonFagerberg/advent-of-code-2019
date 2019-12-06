package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day02Test : StringSpec({

    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_02")!!.toURI()))[0].split(",").map { it.toInt() }

    "run example 0 (part 1)" {
        Day02.run(mutableListOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)) shouldBe mutableListOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)
    }

    "run example 1 (part 1)" {
        Day02.run(mutableListOf(1, 0, 0, 0, 99)) shouldBe mutableListOf(2, 0, 0, 0, 99)
    }

    "run example 2 (part 1)" {
        Day02.run(mutableListOf(2, 3, 0, 3, 99)) shouldBe mutableListOf(2, 3, 0, 6, 99)
    }

    "run example 3 (part 1)" {
        Day02.run(mutableListOf(2, 4, 4, 5, 99, 0)) shouldBe mutableListOf(2, 4, 4, 5, 99, 9801)
    }

    "run example 4 (part 1)" {
        Day02.run(mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99)) shouldBe mutableListOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
    }

    "run (part 1)" {
        val mutableInput = input.toMutableList()
        mutableInput[1] = 12
        mutableInput[2] = 2
        Day02.run(mutableInput)[0] shouldBe 4484226
    }

    "run part(2)" {
        val nounVerb = Day02.part2(input, 19690720)

        nounVerb shouldNotBe null

        if (nounVerb != null) {
            val (noun, verb) = nounVerb
            noun * 100 + verb shouldBe 5696
        }
    }

})
