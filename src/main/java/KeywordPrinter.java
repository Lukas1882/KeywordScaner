import java.io.*;

/**
 * Created by Luke on 10/13/16.
 */
public class KeywordPrinter {
    private static StringBuffer primitiveKeywords = new StringBuffer();
    private static StringBuffer normorlKeywords = new StringBuffer();
    private static File outputPrimitiveFile = new File("./tmp/output_test/primitive.txt");
    private static File outputNormalFile = new File("./tmp/output_test/normal.txt");
    private static File outputFile = new File("./tmp/output_test/output.txt");
    // StringBuffer biggest size for memory safe
    private int stringBlockSize = 200;


    public KeywordPrinter() throws Exception {
        if (outputPrimitiveFile.exists())
            outputPrimitiveFile.delete();
        if (outputNormalFile.exists())
            outputNormalFile.delete();
        if (outputFile.exists())
            outputFile.delete();
        try{
            outputPrimitiveFile.createNewFile();
            outputNormalFile.createNewFile();
            outputFile.createNewFile();
        } catch (Exception ex){
            throw ex;
        }
    }
    public static void prepairWriter() throws IOException {
        if (outputPrimitiveFile.exists())
            outputPrimitiveFile.delete();
        if (outputNormalFile.exists())
            outputNormalFile.delete();
        if (outputFile.exists())
            outputFile.delete();

    }


    public void addPrimitive(String key) throws Exception {
        try{
            addKeyword(key, primitiveKeywords, outputPrimitiveFile);
        } catch (Exception ex){
            throw ex;
        }
    }

    public void addNormal(String key) throws Exception {
        try {
            addKeyword(key, normorlKeywords, outputNormalFile);
        } catch(Exception ex){
            throw ex;
        }
    }

    // Add the rest data in the buffers to files.
    public static void finishWriting() throws Exception {
        try {
            fileWriter(normorlKeywords.toString(),outputNormalFile);
            fileWriter(primitiveKeywords.toString(),outputPrimitiveFile);
            normorlKeywords.delete(0, normorlKeywords.length());
            primitiveKeywords.delete(0, primitiveKeywords.length());
            mergeKeyFiles();
            removeDuplicated();
        } catch (Exception ex){
            throw ex;
        }
    }


    private static void mergeKeyFiles() throws IOException {
        InputStream initialStream = new FileInputStream(outputPrimitiveFile);
        OutputStream outStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = initialStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        initialStream.close();
        initialStream = new FileInputStream(outputNormalFile);
        while ((bytesRead = initialStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        initialStream.close();
        outStream.close();
    }

    private static void removeDuplicated(){
        /*
        We suppose to have the function to remove the duplicated key words. However, since the number of the keys can be
        very big, to store the key into a list or a array will use up machine memory. We don't consider the detail of this
        function.
        In REAL project, the keys should be stored in DB instead of files, while set as unique indexed key column, therefore, there
        is no worry for the memory or performance.
        DB Table Design :
        .....................................................................................
        KEY_ID(Primary, unique, indexed, int) ;  KEY_VALUE(unique, indexed) ; PRIMITIVE(bool)
        .....................................................................................
         */
    }

    private void addKeyword(String key, StringBuffer sb, File file) throws Exception {
        try {
            // If adding this key to buffer exceeds the limit, throw the exception
            if (sb.length() + key.length() >= stringBlockSize)
                throw new OutOfMemoryError();
            else
                sb.append(key + ' ');
        } catch (OutOfMemoryError e) {
            try {
                wirteFile(key, sb, file);
            } catch(Exception ex){
                throw ex;
            }
        }
    }

    private static void wirteFile(String key, StringBuffer sb, File file) throws Exception {
        try {
            //the true will append the new data
            fileWriter(sb.toString(),file);
            fileWriter(key + ' ',file);
        } catch (IOException ex) {
            throw ex;
        } finally {
            sb.delete(0, sb.length());
        }
    }

    private static void fileWriter(String st, File file) throws Exception {
        FileWriter fw = new FileWriter(file.getPath(),true);
        try{
            fw.write(st);
        } catch (Exception ex){
            throw ex;
        }finally {
            fw.close();
        }
    }

}
