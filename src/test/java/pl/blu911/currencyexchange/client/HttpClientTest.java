package pl.blu911.currencyexchange.client;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HttpClientTest {

    @Test
    public void shouldReturnExpectedString() throws IOException {
        String returnedString = HttpClient.getStringFromUri("https://support.oneskyapp.com/hc/en-us/article_attachments/202761627/example_1.json");

        String expectedString = "{\n" +
                "    \"fruit\": \"Apple\",\n" +
                "    \"size\": \"Large\",\n" +
                "    \"color\": \"Red\"\n" +
                "}";

        assertThat(returnedString, Matchers.is(expectedString));
    }
}