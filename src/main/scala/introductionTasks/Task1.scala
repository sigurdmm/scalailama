package introductionTasks

object Task1 {
  def main(args: Array[String]) {
    val range = generateRange(1, 50);
    println("Task1a")
    println(range.mkString(", "))
    assert(range(0) == 1)
    assert(range.last == 50)

    println()

    println("Task1b")
    println("Iteratively calculated sum of array from task 1a: %d".format(sumIteratively(range)))

    println()

    println("Task1c")
    println("Recursively calculated sum of array from task 1a: %d".format(sumRecursively(range)))

    println()

    val n = 10
    val nthFib = nthFibonacci(n)
    println("Task1d")
    println("The %dth fibonacci number is: %d".format(n, nthFib))
  }

  def generateRange(start: Int, end: Int): Array[Int] = {
    val numbers = new Array[Int](end)

    for (num <- start to end) {
      numbers(num - 1) = num
    }

    numbers
  }

  def sumIteratively(numbers: Array[Int]): Int = {
    var sum = 0;

    for(x <- numbers) {
      sum += x
    }

    sum
  }

  def sumRecursively(numbers: Array[Int]): Int = {
    @scala.annotation.tailrec
    def sumRecursivelyInternal(numbers: Array[Int], accSum: Int): Int = if (numbers.isEmpty) {
        accSum
      } else {
        sumRecursivelyInternal(numbers.drop(1), accSum + numbers(0))
      }

    sumRecursivelyInternal(numbers, 0)
  }

  // TODO: Answer theory question: Use BigInt instead of Int. What is the difference between these two data types?
  def nthFibonacci(n: BigInt): BigInt = {
    @scala.annotation.tailrec
    def nthFibonacciInternal(n: BigInt, a: BigInt, b: BigInt):BigInt = {
      if (n == 0) {
        a
      } else if (n == 1) {
        b
      } else {
        nthFibonacciInternal(n - 1, b, a + b)
      }
    }
    nthFibonacciInternal(n, 0, 1)
  }
}
