package weather;

import io.restassured.RestAssured;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class BaseWeatherSystem {
    final private String propFileName = "test.config.properties";
    private Properties prop;

    public BaseWeatherSystem() {
        try {
            // Load properties
            this.loadProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(prop.getProperty("api"));
        RestAssured.baseURI = prop.getProperty("api");
    }

    private void loadProperties() throws Exception {
        prop = new Properties();
        InputStream inputStream;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                this.prop.load(inputStream);
                inputStream.close();
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
