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
Deadlock is when you have concurrent operations that have become stuck. An example of this is when you have two threads that depends on the result of each other so none may continue.
One way to prevent deadlock in concurrency is to have a timeout. This way, if two transactions are stuck because of each other, one of them may cancel and restart which will remove the deadlock.
