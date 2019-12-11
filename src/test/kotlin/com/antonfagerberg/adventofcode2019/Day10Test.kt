package com.antonfagerberg.adventofcode2019

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Path

class Day10Test : StringSpec({
    val input = Files.readAllLines(Path.of(javaClass.classLoader.getResource("input_10")!!.toURI()))

    "part 1 example 1" {
        val exampleInput = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"

        Day10.part1(exampleInput.split('\n')) shouldBe Triple(3, 4, 8)
    }

    "part 1 example 2" {
        val exampleInput = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####"

        Day10.part1(exampleInput.split('\n')) shouldBe Triple(5, 8, 33)
    }

    "part 1 example 3" {
        val exampleInput = "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###."

        Day10.part1(exampleInput.split('\n')) shouldBe Triple(1, 2, 35)
    }

    "part 1 example 4" {
        val exampleInput = ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#.."

        Day10.part1(exampleInput.split('\n')) shouldBe Triple(6, 3, 41)
    }

    "part 1 example 5" {
        val exampleInput = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##\n"

        Day10.part1(exampleInput.split('\n')) shouldBe Triple(11, 13, 210)
    }

    "part 1" {
        Day10.part1(input) shouldBe Triple(19, 14, 274)
    }

    "part 2 example" {
        val exampleInput = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##\n"

        val targets = Day10.part2(exampleInput.split('\n'))

        targets.size shouldBe 299

        targets[1 - 1] shouldBe Pair(11, 12)
        targets[2 - 1] shouldBe Pair(12, 1)
        targets[3 - 1] shouldBe Pair(12, 2)
        targets[10 - 1] shouldBe Pair(12, 8)
        targets[20 - 1] shouldBe Pair(16, 0)
        targets[50 - 1] shouldBe Pair(16, 9)
        targets[100 - 1] shouldBe Pair(10, 16)
        targets[199 - 1] shouldBe Pair(9, 6)
        targets[200 - 1] shouldBe Pair(8, 2)
        targets[201 - 1] shouldBe Pair(10, 9)
        targets[299 - 1] shouldBe Pair(11, 1)
    }


    "part 2" {
        Day10.part2(input)[200 - 1] shouldBe Pair(3, 5)
    }

})
