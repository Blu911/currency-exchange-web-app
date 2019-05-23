package pl.blu911.currencyexchange.client;

import com.jcabi.http.request.JdkRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

class HttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyClient.class);

    static String getStringFromUri(String uri) {
        try {
            return new String(new JdkRequest(uri).fetch().binary());
        } catch (IOException e) {
            LOGGER.error("Error while connecting to external URI");
            e.printStackTrace();
        }
        return null;
    }
}
