package de.maaxgr.calculatorapp

import de.maaxgr.calculatorapp.utils.BigNumberCalculator
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test() {

        val calc = BigNumberCalculator()


        fun numberStringToArray() {

        }
    }

    @Test
    fun numberStringToPositionNumberSystemArrayTest() {
        val calc = BigNumberCalculator()
        val numberString = "168"

        val numberSystemArray = calc.numberStringToPositionNumberSystemList(numberString)

        assertEquals(listOf(8, 6, 1).joinToString(), numberSystemArray.joinToString())
    }

    @Test
    fun positionNumberSystemListToNumberStringTest() {
        val calc = BigNumberCalculator()

        val numberArray = listOf(8, 6, 1)

        assertEquals("168", calc.positionNumberSystemListToNumberString(numberArray))
    }

    @Test
    fun tryToPressIntoIndexTest() {
        val calc = BigNumberCalculator()

        val number = "998"
        val numberArray = calc.numberStringToPositionNumberSystemList(number)

        val resultArray = calc.tryToPressNumberIntoNumberSystem(numberArray, 0, 5)

        println(resultArray)

        val resultNumber = calc.positionNumberSystemListToNumberString(resultArray)

        assertEquals("1003", resultNumber)
    }


    @Test
    fun concatPositionNumberSystemListsTest() {
        val calc = BigNumberCalculator()

        val number = "157"
        val number2 = "224"

        val numberListA = calc.numberStringToPositionNumberSystemList(number)
        val numberListB = calc.numberStringToPositionNumberSystemList(number2)

        println("Nubmberlist A $numberListA")
        println("Nubmberlist B $numberListB")

        val resultNumberList = calc.concatPositionNumberSystemLists(numberListA, numberListB)

        assertEquals("381", calc.positionNumberSystemListToNumberString(resultNumberList))
    }

    @Test
    fun addTest() {

        val calc = BigNumberCalculator()

        val number1 = "123"
        val number2 = "456"
        val number3 = "500"

        val list = listOf(number1, number2, number3)

        assertEquals("1079", calc.addStrings(list))


    }


}
