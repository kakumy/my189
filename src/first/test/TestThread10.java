package first.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *三元判断 
 *
 * 题目：线程轮替，有三个线程ABC，
 * 要求 A打印5次->B打印10次->C打印15次，
 * 轮替 A打印5次->B打印10次->C打印15次， 。。。。。。
 * 来10轮
 * 
 * 多线程题目：
 * 1 判断
 * 2 干活
 * 3 唤醒
 * @author zjx
 *
 */
class Sharedata2{

	private int num = 1;
	private Lock lock = new ReentrantLock();
	
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	public void Loop1(int total) throws InterruptedException{
		lock.lock();
		try {
			//1	判断
			while(num != 1){
				condition1.await();
			}
			//2 干活
			for (int i = 1; i <= 5 ; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t 总轮数： "+total);
			}
			//3 通知+唤醒
			num = 2 ;
			condition2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void Loop2(int total) throws InterruptedException{
		lock.lock();
		try {
			while(num != 2){
				condition2.await();
			}
			for (int i = 1; i <= 10 ; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t 总轮数： "+total);
			}
			num = 3 ;
			condition3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void Loop3(int total) throws InterruptedException{
		lock.lock();
		try {
			while(num != 3){
				condition3.await();
			}
			for (int i = 1; i <= 15 ; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t 总轮数： "+total);
			}
			System.out.println();
			num = 1 ;
			condition1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	
}

public class TestThread10 {

	public static void main(String[] args) {
		
		final Sharedata2 sharedata = new Sharedata2();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						sharedata.Loop1(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						sharedata.Loop2(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "B").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						sharedata.Loop3(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "C").start();
	}

}
