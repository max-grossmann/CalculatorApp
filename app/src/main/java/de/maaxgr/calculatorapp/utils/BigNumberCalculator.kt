package de.maaxgr.calculatorapp.utils

class BigNumberCalculator {

    fun addStrings(numbers: List<String>): String {
        return addLists(numbers.map { numberStringToPositionNumberSystemList(it) })
    }

    fun addLists(numbers: List<List<Int>>): String {

        if (numbers.size == 1) {
            return positionNumberSystemListToNumberString(numbers.first())
        }

        val concat = concatPositionNumberSystemLists(numbers[0], numbers[1])

        return addLists(numbers.drop(2).plusElement(concat))
    }

    fun numberStringToPositionNumberSystemList(numberString: String): List<Int>
            = numberString.reversed().map { Character.getNumericValue(it) }.toList()

    fun concatPositionNumberSystemLists(arrA: List<Int>, arrB: List<Int>): List<Int> {
        var (shortList, longList) = if (arrA.size > arrB.size) {
            arrB to arrA
        } else {
            arrA to arrB
        }

        for (i in 0..longList.size) {
            if (i < shortList.size) {

                val shortListElemtn = shortList[i]
                println("El: " + shortListElemtn)

                longList = tryToPressNumberIntoNumberSystem(longList, i, shortList[i])
                println(longList)
            }
        }
        return longList
    }

    fun tryToPressNumberIntoNumberSystem(targetList: List<Int>, index: Int = 0, value: Int): List<Int> {
        val mutableTargetList = targetList.toMutableList()

        var numberToStore = (mutableTargetList.getOrNull(index) ?: 0) + value

        println("NTS: " + numberToStore)

        if (numberToStore < 10) {

            if (index <= mutableTargetList.size - 1) {
                mutableTargetList[index] = numberToStore
            }  else {
                mutableTargetList.add(numberToStore)
            }
            return mutableTargetList
        }

        val numberThatCanBeStored = numberToStore % 10

        println("Number that can be stored: $index $numberThatCanBeStored")

        mutableTargetList[index] = numberThatCanBeStored

        numberToStore = (numberToStore - numberThatCanBeStored) / 10

        println("NTS 2: " + numberToStore)

        return tryToPressNumberIntoNumberSystem(mutableTargetList, index + 1, numberToStore)
    }

    fun positionNumberSystemListToNumberString(list: List<Int>)
        = list.reversed().joinToString("")

}