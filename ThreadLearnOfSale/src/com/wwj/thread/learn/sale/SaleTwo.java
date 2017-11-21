package com.wwj.thread.learn.sale;

class SaleThread extends Thread{
	static int ticket=20;
	Object obj=new Object();
	SaleThread(String name){
		super.setName(name);
	}
	@Override
	public void run() {
		while(true){
			synchronized(SaleThread.class){  //同步代码块
				int x=10;
				if(ticket>0){
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"======="+ticket);
					ticket--;
				}else{
					break;
				}
			}
		}
	}
}

public class SaleTwo {
	public static void main(String[] args) {
		SaleThread bob=new SaleThread("bob");
		SaleThread mike=new SaleThread("mike");
		
		bob.start();
		mike.start();
		System.out.println(Thread.currentThread().getName()+"=======over==");
	}
}
