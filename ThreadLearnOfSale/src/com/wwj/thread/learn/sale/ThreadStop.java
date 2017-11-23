package com.wwj.thread.learn.sale;

//标志位   中断

class Stop implements Runnable{
	
	private boolean flag=true;

	@Override
	public void run() {
		while(flag){
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
//				e.printStackTrace();
				flag=false;
			}
			for(int i=0;i<50;i++){
				System.out.println(Thread.currentThread().getName()+"============"+i);
			}
		}
	}
	
	public void setFlag(boolean flag){
		this.flag=flag;
	}
	
}


public class ThreadStop {

	public static void main(String[] args) {

		Stop stop=new Stop();
		
		Thread bobi=new Thread(stop,"bobi");
		
		bobi.start();
		
		for(int i=0;i<50;i++){
			if(i>=49){
//				stop.setFlag(false);
				bobi.interrupt();
			}
			System.out.println(Thread.currentThread().getName()+"==========i="+i);
		}
		System.out.println(Thread.currentThread().getName()+"===============over==============");
	}

}
