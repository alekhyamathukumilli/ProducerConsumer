import java.sql.Timestamp;
import java.util.*;

public class Buffer {
	static List<String> buffer = new ArrayList<String>();
	static int sizeOfBuffer;
	static int numOfProducers;
	static int numOfConsumers;
	static int ProducerSleepInMilliSeconds;
	static int ConsumerSleepInMilliSeconds;
	static int theNumberOfMessagesToProdeuce;
	static boolean available = false;

	Buffer() {

	}

	Buffer(int sizeOfBuffer, int numOfProducers, int numOfConsumers, int ProducerSleepInMilliSeconds,
			int ConsumerSleepInMilliSeconds, int theNumberOfMessagesToProdeuce) {
		Buffer.sizeOfBuffer = sizeOfBuffer;
		Buffer.numOfConsumers = numOfConsumers;
		Buffer.numOfProducers = numOfProducers;
		Buffer.ProducerSleepInMilliSeconds = ProducerSleepInMilliSeconds;
		Buffer.ConsumerSleepInMilliSeconds = ConsumerSleepInMilliSeconds;
		Buffer.theNumberOfMessagesToProdeuce = theNumberOfMessagesToProdeuce;

	}

	public static  synchronized String produceItem() {
		String produced=null;
			produced = new Timestamp(System.currentTimeMillis()).toString();
				buffer.add(produced);
		return produced;

	}

	public static synchronized void consumeItem(String name) {
		String consumed = null;
		consumed=buffer.get(0);
		buffer.remove(0);
		System.out.println("Thread " + name + " buffer " + consumed+ ". There are "+buffer.size()+" elements in the queue");
	}

	public void sleep(int time) {
		Random rand = new Random();
		try {
			Thread.sleep(rand.nextInt(time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void  runSimulation() {
			Producer producer;
			Consumer consumer;
			for (int i = 1; i <= numOfProducers; i++) {
				String ProducerName = "P" + i;
				producer = new Producer(ProducerName);
				producer.start();
			}
			for (int i = 1; i <=numOfConsumers; i++) {
				String ConsumerName = "C" + i;
				consumer = new Consumer(ConsumerName);
				consumer.start();
			}

		}
}
