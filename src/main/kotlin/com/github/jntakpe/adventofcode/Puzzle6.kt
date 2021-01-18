package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle6.Line
import com.github.jntakpe.adventofcode.Puzzle6.Slope
import kotlin.math.ceil

fun main() {
    val lines = readInputLines(5)
    listOf(Slope(1, 1), Slope(3, 1), Slope(5, 1), Slope(7, 1), Slope(1, 2))
        .map { lines.treeCount(it).toLong() }
        .reduce { current, acc -> acc * current }
        .run { println(this) }
}

private fun List<String>.treeCount(slope: Slope): Int {
    return IntProgression.fromClosedRange(0, size - 1, slope.down)
        .map { Line(this[it], it / slope.down * slope.right) }
        .count { it.hasTree() }
}

private object Puzzle6 {

    class Line(private val pattern: String, private val position: Int) {

        fun hasTree() = fullLine()[position] == '#'

        private fun fullLine() = if (pattern.length <= position) pattern.repeat(repeatTimes()) else pattern

        private fun repeatTimes() = ceil((position.toDouble() + 1) / 2).toInt()
    }

    data class Slope(val right: Int, val down: Int)
}
