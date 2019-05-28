package pl.blu911.currencyexchange.client;

import com.jcabi.http.request.JdkRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    public String getStringFromUri(String uri) {
        String result = null;

        try {
            result = new String(new JdkRequest(uri).fetch().binary());
            if (result.equals("")){
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("Error while connecting to external URI, check internet connection");
            e.printStackTrace();
        }
        return result;
    }
}
