package com.antonfagerberg.adventofcode2019

object Day09 {

    class Computer(private val program: MutableMap<Long, Long>) {
        val input = mutableListOf<Long>()

        var position = 0L
        var relativeBase = 0L

        fun addInput(value: Long): Computer {
            input.add(value)
            return this
        }

        fun getFullOutput(): List<Long> {
            val value = getOutput()
            return if (value == null) listOf()
            else listOf(value) + getFullOutput()
        }

        fun getOutput(): Long? = execute()

        private fun getParam(mode: Long, offset: Long): Long = when (mode) {
            0L -> program.getOrDefault(program.getOrDefault(position + offset, 0), 0)
            1L -> program.getOrDefault(position + offset, 0)
            2L -> program.getOrDefault(program.getOrDefault(position + offset, 0) + relativeBase, 0)
            else -> throw IllegalStateException()
        }

        private tailrec fun execute(): Long? {
            val instruction = program[position]!!
            val operation = instruction % 100
            val paramMode1 = instruction / 100 % 10
            val paramMode2 = instruction / 1000 % 10
            val paramMode3 = instruction / 10000 % 10

            return when (operation) {
                1L, 2L -> {
                    val param1 = getParam(paramMode1, 1)
                    val param2 = getParam(paramMode2, 2)

                    val value = if (operation == 1L) param1 + param2 else param1 * param2

                    when (paramMode3) {
                        0L -> program[program[position + 3]!!] = value
                        2L -> program[program[position + 3]!! + relativeBase] = value
                        else -> throw IllegalArgumentException("")
                    }

                    position += 4
                    execute()
                }

                3L -> {
                    val value = input.removeAt(0)

                    when (paramMode1) {
                        0L -> program[program[position + 1]!!] = value
                        2L -> program[program[position + 1]!! + relativeBase] = value
                        else -> throw IllegalArgumentException("")
                    }

                    position += 2
                    execute()
                }

                4L -> {
                    val value = getParam(paramMode1, 1)
                    position += 2
                    value
                }

                5L, 6L -> {
                    val param1 = getParam(paramMode1, 1)
                    val param2 = getParam(paramMode2, 2)

                    position =
                            if ((operation == 5L && param1 != 0L) || (operation == 6L && param1 == 0L)) param2
                            else position + 3

                    execute()
                }

                7L, 8L -> {
                    val param1 = getParam(paramMode1, 1)
                    val param2 = getParam(paramMode2, 2)

                    val value =
                            if ((operation == 7L && param1 < param2) || (operation == 8L && param1 == param2)) 1L
                            else 0L

                    when (paramMode3) {
                        0L -> program[program[position + 3]!!] = value
                        2L -> program[program[position + 3]!! + relativeBase] = value
                        else -> throw IllegalStateException()
                    }

                    position += 4
                    execute()
                }

                9L -> {
                    val param1 = getParam(paramMode1, 1)
                    relativeBase += param1
                    position += 2
                    execute()
                }

                99L -> null

                else -> throw IllegalStateException()
            }
        }
    }

    fun parse(input: String): MutableMap<Long, Long> {
        return input.split(',').withIndex().fold(mutableMapOf()) { a, b ->
            a[b.index.toLong()] = b.value.toLong()
            a
        }
    }

}