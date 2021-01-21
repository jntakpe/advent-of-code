package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle8.PassportFields

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

private fun Map<PassportFields, String>.isValid(): Boolean {
    return keys.containsAll(PassportFields.values().filter { it != PassportFields.COUNTRY_ID })
            && get(PassportFields.BIRTH_YEAR).isIntValid { it in 1920..2002 }
            && get(PassportFields.ISSUE_YEAR).isIntValid { it in 2010..2020 }
            && get(PassportFields.EXPIRATION_YEAR).isIntValid { it in 2020..2030 }
            && get(PassportFields.HEIGHT).isHeightValid()
            && get(PassportFields.HAIR_COLOR).isHairValid()
            && get(PassportFields.EYE_COLOR).isEyeColorValid()
            && get(PassportFields.PASSPORT_ID).isPassportIdValid()
}

private fun String?.isIntValid(predicate: (Int) -> Boolean) = this?.toIntOrNull()?.let(predicate) ?: false

private fun String?.isHeightValid() = this?.run {
    when {
        endsWith("in") -> removeSuffix("in").toInt() in 59..76
        endsWith("cm") -> removeSuffix("cm").toInt() in 150..193
        else -> false
    }
} ?: false

private fun String?.isHairValid() = this?.matches("^#[a-f0-9]{6}$".toRegex()) ?: false

private fun String?.isEyeColorValid() = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(this)

private fun String?.isPassportIdValid() = this?.matches("^\\d{9}$".toRegex()) ?: false

private object Puzzle8 {

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
