package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day09Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_09")!!.toURI()))[0]

    "part 1 example 1" {
        val exampleInput = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"
        Day09.Computer(Day09.parse(exampleInput)).getFullOutput() shouldBe exampleInput.split(',').map { it.toLong() }
    }

    "part 1 example 2" {
        val exampleInput = "1102,34915192,34915192,7,4,7,99,0"
        Day09.Computer(Day09.parse(exampleInput)).getFullOutput() shouldBe listOf(1219070632396864L)
    }

    "part 1 example 3" {
        val exampleInput = "104,1125899906842624,99"
        Day09.Computer(Day09.parse(exampleInput)).getFullOutput() shouldBe listOf(1125899906842624L)
    }

    "part 1" {
        Day09.Computer(Day09.parse(input)).addInput(1).getFullOutput() shouldBe listOf(3340912345L)
    }

    "part 2" {
        Day09.Computer(Day09.parse(input)).addInput(2).getFullOutput() shouldBe listOf(51754L)
    }


})
