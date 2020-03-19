package sort

abstract class SortAlgorithm {
    abstract fun <T> sort(arr: Array<T>): Long where T:Number, T:Comparable<T>
    abstract fun getName(): String
}