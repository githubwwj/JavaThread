package com.wwj.thread.learn.sale;

class Ticket implements Runnable{
	int ticket=10;
	@Override
	public void run() {
		
		while(true){
			
			synchronized(this){
				if(ticket>0){
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"===="+ticket);
					ticket--;
					
				}else{
					break;
				}
			}
		}
	}
}

public class SaleLearn {
	public static void main(String[] args) {
		Ticket ticket=new Ticket();
		
		Thread bob=new Thread(ticket,"bob");
		Thread mike=new Thread(ticket,"mike");
		bob.start();
		mike.start();
		System.out.println(Thread.currentThread().getName()+"======over=");
	}
}
