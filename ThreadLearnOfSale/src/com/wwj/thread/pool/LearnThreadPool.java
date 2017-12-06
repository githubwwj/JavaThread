package com.wwj.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LearnThreadPool {
	
	public static void main(String[] args) {

//		createFixedThreadPool();
		createSingThreadPool();
		
	}

	private static void createSingThreadPool() {
		
		ScheduledExecutorService scheduledExecutorService=Executors.newSingleThreadScheduledExecutor();
		
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"==========");
			}
		}, 5, 3, TimeUnit.SECONDS);
		
	}

	private static void createFixedThreadPool() {
		
		ExecutorService executorService=Executors.newFixedThreadPool(3);
		
		for(int e=0;e<10;e++){
			final int index=e;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"====="+index);
					
				}
			});
		}
		
		
		executorService.shutdown();
		System.out.println(Thread.currentThread().getName()+"=====over=======");
	}

}
