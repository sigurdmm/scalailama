import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}
import collection.JavaConverters.asScalaIterator

//sealed trait TransactionStatus
//case object SUCCESS extends TransactionStatus
//case object PENDING extends TransactionStatus
//case object FAILED extends TransactionStatus

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {
    /**
     * Use BlockingQueue to be thread-safe
     * */
    private val transactions: BlockingQueue[Transaction] = new LinkedBlockingQueue()

    // TODO
    // project task 1.1
    // Add datastructure to contain the transactions

    // Remove and return the first element from the queue
    def pop: Transaction = transactions.take()

    // Return whether the queue is empty
    def isEmpty: Boolean = transactions.isEmpty

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = transactions.put(t)

    // Return the first element from the queue without removing it
    def peek: Transaction = transactions.peek()

    // Return an iterator to allow you to iterate over the queue
    def iterator: Iterator[Transaction] = asScalaIterator(transactions.iterator())
}

class Transaction(val transactionsQueue: TransactionQueue,
                  val processedTransactions: TransactionQueue,
                  val from: Account,
                  val to: Account,
                  val amount: Double,
                  val allowedAttemps: Int) extends Runnable {

  var status: TransactionStatus.Value = TransactionStatus.PENDING
  var attempt = 0

  override def run(): Unit = {

      def doTransaction(): Unit = {
          // TODO - project task 3
          // Extend this method to satisfy requirements.
          this.status = from.withdraw(amount)
              .fold( _ => to.deposit(amount), error => Right(error))
              .fold(_ => TransactionStatus.SUCCESS , _ => TransactionStatus.PENDING)
//          val attemptWithdraw = from.withdraw(amount)
//          if(attemptWithdraw.isRight){
//              val attemptDeposit = to.deposit(amount)
//              if(!attemptDeposit.isRight){
//                  from.deposit(amount)
//                  status = TransactionStatus.FAILED
//              }
//              else status = TransactionStatus.SUCCESS
//          }
      }


      // TODO - project task 3
      // make the code below thread safe
      while (this.status == TransactionStatus.PENDING) {
          this.attempt += 1
          if (attempt > allowedAttemps) {
              this.status = TransactionStatus.FAILED
          }
          doTransaction()
          Thread.sleep(50) // you might want this to make more room for
                           // new transactions to be added to the queue
      }
    }
}
