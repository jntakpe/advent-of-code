package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle16.Instruction
import com.github.jntakpe.adventofcode.Puzzle16.Line
import com.github.jntakpe.adventofcode.Puzzle16.Program

fun main() {
    readInputLines(15)
        .map { it.toLine() }
        .run { Program(this).run() }
}

private object Puzzle16 {
    enum class Instruction {
        ACC,
        JMP,
        NOP
    }

    data class Line(val instruction: Instruction, val value: Int)

    class Program(private var lines: List<Line>) : Iterator<Line> {

        private var accumulator = 0
        private var index = 0
        private var indexesExecuted = mutableListOf<Int>()

        fun run() {
            fix()
            reset()
            walkList()
            println(accumulator)
        }

        private fun fix() {
            walkList()
            val loopIndexes = indexesExecuted.groupBy { it }.filter { it.value.size >= 2 }.map { it.key }
            var loopIndex = loopIndexes.size - 1
            reset()
            var fixedList = fix(lines, loopIndexes[loopIndex])
            while (fixedList.isEmpty()) {
                reset()
                fixedList = fix(lines, loopIndexes[loopIndex--])
            }
        }

        private fun fix(lines: List<Line>, currentIndex: Int): List<Line> {
            val edited = lines.toMutableList()
            val line = edited[currentIndex]
            val newLine = when (line.instruction) {
                Instruction.NOP -> line.copy(instruction = Instruction.JMP)
                Instruction.JMP -> line.copy(instruction = Instruction.NOP)
                else -> line
            }
            edited.removeAt(currentIndex)
            edited.add(currentIndex, newLine)
            this.lines = edited
            walkList()
            return if (hasNext()) {
                this.lines = lines
                emptyList()
            } else edited
        }

        private fun walkList() {
            while (hasNext() && !loopDetected()) {
                next()
            }
        }

        private fun reset() {
            accumulator = 0
            index = 0
            indexesExecuted = mutableListOf()
        }

        private fun loopDetected() = indexesExecuted.filter { it == index }.count() > 2

        override fun hasNext() = index < lines.size

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
                    if (index < lines.size) lines[index] else lines[lines.size - 1]
                }
                Instruction.NOP -> lines[index++]
            }
        }
    }
}

private fun String.toLine(): Line {
    val (start, end) = split(" ")
    return Line(Instruction.valueOf(start.toUpperCase()), end.toInt())
}
