package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle7.PassportFields

fun main() {
    readInputSplitOnBlank(7)
        .map { it.parsePassport() }
        .count { it.isValid() }
        .run { println(this) }
}

private fun String.parsePassport(): Map<PassportFields, String> {
    return split("[\\n\\r\\s]+".toRegex())
        .map { it.trim().split(":") }
        .associate { it.first().parseKey() to it.last() }
}

private fun String.parseKey() = PassportFields.values().find { it.text == this }!!

private fun Map<PassportFields, String>.isValid() = keys.containsAll(PassportFields.values().filter { it != PassportFields.COUNTRY_ID })

private object Puzzle7 {

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
