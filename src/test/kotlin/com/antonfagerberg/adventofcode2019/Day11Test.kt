package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day11Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_11")!!.toURI()))[0]

    "part 1" {
        Day11.part1(input) shouldBe 2428
    }

    "part 2" {
        Day11.part2(input) shouldBe
                " ███    ██ █    ████ ███  █  █  ██  █  █   \n" +
                " █  █    █ █    █    █  █ █  █ █  █ █  █   \n" +
                " █  █    █ █    ███  ███  █  █ █    █  █   \n" +
                " ███     █ █    █    █  █ █  █ █    █  █   \n" +
                " █ █  █  █ █    █    █  █ █  █ █  █ █  █   \n" +
                " █  █  ██  ████ █    ███   ██   ██   ██    \n"
    }

})
