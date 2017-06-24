package first.test;

class Print {

	private int num = 0;// 是0时打印数字，是1时打印字母

	public synchronized void print1(int i) throws InterruptedException {

		while (num != 0) {
			this.wait();
		}

		System.out.println(Thread.currentThread().getName() + "\t" + (2*i+1)+"\n\t"+(2*i+2));
		++num;
		this.notifyAll();
	}

	public synchronized void printA(int i) throws InterruptedException {

		while (num == 0) {
			this.wait();
		}
		
		--num;
		
		System.out.println(Thread.currentThread().getName() + "\t" + (char)('A'+i));
		
		this.notifyAll();
	}

}

public class TestThread07 {

	public static void main(String[] args) {

		final Print p = new Print();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 26; i++) {
						p.print1(i);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "X").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 26; i++) {
						p.printA(i);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Y").start();
		
	}

}

// class test{
// public void char1() {
// StringBuffer buf = new StringBuffer();
// for (int i = (int) 'A', length = (int) 'Z'; i <= length; i++) {
// buf.append((char) i);
// }
// System.out.println(buf);
// }

// public void char2() {
// char a = 'a';//A:65 a:97
// for (int i = 97; i < 123; i++) {
// System.out.print(a);
// a++;
// }
//
// System.out.println();

// char A = 'A';
// for (int i = 65; i <= 90; i++) {
// System.out.print(A);
// A++;
// }
// }
// }