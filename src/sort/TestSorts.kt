package sort

import kotlin.math.log10
import kotlin.math.pow
import kotlin.random.Random

inline fun <reified T> Array<Int>.sort(sort: SortAlgorithm, noinline init: (Int) -> T): Array<Long> where T: Number, T: Comparable<T>{
    val arr = Array(this.size){ 0L }
    this.forEach {
        val vector = Array(it, init)
        val index = log10(it.toDouble()).toInt() - 1
        arr[index] = sort.sort(vector)
    }
    return arr
}

fun main(){

    val n = 4
    val nSizes = Array(n){ (10.toFloat()).pow(it+1).toInt() }

    val worstCase = nSizes.sort(InsertionSort){pos -> (nSizes.size - pos).toLong() }
    val bestCase = nSizes.sort(InsertionSort){num:Int -> num + 1L}
    val randomCase = nSizes.sort(InsertionSort){ Random.nextLong(nSizes.size.toLong()) }

    println("Best case: ${bestCase.toList()}")
    println("Random case: ${randomCase.toList()}")
    println("Worst case: ${worstCase.toList()}")

}