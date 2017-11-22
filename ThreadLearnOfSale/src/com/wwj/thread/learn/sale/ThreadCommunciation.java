package com.wwj.thread.learn.sale;


class Resource{  //煤
	String name;
	int weight;
	boolean flag=false;
	int sumCar=0;
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
			synchronized (res) {
				if(res.sumCar>99){
					break;
				}
				if(!res.flag){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
					System.out.println(Thread.currentThread().getName()+"=====拉了一车煤========="+res.sumCar+"车");
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
			synchronized (res) {
				if(res.flag){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"=======烧了="+res.name+"======"+res.weight+"吨========"+res.sumCar+"车");
					res.name=null;
					res.weight=0;
					res.flag=false;
					res.notify();
				}
				if(res.sumCar>99){
					break;
				}
			}
		}
	}
	
}

public class ThreadCommunciation {

	public static void main(String[] args) {
		
		Resource res=new Resource();
		
		Input input=new Input(res);
		Output output=new Output(res);
		
		Thread bob=new Thread(input,"bob");
		Thread mike=new Thread(output,"mike");
		
		bob.start();
		mike.start();
		
		

	}

}
