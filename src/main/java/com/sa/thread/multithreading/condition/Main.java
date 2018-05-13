package com.sa.thread.multithreading.condition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		ExecutorService e = null;
		try {
			e = Executors.newFixedThreadPool(10);
			final ProducerConsumer producerConsumer = new ProducerConsumer();

			Runnable r1 = new Runnable() {

				public void run() {
					while (true) {
						try {
							producerConsumer
									.push(LocalDateTime.now() + " :: " + Thread.currentThread().getName() + " :: ");

							Random r = new Random();
							// Thread.sleep(r.nextInt(1500));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};

			Runnable r2 = new Runnable() {

				public void run() {
					while (true) {
						try {
							producerConsumer
									.pop(LocalDateTime.now() + " :: " + Thread.currentThread().getName() + " :: ");

							Random r = new Random();
							// Thread.sleep(r.nextInt(3000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};

			e.submit(r1);
			e.submit(r2);

			e.submit(r1);
			e.submit(r1);

			e.submit(r1);
			e.submit(r1);

			e.submit(r2);
			e.submit(r2);

			e.submit(r1);
			e.submit(r2);
		} finally {
			if (null != e)
				e.shutdown();
		}

	}

}
