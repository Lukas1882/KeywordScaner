/**
 * Created by Luke on 10/13/16.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here
        try {
            KeyCollector collector = new KeyCollector();
            collector.scanFiles();
        } catch(Exception ex){
            System.out.print(ex.getLocalizedMessage());
        }
    }
}
