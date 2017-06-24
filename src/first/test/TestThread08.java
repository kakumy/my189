package first.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 二元判断
 */
class Myprint {

	private int num = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void print1(int i) throws InterruptedException {
		lock.lock();
		try {
			while (num != 1) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "\t" + (2 * i + 1) + "\n\t" + (2 * i + 2));
			++num;
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void printA(int i) throws InterruptedException {
		lock.lock();
		try {
			while (num == 1) {
				condition.await();
			}
			--num;
			System.out.println(Thread.currentThread().getName() + "\t" + (char) ('A' + i));
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}

public class TestThread08 {

	public static void main(String[] args) {

		final Myprint myprint = new Myprint();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 26; j++) {

					try {
						myprint.print1(j);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 26; i++) {
					try {
						myprint.printA(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "B").start();

	}

}
