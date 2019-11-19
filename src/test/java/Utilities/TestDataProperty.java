package Utilities;


import org.junit.Test;

import java.io.*;
import java.util.Properties;


public class TestDataProperty {
    private static Properties testData;
    static
    {
        /*
            if there's a need for cross-browser testing this can be enhanced
         */

        String DataFile = System.getProperty("testDataFile", "chrome.test.properties");

        testData = new Properties();
        try
        {
            testData.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DataFile));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getDataItem(String s) {
        return testData.getProperty(s);
    }

    public void updatePropertyValue(String key,String value) throws IOException {

        String propValue = "equalExperts."+ key ;

        File file = new File("src/test/resources/chrome.test.properties");
        String absolutePath = file.getAbsolutePath();

        System.out.println(file.toString());
        FileInputStream in = new FileInputStream(absolutePath);
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(absolutePath);
        props.setProperty(propValue, value);
        props.store(out, null);
        out.close();
    }
}
