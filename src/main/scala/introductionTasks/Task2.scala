package introductionTasks

object Task2 {
    def main(args: Array[String]) = {

        def asThread(fun: () => Unit):Thread = new Thread{
                override def run(): Unit = fun()
        }

//        val thread = initThreadWithFunction(
//            () => {
//                Thread.sleep(2000)
//                println("hei")
//            }
//        )
//        thread.start()
//        println()
    }
}
