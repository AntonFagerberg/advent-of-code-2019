package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day08Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_08")!!.toURI()))[0].map(Char::toString).map(String::toInt)

    "part 1" {
        Day08.part1(input, 25, 6) shouldBe 1206
    }

    "part 2" {
        Day08.part2(input, 25, 6) shouldBe
                "####   ## ###   ##  ###  \n" +
                "#       # #  # #  # #  # \n" +
                "###     # #  # #    #  # \n" +
                "#       # ###  # ## ###  \n" +
                "#    #  # # #  #  # #    \n" +
                "####  ##  #  #  ### #    "
    }

})
