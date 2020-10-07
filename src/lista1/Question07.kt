package lista1

import kotlin.math.ln

object PrimeNumber {
    fun nPrimes(nth: Int): Int{

        val size = sRange(nth)
        /* Each position corresponds to a number within the search range. As an initial hypothesis, we take indexes 0 
         * and 1 as not prime numbers (assign 0) and consider all remaining indexes as prime numbers (assign 1). After
         * that, we will check which one is prime or not.
         */
        val nNumbers = Array(size){1}
        nNumbers[0] = 0
        nNumbers[1] = 0

        /* list primes stores all n prime numbers asked */
        val lPrimes = mutableListOf<Int>()

        /* countPrime indicates how many prime numbers we found. */
        var countPrimes = 0
        /* Steps indicates how many steps we need to found the prime numbers that we want. After each instruction,
         * we increment steps.
         */
        var steps = 0

        /* The variable i inside for loop starts in two because we know previously that 0 and 1 are not prime numbers.*/
        for (i in 2..size) {
            steps++
            /* If this number remains equals one, we calculate the multiple numbers of the square of this number
               and assign them zero, meaning they are not any more prime numbers
             */
            if (nNumbers[i] == 1) {
                steps++

                /* If we find all number primes asked, then we stop the searching; otherwise, we store the next prime
                 * number founded and continue the searching.
                 */
                if(countPrimes++ < nth) {
                    lPrimes.add(i)
                    steps+=1
                }
                else {
                    steps+=2
                    break
                }

                /* The calculation of the multiple numbers of the square of the last prime number founded. */
                for (j in i..size) {
                    steps++
                    if (i * j < size){
                        nNumbers[i * j] = 0
                        steps+=2
                    }
                    else {
                        steps+=2
                        break
                    }
                }
            }
        }

        println(lPrimes)
        return steps
    }

    private fun sRange(nth: Int): Int{
        var i = 2
        /*
        while (2.0.pow(i.toDouble()).div(ln(2.0.pow(i.toDouble()))) < nth.toDouble())
            i++
        return 2.0.pow(i.toDouble()).toInt()
        */

        while (i.toDouble().div(ln(i.toDouble())) < nth.toDouble())
            i++

        return i
    }
}

fun main() {
    val arr = mutableMapOf<Int,Int>()

    for (i in 10..1000)
        arr[i] = PrimeNumber.nPrimes(i)

    println("N;Steps")
    arr.asSequence().map { println("${it.key};${it.value}") }.toList()

}

