class NumbersConverterTest (val converter: NumbersConverter) {

    fun test() {
        testCase(0, "zero")
        testCase(1, "one")
        testCase(7, "seven")
        testCase(14, "fourteen")
        testCase(99, "ninety nine")
        testCase(103, "one hundred and three")
        testCase(198, "one hundred ninety eight")
        testCase(20002, "twenty thousand and two")
        testCase(20805, "twenty thousand eight hundred and five")
        testCase(20805, "twenty thousand eight hundred and five")
        testCase(99000099, "ninety nine million and ninety nine")
        testCase(-909000099, "minus nine hundred and nine million and ninety nine")
    }

    private fun testCase(input: Int, output: String) {
        val result = converter.convert(input)
        val passed = (result == output)
        println("$input - $output: " + if (passed) "passed" else ("FAILED ($result)"))
    }
}