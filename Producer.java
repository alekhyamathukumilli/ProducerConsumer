public class Producer extends Thread {
	Buffer bufferClass = new Buffer();
	Producer(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < Buffer.theNumberOfMessagesToProdeuce; i++) {
			while(Buffer.buffer.size()==Buffer.sizeOfBuffer)
			{
				bufferClass.sleep(Buffer.ProducerSleepInMilliSeconds);
			}
			String produced;
			produced=Buffer.produceItem();
			System.out.println("Thread " + getName() + " buffer " + produced + ". There are "+Buffer.buffer.size()+" elements in the queue");
			bufferClass.sleep(Buffer.ProducerSleepInMilliSeconds);
		}
	}

}
