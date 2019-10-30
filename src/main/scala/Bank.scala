class Bank(val allowedAttempts: Integer = 3) {
    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()

    // TODO
    // project task 2
    // create a new transaction object and put it in the queue
    // spawn a thread that calls processTransactions
    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = {
        val transaction = new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)

        transactionsQueue.push(transaction)

        val thread = new Thread {
            override def run() {
                processTransactions
            }
        }

        thread.start()
    }

    // TODO
    // project task 2
    // Function that pops a transaction from the queue
    // and spawns a thread to execute the transaction.
    // Finally do the appropriate thing, depending on whether
    // the transaction succeeded or not
    private def processTransactions: Unit = {
        val transaction = transactionsQueue.pop

        val thread = new Thread(transaction)
        thread.start()
        thread.join(10000) // prevents infinite stall by timing out after 10 seconds

        if (transaction.status == TransactionStatus.PENDING) {
            transactionsQueue.push(transaction)
            processTransactions
        } else {
            processedTransactions.push(transaction)
        }

    }

    def addAccount(initialBalance: Double) = new Account(this, initialBalance)

    def getProcessedTransactionsAsList = processedTransactions.iterator.toList
}
