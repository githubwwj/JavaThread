package com.wwj.thread.communciation;

class Resource{  //煤
	String name;
	int weight;
	boolean flag=false;  //true 有煤       false 没有煤
	int sumCar=0; //今天拉煤多少卡车
}

class Input implements Runnable{  //小卡车输入煤
	Resource res;
	Input(Resource res){
		this.res=res;
	}

	@Override
	public void run() {
		int x=0;
		while(true){
			synchronized (res) {  //bob(活)   lili(睡着)
				if(res.sumCar>99){
					break;
				}
				while(res.flag){
					try {
						res.wait();  // 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
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
				System.out.println(Thread.currentThread().getName()+"=====拉了一车"+res.name+"煤========="+res.sumCar+"车");
				res.notifyAll(); //
			}
		}
	}
	
}

class Output implements Runnable{  //锅炉烧煤
	
	Resource res;
	
	Output(Resource res){
		this.res=res;
	}

	@Override
	public void run() {
		while(true){
			synchronized (res) {    //mike(睡着)    jenny(睡着)
				while(!res.flag){
					try {
						res.wait();  //
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println(Thread.currentThread().getName()+"=======烧了一车="+res.name+"======"+res.weight+"吨========"+res.sumCar+"车");
				res.name=null;
				res.weight=0;
				res.flag=false;
				res.notifyAll();
				if(res.sumCar>99){
					break;
				}
			}
		}
	}
	
}

public class ThreadCommunciationTwo {

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
