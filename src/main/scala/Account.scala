import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    // TODO
    // for project task 1.2: implement functions
    // for project task 1.3: change return type and update function bodies
    def withdraw(amount: Double): Either[Unit, Exception] = {
        // See Synchronization, https://twitter.github.io/scala_school/concurrency.html
        balance.synchronized {
            val nextBalance = balance.amount - amount

            if (amount <= 0) {
                Right(new IllegalAmountException("You cannot withdraw a negative amount"))
            } else if (nextBalance < 0) {
                Right(new NoSufficientFundsException(s"You are missing ${nextBalance * -1}"))
            } else {
                balance.amount = nextBalance
                Left(Unit)
            }
        }
    }
    def deposit (amount: Double): Either[Unit, Exception] = {
        balance.synchronized {
            if (amount <= 0) {
                return Right(new IllegalAmountException("You cannot deposit a negative amount"))
            }

            balance.amount += amount
            Left(Unit)
        }
    }

    def getBalanceAmount: Double = balance.amount

    def transferTo(account: Account, amount: Double) {
        bank addTransactionToQueue (this, account, amount)
    }


}
