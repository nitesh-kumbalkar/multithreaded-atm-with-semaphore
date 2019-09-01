package com.atm;

import java.util.ArrayDeque;
import java.util.Deque;

public class MySemaphore {

	private final int capacity;
	private int usedCount;
	private Deque<Long> waitList;

	MySemaphore(int capacity) {
		this.capacity = capacity;
		waitList = new ArrayDeque<>();
	}

	public synchronized void lock() {

		waitList.addLast(Thread.currentThread().getId());

		while (!(usedCount < capacity && Thread.currentThread().getId() == waitList.getFirst())) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		usedCount++;

		waitList.removeFirst();
	}

	public synchronized void unlock() {
		if (usedCount > 0) {

			usedCount--;

			notifyAll();
		}
	}

}
