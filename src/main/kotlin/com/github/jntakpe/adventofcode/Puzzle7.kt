package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle7.PassportFields

fun main() {
    readInput(7)
        .split("\\n\\r".toRegex())
        .map { it.trim() }
        .map { it.parsePassport() }
        .count { it.isValid() }
        .run { println(this) }
}

private fun String.parsePassport() = split("[\\n\\r\\s]+".toRegex()).map { it.trim().split(":") }.map { it.first().parseKey() to it.last() }

private fun String.parseKey() = PassportFields.values().find { it.text == this }!!

private fun List<Pair<PassportFields, String>>.isValid(): Boolean {
    return map { it.first }
        .containsAll(PassportFields.values().filter { it != PassportFields.COUNTRY_ID })
}

object Puzzle7 {

    enum class PassportFields(val text: String) {
        BIRTH_YEAR("byr"),
        ISSUE_YEAR("iyr"),
        EXPIRATION_YEAR("eyr"),
        HEIGHT("hgt"),
        HAIR_COLOR("hcl"),
        EYE_COLOR("ecl"),
        PASSPORT_ID("pid"),
        COUNTRY_ID("cid")
    }
}
