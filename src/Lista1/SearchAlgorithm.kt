package Lista1

abstract class SearchAlgorithm {
    abstract fun <T> search(arr: Array<T>, key: T): Answer where T:Number, T:Comparable<T>
    abstract fun getName(): String
}

object LinearSearch : SearchAlgorithm() {
    override fun <T> search(arr: Array<T>, key: T): Answer where T : Number, T : Comparable<T> {
        val ans = Answer(0,-1)
        for(i in 0 until arr.size){

            if(arr[i] == key) {
                ans.pos = i
                ans.count++
                return ans
            }
            ans.count += 2
        }
        return ans
    }

    override fun getName(): String {
        return "Linear Search"
    }
}