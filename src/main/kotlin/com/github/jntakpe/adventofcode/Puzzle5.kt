package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle5.Line
import kotlin.math.ceil

fun main() {
    readInputLines(5)
        .mapIndexed { i, s -> Line(s, i * 3) }
        .count { it.hasTree() }
        .run { println(this) }
}

private object Puzzle5 {

    class Line(private val pattern: String, private val position: Int) {

        fun hasTree() = fullLine()[position] == '#'

        private fun fullLine() = if (pattern.length <= position) pattern.repeat(repeatTimes()) else pattern

        private fun repeatTimes() = ceil((position.toDouble() + 1) / 2).toInt()
    }
}
