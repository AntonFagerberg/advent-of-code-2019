package com.antonfagerberg.adventofcode2019

object Day13 {
    fun part1(input: String): Int {
        val computer = Day09.Computer(Day09.parse(input))

        return generateSequence { computer.getOutput() }
                .windowed(3, 3)
                .count { it[2] == 2L }
    }

    fun part2(input: String): Long {
        val program = Day09.parse(input)
        program[0] = 2

        val computer = Day09.Computer(program)
        val screen = mutableMapOf<Pair<Long, Long>, Char>()
        var score = 0L
        var blockCount = 0
        var paddleX = 0L
        var ballX = 0L

        do {
            generateSequence {
                try {
                    computer.getOutput()
                } catch (e: IndexOutOfBoundsException) {
                    null
                }
            }.windowed(3, 3)
                    .forEach {
                        if (it[0] == -1L && it[1] == 0L) {
                            score = it[2]
                        } else {
                            val gfx =
                                    when (it[2]) {
                                        0L -> {
                                            ' '
                                        }
                                        1L -> '█'
                                        2L -> {
                                            blockCount += 1
                                            '░'
                                        }
                                        3L -> {
                                            paddleX = it[0]
                                            '^'
                                        }
                                        4L -> {
                                            ballX = it[0]
                                            '●'
                                        }
                                        else -> throw IllegalStateException()

                                    }
                            if (screen[Pair(it[0], it[1])] == '░' && gfx == ' ') {
                                blockCount--
                            }

                            screen[Pair(it[0], it[1])] = gfx
                        }
                    }

//            UNCOMMENT TO SHOW SCREEN
//
//            val minX = screen.keys.map { it.first }.min()!!
//            val maxX = screen.keys.map { it.first }.max()!!
//            val minY = screen.keys.map { it.second }.min()!!
//            val maxY = screen.keys.map { it.second }.max()!!
//
//            println(
//                    (minY..maxY).fold("") { accY, y ->
//                        (minX..maxX).fold(accY) { accX, x ->
//                            accX + (screen[Pair(x, y)] ?: ' ')
//                        } + "\n"
//                    }
//            )

            computer.addInput(
                    when {
                        ballX < paddleX -> -1L
                        ballX > paddleX -> 1L
                        else -> 0L
                    }
            )
        } while (blockCount > 0)

        return score
    }
}