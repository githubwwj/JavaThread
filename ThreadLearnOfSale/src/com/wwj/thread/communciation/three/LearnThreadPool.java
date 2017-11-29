package com.wwj.thread.communciation.three;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LearnThreadPool {

	public static void main(String[] args) {
		
		ExecutorService executorService=Executors.newFixedThreadPool(3);
		
		for(int i=0;i<10;i++){
			final int index=i;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(Thread.currentThread().getName()+"====="+index);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}

		try {
			Thread.sleep(500);
			executorService.shutdown();
			System.out.println(Thread.currentThread().getName()+"================");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
