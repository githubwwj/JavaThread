package com.wwj.thread.communciation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource{  //煤
	String name;
	int weight;
	boolean flag=false;  //true 有煤       false 没有煤
	int sumCar=0; //今天拉煤多少卡车
	Lock lock=new ReentrantLock();
	Condition productMonitor;
	Condition consumeMonitor;
	
	public Condition getProductMonitor(){
		if(null==productMonitor){
			productMonitor=lock.newCondition();
		}
		return productMonitor;
	}
	
	
	public Condition getConcumeMonitor(){
		if(null==consumeMonitor){
			consumeMonitor=lock.newCondition();
		}
		return consumeMonitor;
	}
	
}

class Input implements Runnable{  //小卡车输入煤
	private Resource res;
	private int x=0;
	Input(Resource res){
		this.res=res;
	}

	@Override
	public void run() {
		
		Lock lock=res.lock;
		while(true){
//			synchronized (res) {  
				lock.lock();
				Condition productMonitor=res.getProductMonitor();
				Condition consumeMonitor=res.getConcumeMonitor();
				try{
					while(res.flag){
						try {
							productMonitor.await();
//							res.wait();   //bob(睡)   lili(睡)
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					if(res.sumCar>99){
						break;
					}
					
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
					if(x==0){
						res.name="精煤";
						res.weight=6;
					}else{
						res.name="烟煤";
						res.weight=10;
					}
					x=(x+1)%2;
					res.flag=true;
					res.sumCar++;
					System.out.println(Thread.currentThread().getName()+"=====拉了一车"+res.name+"煤========="+res.sumCar+"车"); // bob 车   lili车
					consumeMonitor.signal();
//					res.notify();
				}finally{
					lock.unlock();
				}
				
			}
//		}
	}
	
}

class Output implements Runnable{  //锅炉烧煤
	
	Resource res;
	
	Output(Resource res){
		this.res=res;
	}

	@Override
	public void run() {
		Lock lock=res.lock;
		while(true){
//			synchronized (res) {    
			try{
				lock.lock();
				Condition productMonitor=res.getProductMonitor();
				Condition consumeMonitor=res.getConcumeMonitor();
				while(!res.flag){
					try {
						consumeMonitor.await();
//						res.wait();  //mike(睡着)  jenny(睡着)
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					
				try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println(Thread.currentThread().getName()+"=======烧了一车"+res.name+"======"+res.weight+"吨========"+res.sumCar+"车");
				res.name=null;
				res.weight=0;
				res.flag=false;
//				res.notifyAll();
				productMonitor.signal();
				if(res.sumCar>99){
					break;
				}
			}finally{
				lock.unlock();
			}
				
//			}
		}
	}
	
}

public class ThreadCommunciationThree {

	public static void main(String[] args) {
		
		
		Resource res=new Resource();  //煤
		
		Input input=new Input(res);  //拉煤
		Output output=new Output(res);  //烧煤
		
		Thread bob=new Thread(input,"bob");
		Thread lili=new Thread(input,"lili");
		
		Thread mike=new Thread(output,"mike");
		Thread jenny=new Thread(output,"jenny");
		
		
		bob.start();
		lili.start();
		
		mike.start();
		jenny.start();
		

	}

}
