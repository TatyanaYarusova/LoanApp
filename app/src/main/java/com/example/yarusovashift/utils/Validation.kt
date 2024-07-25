package com.example.yarusovashift.utils

fun isValidFirstName(firstName: String): Boolean {
    for (char in firstName) {
        if (!char.isLetter() || char !in 'a'..'z' && char !in 'A'..'Z') {
            return false
        }
    }
    return true
}

fun isValidLastName(lastName: String): Boolean {
    for (char in lastName) {
        if (!char.isLetter() || char !in 'a'..'z' && char !in 'A'..'Z') {
            return false
        }
    }
    return true
}

fun isValidNumber(number: String): Boolean {
    val phonePattern = Regex("""^\+[1-9]\d{0,2}\s\d{3}\s\d{3}-\d{2}-\d{2}$""")
    return phonePattern.matches(number)
}

fun isValidName(login: String): Boolean {
    return login.all { it.isLetterOrDigit() }
}