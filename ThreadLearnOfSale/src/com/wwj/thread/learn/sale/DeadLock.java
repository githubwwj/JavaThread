package com.wwj.thread.learn.sale;

class Lock implements Runnable{

	boolean flag=true;
	Object locka=new Object();
	Object lockb=new Object();
	
	@Override
	public void run() {
		
		if(flag){
			synchronized (locka) {
				
				try {
					//bob 做了一些耗时的操作
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				synchronized (lockb) {
					System.out.println("====bob====");
				}
			}
		}else{
			
			synchronized (lockb) {
				try {
					//mike 做了一些耗时的操作
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				synchronized (locka) {
					System.out.println("==========mike=========");
				}
				
			}
			
			
		}
		
		
	}
	
}

public class DeadLock {

	public static void main(String[] args) {

		Lock lock=new Lock();
		
		Thread bob=new Thread(lock,"bob");
		Thread mike=new Thread(lock,"mike");
		
		bob.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lock.flag=false;
		
		mike.start();
		
	}

}
