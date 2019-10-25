package introductionTasks

import java.util.concurrent.atomic.AtomicInteger

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
}
