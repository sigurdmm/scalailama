# Theory answers

## Information about file structure:
The introduction tasks are inside `introductionTasks` package containing each task respectively.
Each project task is written in the places defined in the exercise document, i.e. `Acoount.scala, Bank.scala`...

## Task 1: Scala introduction
### Task 1d)
The INTEGER type can store a smaller amount of integers, that is, to 2.1 billion numbers. This uses 2^31-1 bits to represent numbers.
The BIGINT type can store even bigger numbers and support larger numbers to 20 digits. This uses 2^63-1 bits to represent numbers.

## Task 2: Concurrency in Scala
### Task 2b
The phenomenon is called _Race condition_ which the output of a concurrent program that, for instance uses threads, depends on the execution schedule of the statements in the program.
One example where the race condition phenomenon is problematic is where one thread is depending on a result from another thread.

## Task 2d
Deadlock is when you have two processes x and y where x is waiting for y to finish and vice versa. In this case, both processes will run in an endless loop.
If one is only using pure functions, which does not contain any side effects and furthermore be deadlock safe.
Other methods that will prevent deadlock:
- lock timeouts
- lock ordering, if done correctly
- deadlock detection


