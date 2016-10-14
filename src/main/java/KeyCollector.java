import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Luke on 10/13/16.
 */
public class KeyCollector implements Runnable {
    private String inputFolder = "./tmp/collat_test/";
    private File[] files;
    private File file;
    private KeywordPrinter printer;
    private int corePoolSize = 1;
    private int maximumPoolSize = 5;
    private long keepAliveTime = 1000;
    public KeyCollector() throws Exception {
        File folder = new File(inputFolder);
        files = folder.listFiles();
        try {
            printer = new KeywordPrinter();
        } catch(Exception ex){
            throw ex;
        }
    }

    public KeyCollector(File file) throws Exception {
        this.file = file;
        File folder = new File(inputFolder);
        files = folder.listFiles();
        try {
            printer = new KeywordPrinter();
        } catch(Exception ex){
            throw ex;
        }
    }

    // Scan all the files in the folder.
    public void scanFiles() throws Exception {
        KeywordPrinter.prepairWriter();
        File folder = new File(inputFolder);
        File[] files = folder.listFiles();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                queue, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && files[i].getName().endsWith(".txt")) {
                threadPool.execute(new KeyCollector(files[i]));
                Thread.sleep(1);
            } else
                continue;
        }
        threadPool.shutdown();
        KeywordPrinter.finishWriting();
    }

    // Scan one single file key words.
    private void scanFile () throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                scanString(line);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    // Scan the string of one line in the file, add the key words to the buffer or files.
    private void scanString(String st) throws Exception {
        String[] strings = st.split(" ",2);
        try {
            if (!strings[0].equals("#")){
                if (strings.length < 2 || !strings[1].startsWith("."))
                    printer.addNormal(strings[0]);
                else
                    printer.addPrimitive(strings[0]);
            }
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void run() {
        try {
            scanFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInputFolder(String inputFolder) {
        this.inputFolder = inputFolder;
    }
}
