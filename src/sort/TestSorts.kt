package sort

import java.util.concurrent.TimeUnit
import kotlin.math.log10
import kotlin.math.pow
import kotlin.random.Random

inline fun <reified T> Array<Int>.sort(nameCase: String, sort: SortAlgorithm, noinline init: (Int) -> T): Array<Long>
        where T: Number, T: Comparable<T>
{
    println("Starting ${sort.getName()} for $nameCase")

    val start = System.currentTimeMillis()
    val arr = Array(this.size){ 0L }

    this.forEach {
        val vector = Array(it, init)
        val index = log10(it.toDouble()).toInt() - 1
        arr[index] = measureTimeMillis(sort,it) { sort.sort(vector) }
    }

    println("Runtime(${sort.getName()}) = ${getTime(System.currentTimeMillis() - start)}\n")

    return arr
}

inline fun measureTimeMillis(typeSort:SortAlgorithm, size: Int, block: () -> Long): Long {

    print("Executing ${typeSort.getName()} for n = $size ... ")

    val start = System.currentTimeMillis()
    val out = block()

    println("finished in ${getTime(System.currentTimeMillis() - start)}")

    return out
}

fun getTime(millis: Long): String = String
    .format("%02d:%02d:%02d:%04d",
        TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
        TimeUnit.MILLISECONDS.toMillis(millis)  - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis))
        )


fun main(){
    val n = 7
    val nSizes = Array(n){ (10.toFloat()).pow(it+1).toInt() }

    val bestCase = nSizes.sort("best case",InsertionSort){num:Int -> num + 1L}
    val randomCase = nSizes.sort("random case",InsertionSort){ Random.nextLong(nSizes.size.toLong()) }
    val worstCase = nSizes.sort("worst case",InsertionSort){pos -> (nSizes.size - pos).toLong() }


    println("The best cases has taken ${bestCase.toList()} steps")
    println("The random cases has took ${randomCase.toList()} steps")
    println("the worst cases has took ${worstCase.toList()} steps")
}