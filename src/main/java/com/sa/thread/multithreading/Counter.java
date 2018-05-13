package com.sa.thread.multithreading;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {

	private int count = 0;

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public void write() {
		lock.writeLock().lock();
		try {
			count = count + 1;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public int read() {
		lock.readLock().lock();
		try {
			return count;
		} finally {
			lock.readLock().unlock();
		}
	}
}
