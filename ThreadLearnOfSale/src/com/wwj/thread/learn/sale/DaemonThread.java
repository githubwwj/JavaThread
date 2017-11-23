package com.wwj.thread.learn.sale;

class Daemon implements Runnable{
	
	@Override
	public void run() {
		while(true){
			for(int i=0;i<50;i++){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().toString()+"=====i="+i);
			}
			break;
		}
	}
}

public class DaemonThread {
	public static void main(String[] args) {

		Daemon daemon=new Daemon();
		Thread bobi=new Thread(daemon,"bobi");
		
		Thread aolifu=new Thread(daemon,"aolifu");
		
//		bobi.setDaemon(true); // 守护线程，也叫后台线程，当我们的主线程退出,bobi线程必须退出
		bobi.start();
		
		try {
			bobi.join();  //当bobi线程加入主线程,主线程处于冻结状态
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		aolifu.start();
		
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().toString()+"========over======");
		
	}

}
