import java.util.concurrent.CountDownLatch;

public class TestBravoWorker extends DataService implements Runnable {

    private CountDownLatch countDownLatch;
    
    public TestBravoWorker()
    {
        
    }
    
    public TestBravoWorker(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    public void run()
    {
                loadDataToList(Thread.currentThread().getName());
                this.countDownLatch.countDown();
    }
    
    public String loadDataToList(String threadName)
    {
        long start = System.nanoTime();
        DataLoader dl = new DataLoader();
        dl.loadData(true);
        long span = (System.nanoTime() - start);
        System.out.println("[Bravo]["+threadName+"] Loaded List Size:" + dl.result.size +"  Execution Time : "+ span / 1000000 +" milliseconds");
    }
    
}
