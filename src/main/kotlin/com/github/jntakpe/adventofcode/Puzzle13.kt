package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle13.Bag

fun main() {
    readInputLines(13)
        .map { Bag(it) }
        .toSet()
        .contains("shiny gold")
        .run { println(size) }
}

private fun Set<Bag>.contains(name: String): Set<Bag> {
    val bags = containsRecursively(this, name).toMutableSet()
    do {
        val filtered = bags
            .flatMap { containsRecursively(this, it.name) }
            .toSet()
    } while (bags.addAll(filtered))
    return bags
}

private fun containsRecursively(bags: Set<Bag>, bagName: String): Set<Bag> {
    return bags.filter { it.content.containsKey(bagName) }.toSet()
}

object Puzzle13 {
    class Bag(line: String) {

        val name: String = line.substringBefore("bags").trim()
        val content: Map<String, Int> = resolveContent(line)

        private fun resolveContent(line: String): Map<String, Int> {
            return line
                .substringAfter("contain")
                .split(",")
                .map { it.substringBefore("bag") }
                .filter { s -> s.any { it.isDigit() } }
                .groupBy({ s -> s.filter { !it.isDigit() }.trim() }, { s -> s.filter { it.isDigit() }.toInt() })
                .mapValues { it.value.first() }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Bag

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }
}
