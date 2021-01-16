package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle4.Line
import com.github.jntakpe.adventofcode.Puzzle4.Position

fun main() {
    readInputLines(3)
        .mapNotNull { it.parseLine() }
        .count { it.isValid() }
        .run { print(this) }
}

private object Puzzle4 {
    data class Position(val first: Int, val last: Int, val character: Char)

    class Line(private val password: String, private val position: Position) {

        fun isValid() = hasChar(position.first) xor hasChar(position.last)

        private fun hasChar(index: Int) = with(index - 1) { password.length > this && password[this] == position.character }
    }
}

private fun String.parseLine() = split(':').let { l -> l.first().parseRule()?.let { r -> l.last().parsePassword()?.let { Line(it, r) } } }

private fun String?.parseRule(): Position? {
    return takeIf { !isNullOrBlank() }
        ?.split(Regex("[^\\w']+"))
        ?.map { it.trim() }
        ?.let { Position(it.first().toInt(), it[1].toInt(), it.last().first()) }
}

private fun String?.parsePassword() = takeIf { !isNullOrBlank() }?.trim()

