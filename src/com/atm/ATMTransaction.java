package com.atm;

public class ATMTransaction extends Thread {

	MySemaphore s = null;

	ATMTransaction(MySemaphore s) {
		this.s = s;
	}

	@Override
	public void run() {

		s.lock();
		System.out.println(this.getName() + " - Tansaction Completed.");
		s.unlock();

	}

	public static void main(String[] args) {

		MySemaphore s = new MySemaphore(3);

		for (int i = 1; i <= 10; i++) {
			new ATMTransaction(s).start();
		}

	}

}
