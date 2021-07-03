
class AccountTransferThread extends Thread {

	private Account fromAccount;
	private Account toAccount;
	private int amount;
	private int maxIter = 10000;
	private int totalInc;

	public AccountTransferThread(String name, Account fromAccount, Account toAccount, int amount) {
		super(name);
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	/* Transfer amount from fromAccount to toAccount */
	public void accountTransfer() {
		synchronized (fromAccount) {
			synchronized (toAccount) {
				if (fromAccount.getSaldo() >= amount) {
					fromAccount.changeSaldo(-amount);
					toAccount.changeSaldo(amount);
					totalInc = amount;
				}
			}
		}
	}

	public void run() {
		for (int i = 0; i < maxIter; i++) {
			accountTransfer();
			try { // simulation of work time
				Thread.sleep((int) (Math.random() * 10));
			} catch (InterruptedException e) {
				;
			}
		}
		System.out.println("DONE! " + getName());
	}
}

/*
 * 2a) Es gibt Transaktionen die sich überschneiden und es passiert wenn
 * gleichzeitig getSaldo() und changeSaldo() aufgerufen werden
 * 
 * 2b) Mutual-Exclusion (synchronized void changeSaldo() in Account &
 * accountTransfer() in AccountTransferThread). Monitor-Objekt: Account. Beide
 * Account-Objekte sollten gelockt sein.
 * 
 * 2c) Es könnte ein Deadlock sein.
 * 
 */