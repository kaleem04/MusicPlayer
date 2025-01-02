
class Practice {
    fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {
        var myArray = arrayOf<Int>()

        for (i in 0 until a.size) {
            if (a[i] > b[i]) {
                myArray[0] = 1
                myArray[1] = 0
            } else if (a[i] < b[i]) {
                myArray[0] = 0
                myArray[1] = 1
            } else if (a[i] == b[i]) {
                myArray[0] = 0
                myArray[1] = 0
            }
        }
        return myArray

    }
    compainion
    object {
        fun main() {
            val a = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

            val b = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

            val result = compareTriplets(a, b)

            println(result.joinToString(" "))
        }
    }
}