package Lista1

import java.util.concurrent.TimeUnit
import kotlin.math.log10
import kotlin.math.pow
import kotlin.random.Random

inline fun <reified T> Array<Int>.sort(nameCase: String, sort: SortAlgorithm, reverse: Boolean = false, noinline init: (Int) -> T): Array<Long>
        where T: Number, T: Comparable<T>
{
    println("Starting ${sort.getName()} for $nameCase")

    val start = System.currentTimeMillis()
    val arr = Array(this.size){ 0L }

    this.forEach {
        var vector = Array(it, init)

        if(reverse)
            vector = vector.asList().asReversed().toTypedArray()

        println("Array = ${vector.asList()}")
        val index = log10(it.toDouble()).toInt() - 1
        arr[index] = measureTimeMillis(sort,it) { sort.sort(vector) }

    }

    println("Runtime(${sort.getName()}) = ${getTime(System.currentTimeMillis() - start)}\n")

    return arr
}

inline fun measureTimeMillis(typeSort: SortAlgorithm, size: Int, block: () -> Long): Long {

    println("Executing ${typeSort.getName()} for n = $size ... ")

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
    val n = 4
    val nSizes = Array(n){ (10.toFloat()).pow(it+1).toInt() }

    println("nSizes = ${nSizes.toList()}")

    val bestCase = nSizes.sort("best case", SelectionSort){ pos ->  pos + 1L }
    val randomCase = nSizes.sort("random case", SelectionSort){ pos -> Random.nextLong(pos.toLong()+1L)  }
    val worstCase = nSizes.sort("worst case", SelectionSort,true){ pos -> pos + 1L }

    println("The best cases has taken ${bestCase.toList()} steps")
    println("The random cases has took ${randomCase.toList()} steps")
    println("the worst cases has took ${worstCase.toList()} steps")
}