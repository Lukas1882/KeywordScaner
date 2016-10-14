import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Luke on 10/13/16.
 */
public class MultiFiles_Test {
    KeyCollector collector1;
    String outputPath;
    @Before
    public void prepareTest() throws Exception {
        collector1 = new KeyCollector();
        outputPath = "./tmp/output_test/output.txt";
    }

    @Test
    public void MultiFileTest() throws Exception {
        collector1.scanFiles();
        String out = new String(Files.readAllBytes(Paths.get(outputPath)));
        assertEquals("key1_p key2_p1 key2_p2 key4_p1 key4_p2 key1_1 key1_2 key1_3  key2_1 key2_2 key3_2 key3_1 key5_1 key5_2", out.trim());
    }
}
