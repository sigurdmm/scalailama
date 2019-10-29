package introductionTasks

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import java.util.concurrent.atomic.AtomicInteger

import scala.concurrent.Future

object Task2 {
    def main(args: Array[String]) = {

        // Task 2a)
        def asThread(fun: () => Unit):Thread = new Thread{
                override def run(): Unit = fun()
        }

        val thread = asThread(
            () => {
                Thread.sleep(2000)
                println("Thread is done")
            }
        )
        thread.start()
        println("Main is done")

        // Task 2b)
        val thread1 = asThread(() => increaseCounter())
        val thread2 = asThread(() => increaseCounter())
        val thread3 = asThread(() => printCounter())

        thread1.start()
        thread2.start()
        thread3.start()

    }

    // Task 2b), task 2c)
    private var counter: AtomicInteger = new AtomicInteger(0)
    def increaseCounter(): Unit = {
        counter.incrementAndGet()
    }

    def printCounter(): Unit = {
        println(counter)
    }

    // Task 2d)
    /**
     * Here is an example of deadlock between the two objects. With A depending on the step value in B and the step
     * value in B depending on the start value in A, the dependencies makes such that we have a deadlock.
     */

    object A {
        lazy val base = 42
        lazy val start = B.step
    }

    object B {
        lazy val step = A.base
    }

    object Scenario2 {
        def run = {
            val result = Future.sequence(Seq(
                Future { A.start },                        // (1)
                Future { B.step }                          // (2)
            ))
            Await.result(result, 1.minute)
        }
    }
}
