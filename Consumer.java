public class Consumer extends Thread {
	Buffer bufferClass = new Buffer();

	Consumer(String name) {
		super(name);
	}

	public synchronized void run() {
		
		for(int i=0;i<((Buffer.theNumberOfMessagesToProdeuce*Buffer.numOfProducers)/Buffer.numOfConsumers);i++)
		{
			synchronized (Consumer.class) {
			while (Buffer.buffer.size()==0) {
				//System.out.println("inSleep");
				bufferClass.sleep(Buffer.ConsumerSleepInMilliSeconds);
			}
			
			Buffer.consumeItem(getName());
		}
		
		//System.out.println("Thread " + getName() + " buffer " + consumed+ ". There are "+Buffer.buffer.size()+" elements in the queue");
		bufferClass.sleep(Buffer.ConsumerSleepInMilliSeconds);
		}
	}

}