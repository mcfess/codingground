
public class MainTest{

     public static void main(String []args){
        
        //************************************
        
        int it = 10;   //iterations per job
        boolean executeAplha = true;
        boolean executeBravo = true;
        
        int threadCount = 20;
        
        //************************************
        
        System.out.println(String.format(openingMessage(),it,it));
        long overallStart = System.nanoTime();

        DataService dataService = new DataService(threadCount, it, executeAplha, executeBravo);
        dataService.execute();
        dataService.finishWork();
        DataService.Response response;
         
        long n = System.nanoTime() - overallStart;  
        System.out.println("\nTest Completed");
        System.out.println("\nOverall Run Time : " +  n / 1000000 + " milliseconds\n");
         
     }
     
     private static final String openingMessage(){
         StringBuilder sb = new StringBuilder("\n\n");
         sb
            .append("## ================================================================\n")
            .append("##     Test -- Data Processing Options \n")
            .append("##\n")
            .append("##     Test #1 - %d iterations loaded into  'tree type' map\n")
            .append("##     Test #2 - %d iterations loaded into  'flat style' map\n")
            .append("##\n")
            .append("##     Sample Data Definition:\n")
            .append("##         - 10,499 Rows\n")
            .append("##         - Data Size - 'About' 350kb\n")
            .append("##         - 3 String Objects per row seperated by a comma\n")
            .append("##             - String Object 1: CHAR(2)\n")
            .append("##             - String Object 2: VARCHAR(3) - VARCHAR(14)\n")
            .append("##             - String Object 3: CHAR(4)\n")
            .append("## ================================================================\n");
            
            
        return sb.toString();
            
     }
}
