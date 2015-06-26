package interview;

public class ProducerConsumer {
	public static void main(String[] args) {
		QueueBuffer buffer = new QueueBuffer();
		Producer p = new Producer(buffer);
		Consumer c1 = new Consumer(buffer);
		Consumer c2 = new Consumer(buffer);
		Consumer c3 = new Consumer(buffer);
		Thread tp = new Thread(p);
		Thread tc1 = new Thread(c1);
		Thread tc2 = new Thread(c2);
		Thread tc3 = new Thread(c3);
		tp.start();
		tc1.start();
		tc2.start();
		tc3.start();
	}
}

class Instance {
	private int id;

	Instance(int id) {
		this.id = id;
	}

	public String toString() {
		return "Instance :" + id;
	}
}

class QueueBuffer {
	Instance sm[] = new Instance[6];
	int index = 0;

	public synchronized void push(Instance m) {
		try {
			while (index == sm.length) {
				System.out.println("生产满了");
				this.wait();
			}
			this.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalMonitorStateException e) {
			e.printStackTrace();
		}

		sm[index] = m;
		index++;
		System.out.println("生产了：" + m + " 共" + index + "个数据");
	}

	public synchronized Instance pop() {
		try {
			while (index == 0) {
				System.out.println("消费光了");
				this.wait();
			}
			this.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalMonitorStateException e) {
			e.printStackTrace();
		}
		index--;
		System.out.println("消费了：---------" + sm[index] + " 共" + index + "个数据");
		return sm[index];
	}
}

class Producer implements Runnable {
	QueueBuffer ss = new QueueBuffer();

	Producer(QueueBuffer ss) {
		this.ss = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			Instance m = new Instance(i);
			ss.push(m);
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	QueueBuffer ss = new QueueBuffer();

	Consumer(QueueBuffer ss) {
		this.ss = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			Instance m = ss.pop();
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}