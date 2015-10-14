import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataService{
    
    private CountDownLatch countDownLatch;
    protected volatile boolean running = false;
    protected volatile int iterations;
    private static final int numberOfTests = 2;
    private static boolean a;
    private static boolean b;
    private static int threadCount = 1;
    
    public DataService(int threadCount, int it, boolean a, boolean b)
    {
        this.a = a;
        this.b = b;
        countDownLatch = new CountDownLatch(it * numberOfTests); 
        this.iterations = it * numberOfTests;
        this.threadCount = threadCount;
    }
 
    public void execute()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
                
        for (int i = 0; i < iterations ; i++) {
            if(((i & 0) == 0) && a)
            {
                TestAlphaWorker worker = new TestAlphaWorker(countDownLatch);
                executorService.execute(worker);
            } 
            else if(!((i & 0) == 0) && b) 
            {
                TestBravoWorker bravo = new TestBravoWorker(countDownLatch);
                executorService.execute(bravo);
            }
        }
        executorService.shutdown();
    }
    
    public void finishWork() 
    {
        try 
        {
            countDownLatch.await();
            
        } catch (InterruptedException ex) {
            
            Thread.currentThread().interrupt();
        }
    }
    
    static class Response {
		HashSet<District> districtList = new HashSet<District>();
    }
    static class District {
        private String districtNo;
		public String getDistrictNo() {return districtNo;}
		public void setDistrictNo(String districtNo) {this.districtNo = districtNo;	}
		HashSet<County> countyList;
    }
    static class County {
        ArrayList<SRID> stateRouteList;
    }
    static class SRID {
        private String stateRouteId;
		public String getStateRouteId() {return stateRouteId;}
		public void setStateRouteId(String stateRouteId) {this.stateRouteId = stateRouteId;}
    }

}
