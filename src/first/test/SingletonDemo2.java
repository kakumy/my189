package first.test;

public class SingletonDemo2 {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Singleton3 singleton3 = Singleton3.getInstance();
				System.out.println(singleton3);
			}
		}, "A").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Singleton3 singleton3 = Singleton3.getInstance();
				System.out.println(singleton3);
			}
		}, "B").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Singleton3 singleton3 = Singleton3.getInstance();
				System.out.println(singleton3);
			}
		}, "C").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Singleton3 singleton3 = Singleton3.getInstance();
				System.out.println(singleton3);
			}
		}, "D").start();
		
	}

}

class Singleton3 {
	private static Singleton3 singleton = null;

	private Singleton3() {

	}

	public static Singleton3 getInstance(){
		if(singleton==null){
			
			singleton = new Singleton3();
		}
		return singleton;
	}
}