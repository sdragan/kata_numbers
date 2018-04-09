class NumbersConverter {
    private val atomicValues: Map<Int, String> = hashMapOf(
            Pair(1, "one"),
            Pair(2, "two"),
            Pair(3, "three"),
            Pair(4, "four"),
            Pair(5, "five"),
            Pair(6, "six"),
            Pair(7, "seven"),
            Pair(8, "eight"),
            Pair(9, "nine"),
            Pair(10, "ten"),
            Pair(11, "eleven"),
            Pair(12, "twelve"),
            Pair(13, "thirteen"),
            Pair(14, "fourteen"),
            Pair(15, "fifteen"),
            Pair(16, "sixteen"),
            Pair(17, "seventeen"),
            Pair(18, "eighteen"),
            Pair(19, "nineteen")
    )

    private val tenthNames: Map<Double, String> = hashMapOf(
            Pair(20.00, "twenty"),
            Pair(30.00, "thirty"),
            Pair(40.00, "forty"),
            Pair(50.00, "fifty"),
            Pair(60.00, "sixty"),
            Pair(70.00, "seventy"),
            Pair(80.00, "eighty"),
            Pair(90.00, "ninety")
    )

    private val groupNames: Map<Int, String> = hashMapOf(
            Pair(1, "thousand"),
            Pair(2, "million"),
            Pair(3, "billion")
    )

    fun convert(input: Int): String {
        if (input == 0) {
            return "zero"
        }
        val number = Math.abs(input)
        val strings: MutableList<String> = ArrayList()
        var currentGroup = 0.00
        while (number >= Math.pow(1000.00, currentGroup)) {
            currentGroup++
            val threeDigitPart = Math.floor((number % (Math.pow(1000.00, currentGroup))) / Math.pow(1000.00, currentGroup - 1))
            strings.add(processThreeDigitPart(threeDigitPart))
        }

        var result = if (number < 0) "minus " else ""

        for (i in strings.size - 1 downTo 0) {
            result += strings[i]
            if (i > 0 && strings[i].isNotEmpty()) {
                result += " " + groupNames[i] + " "
            }
        }

        result = removeLeadingAnd(result)
        result = addMinusIfInputIsNegative(input, result)
        return result
    }

    private fun processThreeDigitPart(threeDigitPart: Double): String {
        var result = ""

        val firstDigit = Math.floor(threeDigitPart / 100)
        if (firstDigit >= 1) {
            result += (atomicValues[firstDigit.toInt()]) + " hundred "
        }

        val lastTwoDigits = threeDigitPart % 100
        if (lastTwoDigits > 0) {
            if (firstDigit == 0.00 || lastTwoDigits < 10) {
                result += "and "
            }

            if (lastTwoDigits <= 19) {
                result += atomicValues[lastTwoDigits.toInt()]
            } else {
                result += tenthNames[Math.floor(lastTwoDigits / 10) * 10]
                if (lastTwoDigits % 10 > 0) {
                    result += " " + atomicValues[(lastTwoDigits % 10).toInt()]
                }
            }
        }

        return result
    }

    private fun removeLeadingAnd(result: String): String {
        return if (result.startsWith("and ", true)) {
            result.substring("and ".length)
        } else {
            result
        }
    }

    private fun addMinusIfInputIsNegative(input: Int, result: String): String {
        return if (input < 0) "minus $result" else result

    }
}