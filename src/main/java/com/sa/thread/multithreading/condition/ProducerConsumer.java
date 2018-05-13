package com.sa.thread.multithreading.condition;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

	private Stack<Integer> stack = new Stack<Integer>();
	private final int CAPACITY = 5;
	private Lock lock = new ReentrantLock();
	private Condition stackEmptyCondition = lock.newCondition();
	private Condition stackFullCondition = lock.newCondition();

	
	public void push(String threadName) throws InterruptedException {
		lock.lock();
		try {
			if(stack.size() == CAPACITY) {
				System.out.println("\n"+threadName+"Producer lock acquired\n");
				stackFullCondition.await();
			}
			Random random = new Random();
			int item =random.nextInt(1000);
			stack.push(item);
			System.out.println(threadName+"Produced item ::"+ item);
//			Thread.sleep(1000);
			stackEmptyCondition.signalAll();
			System.out.println("\n"+threadName+"Consumer lock released\n");
		} finally {
			lock.unlock();
		}
	}
	
	public void pop(String threadName) throws InterruptedException {
		lock.lock();
		try {
			if(stack.size() == 0) {
				System.out.println("\n"+threadName+"Consumer lock acquired\n");
				stackEmptyCondition.await();
			}
			int item = stack.pop();
			System.out.println(threadName+"Consumed item ::"+ item);
//			Thread.sleep(1000);
			stackFullCondition.signalAll();
			System.out.println("\n"+threadName+"Producer lock released\n");
		} finally {
			lock.unlock();
		}
	}
}
