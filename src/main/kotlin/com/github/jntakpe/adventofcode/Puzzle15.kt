package com.github.jntakpe.adventofcode

fun main() {
    readInputLines(15)
        .map { it.toLine() }
        .run { Puzzle15.Program(this) }
        .run {
            while (hasNext() && firstTime()) {
                next()
            }
            println(accumulator)
        }
}

private object Puzzle15 {
    enum class Instruction {
        ACC,
        JMP,
        NOP
    }
    data class Line(val instruction: Instruction, val value: Int)

    class Program(private val lines: List<Line>): Iterator<Line> {

        private var index = 0
        private var indexesExecuted = mutableListOf<Int>()
        var accumulator: Int = 0

        fun firstTime() = !indexesExecuted.contains(index)

        override fun hasNext() = index < lines.size - 1

        override fun next(): Line {
            val currentLine = lines[index]
            indexesExecuted.add(index)
            return when (currentLine.instruction) {
                Instruction.ACC -> {
                    accumulator += currentLine.value
                    lines[index++]
                }
                Instruction.JMP -> {
                    index += currentLine.value
                    lines[index]
                }
                Instruction.NOP -> lines[index++]
            }
        }
    }
}

private fun String.toLine(): Puzzle15.Line {
    val (start, end) = split(" ")
    return Puzzle15.Line(Puzzle15.Instruction.valueOf(start.toUpperCase()), end.toInt())
}
