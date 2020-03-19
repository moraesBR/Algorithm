package sort

object InsertionSort : SortAlgorithm() {
    override fun <T> sort(arr: Array<T>): Long where T : Number, T : Comparable<T> {
        var count = arr.size.toLong()
        for (j in 1 until arr.size) {
            val key = arr[j]
            var i = j - 1
            while (++count > 0 && i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i]
                i--
                count += 2
            }
            arr[i + 1] = key
            count += 3
        }
        return count
    }

    override fun getName(): String {
        return "InsertionSort"
    }
}