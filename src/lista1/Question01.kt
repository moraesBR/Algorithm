package lista1

inline fun <reified T> Array<Int>.search(nameCase: String, search: SearchAlgorithm, key: Int, case: Case = Case.RANDOM): Array<Answer>
        where T: Number, T: Comparable<T>
{
    println("Starting ${search.getName()} for $nameCase")

    val arr = Array<Answer>(this.size){ Answer(0,-1) }
    var index = 0

    this.forEach {
        val vector = Array(it){ pos -> pos + 1 }

        vector.toMutableList().shuffle()

        when(case){
            Case.BEST -> vector[0] = key
            Case.RANDOM -> vector[(1..vector.size-2).random()] = key
            Case.WORST -> vector[vector.size-1] = key
        }

        println("Array($it): ${vector.toList()}")
        //val index = log10(it.toDouble()).toInt() - 1
        arr[index] = search.search(vector,key)
        println("Position: ${arr[index].pos}\nSteps = ${arr[index].count}")
        index++
    }

    return arr
}

fun main(){
    val n = 100
    //val nSizes = Array(n){ (10.toFloat()).pow(it+1).toInt() }
    val nSizes = Array(n){ 10 + 10*it }

    println("nSizes = ${nSizes.toList()}")

    val bestCase = nSizes.search<Long>("best case", LinearSearch,-9999,Case.BEST)
    val randomCase = nSizes.search<Long>("random case", LinearSearch,-9999)
    val worstCase = nSizes.search<Long>("worst case", LinearSearch,-9999,Case.WORST)

    println("The best cases has taken ${bestCase.toList().asSequence().map { answer -> answer.count }.toList()} steps")
    println("The random cases has took ${randomCase.toList().asSequence().map { answer -> answer.count }.toList()} steps")
    println("the worst cases has took ${worstCase.toList().asSequence().map { answer -> answer.count }.toList()} steps")

    val map = nSizes.zip(bestCase.zip(randomCase.zip(worstCase))).toMap()
    println("N,Best;Random;Worst")
    map.asSequence().map{ println("${it.key};${it.value.first.count};${it.value.second.first.count};${it.value.second.second.count}")}.toList()
}