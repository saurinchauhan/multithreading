package com.sa.thread.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ExecutorService e = null;
		try {
			e = Executors.newFixedThreadPool(3);
			final Counter counter = new Counter();

			Runnable r1 = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName() + " is writing on count variable..");
					for (int i = 0; i < 50000; i++) {
						counter.write();
					}
				}
			};

			Runnable r2 = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName() + " is reading on count variable..");
					counter.read();
				}
			};

			Runnable r3 = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName() + " is reading on count variable..");
					counter.write();
				}
			};

			e.submit(r1);
			e.submit(r2);
			e.submit(r3);
		} finally {
			if (null != e)
				e.shutdown();
		}
	}
}
