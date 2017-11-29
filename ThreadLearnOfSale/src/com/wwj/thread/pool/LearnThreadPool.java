package com.wwj.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LearnThreadPool {

	public static void main(String[] args) {
		
		ExecutorService executorService=Executors.newFixedThreadPool(3);
		
		for(int e=1;e<=10;e++){
			
			final int index=e;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(Thread.currentThread().getName()+"===第"+index+"个任务");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
		executorService.shutdown();
		

	}

}
