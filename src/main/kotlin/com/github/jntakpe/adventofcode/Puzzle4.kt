package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle3.Line
import com.github.jntakpe.adventofcode.Puzzle3.Rule

fun main() {
    readInputLines(3)
        .mapNotNull { it.parseLine() }
        .count { it.isValid() }
        .run { print(this) }
}

object Puzzle4 {
    data class Rule(val minOccurrence: Int, val maxOccurrence: Int, val character: Char)

    data class Line(val password: String, val rule: Rule) {

        fun isValid() = password.count { it == rule.character }.run { this >= rule.minOccurrence && this <= rule.maxOccurrence }
    }
}

private fun String.parseLine() = split(':').let { l -> l.first().parseRule()?.let { r -> l.last().parsePassword()?.let { Line(it, r) } } }

private fun String?.parseRule(): Rule? {
    return takeIf { !isNullOrBlank() }
        ?.split(Regex("[^\\w']+"))
        ?.map { it.trim() }
        ?.let { Rule(it.first().toInt(), it[1].toInt(), it.last().first()) }
}

private fun String?.parsePassword() = takeIf { !isNullOrBlank() }?.trim()
