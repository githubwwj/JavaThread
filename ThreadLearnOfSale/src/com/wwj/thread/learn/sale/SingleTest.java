package com.wwj.thread.learn.sale;

class Single{

	/**
	 * 单例饿汉式  在多线程下是数据安全的
	 */
	private Single(){
		
	}
	
//	private static Single mSingle=new Single();
//	
//	public static Single getInstance(){
//		return mSingle;
//	}
	
	private static Single mSingle;
	
	Class clazz=Single.class;
	/**
	 * 这种写法效率并不高
	 * @return
	 */
//	public static synchronized Single getInstace(){
//		if(mSingle==null){
//			mSingle=new Single();  //t3 创建了一个单例对象  t2 创建了一个单例对象
//		}
//		return mSingle;
//	}
	
	/**
	 * 效率高
	 * @return
	 */
	public static  Single getInstace(){
		if(mSingle==null){
			synchronized(Single.class){
				if(mSingle==null){
					mSingle=new Single();  
				}
			}
		}
		return mSingle;
	}
	
	
	
}

class Pause extends Thread{
	@Override
	public void run() {
		for(int i=0;i<30;i++){
			System.out.println(Thread.currentThread().getName()+"=============="+i);
			yield();  //放弃CPU执行权,但是不放弃CPU执行资格
		}
		
	}
}

public class SingleTest {

	public static void main(String[] args) {

//		Single.getInstace();
		
		Pause t1=new Pause();
		Pause t2=new Pause();
		t1.start();
		t2.start();
		
	}

}
