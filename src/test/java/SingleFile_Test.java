import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.*;


/**
 * Created by Luke on 10/13/16.
 */
public class SingleFile_Test {
    KeyCollector collector1;
    KeyCollector collector2;
    KeyCollector collector3;
    KeyCollector collector4;
    String outputPath;
    @Before
    public void prepareTest() throws Exception {
        collector1 = new KeyCollector();
        collector1.setInputFolder("./tmp/Collat_test/unit_test/1/");
        collector2 = new KeyCollector();
        collector2.setInputFolder("./tmp/Collat_test/unit_test/2/");
        collector3 = new KeyCollector();
        collector3.setInputFolder("./tmp/Collat_test/unit_test/3/");
        collector4 = new KeyCollector();
        collector4.setInputFolder("./tmp/Collat_test/unit_test/4/");
        outputPath = "./tmp/output_test/output.txt";
    }

    @Test
    public void singleFileTest1() throws Exception {
        collector1.scanFiles();
        String out = new String(Files.readAllBytes(Paths.get(outputPath)));
        assertEquals("C A B", out.trim());
    }

    @Test
    public void singleFileTest2() throws Exception {
        collector2.scanFiles();
        String out = new String(Files.readAllBytes(Paths.get(outputPath)));
        assertEquals("C A B", out.trim());
    }

    @Test
    public void singleFileTest3() throws Exception {
        collector3.scanFiles();
        String out = new String(Files.readAllBytes(Paths.get(outputPath)));
        assertEquals("B C A", out.trim());
    }

    @Test
    public void singleFileTest4() throws Exception {
        collector4.scanFiles();
        String out = new String(Files.readAllBytes(Paths.get(outputPath)));
        assertEquals("shape food-type color red apple onion long banana yellow roundish fruit pear corn vegetable", out.trim());
    }


}
