package com.havardp.calculator.lexer.token

abstract class BinaryOperatorToken: OperatorToken() {
    abstract val verbose: String

    companion object {
        private val PLUS = Plus()
        private val MINUS = Minus()
        private val MULTIPLICATION = Multiplication()
        private val DIVIDE = Divide()
        private val POWER = Power()
        private val MODULUS = Modulus()
        private val EQUAL = Equal()

        private val OPERATORS: Array<Token> = arrayOf(
                PLUS,
                MINUS,
                MULTIPLICATION,
                DIVIDE,
                POWER,
                MODULUS,
                EQUAL
        )

        // Checks if the passed string is a binary operator
        fun assert(string: String): Boolean = OPERATORS.any { it.value == string }

        fun acquire(string: String): Token = OPERATORS.first { it.value == string }
    }
}

data class Plus(override val value: String = "+", override val precedence: Int = 0, override val verbose: String = "+"): BinaryOperatorToken()
data class Minus(override val value: String = "-", override val precedence: Int = 0, override val verbose: String = "-"): BinaryOperatorToken()
data class Multiplication(override val value: String = "*", override val precedence: Int = 1, override val verbose: String = "\\cdot"): BinaryOperatorToken()
data class Divide(override val value: String = "/", override val precedence: Int = 1, override val verbose: String = "/"): BinaryOperatorToken()
data class Power(override val value: String = "^", override val precedence: Int = 3, override val verbose: String = "^"): BinaryOperatorToken()
data class Modulus(override val value: String = "%", override val precedence: Int = 0, override val verbose: String = "%"): BinaryOperatorToken()
data class Equal(override val value: String = "=", override val precedence: Int = -1, override val verbose: String = "="): BinaryOperatorToken()
