import java.util.concurrent.CountDownLatch;

public class TestAlphaWorker implements Runnable {

    private CountDownLatch countDownLatch;
   
    public TestAlphaWorker()
    {
    }
    
    public TestAlphaWorker(CountDownLatch countDownLatch)
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
        System.out.println("[Alpha]["+threadName+"] Loaded List Size:" + dl.result.size +"  Execution Time : "+ span / 1000000 +" milliseconds");
    }
    
}
