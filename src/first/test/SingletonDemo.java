package first.test;

public class SingletonDemo {

	public static void main(String[] args) {
		Singleton1 singleton1 = Singleton1.getInstance();
		Singleton1 singleton11 = Singleton1.getInstance();
		System.out.println(singleton1 == singleton11);

		Singleton2 singleton2 = Singleton2.getInstance();
		Singleton2 singleton22 = Singleton2.getInstance();
		System.out.println(singleton2 == singleton22);

	}

}

// 饿汉式
class Singleton1 {
	// 2. 在类的内部创建对象
	private static Singleton1 instance = new Singleton1();

	// 1. 构造器私化
	private Singleton1() {
	}

	// 3. 提供公共的 get 方法
	public static Singleton1 getInstance() {
		return instance;
	}

}

// 懒汉式:存在线程安全问题
class Singleton2 {
	private static Singleton2 singleton2 = null;

	private Singleton2() {
	}

	public static Singleton2 getInstance() {

		if (singleton2 == null) {
			singleton2 = new Singleton2();
		}
		return singleton2;

	}

}