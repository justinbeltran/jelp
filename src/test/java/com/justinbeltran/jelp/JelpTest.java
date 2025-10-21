package com.justinbeltran.jelp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.justinbeltran.jelp.model.Business;
import com.justinbeltran.jelp.model.Results;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JelpTest extends BaseTest {

    @Mock
    private Api api;

    @InjectMocks
    private Jelp jelp = new Jelp("test-api-key");

    @Test
    public void search() throws Exception {
        @SuppressWarnings("unchecked")
        HttpResponse<String> resp = mock(HttpResponse.class);
        when(api.search(anyString(), anyString())).thenReturn(resp);

        when(resp.statusCode()).thenReturn(200);

        String exampleResponse = IOUtils.toString(
                this.getClass().getResourceAsStream("/example_search_result.json"),
                StandardCharsets.UTF_8);
        when(resp.body()).thenReturn(exampleResponse);

        Results results = jelp.search("sushi", "Irvine, CA");
        assertEquals(176, results.getTotal());
    }

    @Test
    public void search_unsuccessful() throws Exception {
        @SuppressWarnings("unchecked")
        HttpResponse<String> resp = mock(HttpResponse.class);
        when(api.search(anyString(), anyString())).thenReturn(resp);

        when(resp.statusCode()).thenReturn(400);
        when(resp.body()).thenReturn("{\"error\": \"Bad Request\"}");

        assertThrows(IllegalArgumentException.class, () -> {
            jelp.search("sushi", "Irvine, CA");
        });
    }

    @Test
    public void business() throws Exception {
        @SuppressWarnings("unchecked")
        HttpResponse<String> resp = mock(HttpResponse.class);
        when(api.business(anyString())).thenReturn(resp);

        when(resp.statusCode()).thenReturn(200);

        String exampleResponse = IOUtils.toString(
                this.getClass().getResourceAsStream("/example_business.json"),
                StandardCharsets.UTF_8);
        when(resp.body()).thenReturn(exampleResponse);

        Business business = jelp.business("urban-curry-san-francisco");
        assertEquals("+1-415-677-9744", business.getDisplay_phone());
    }

    @Test
    public void business_unsuccessful() throws Exception {
        @SuppressWarnings("unchecked")
        HttpResponse<String> resp = mock(HttpResponse.class);
        when(api.business(anyString())).thenReturn(resp);

        when(resp.statusCode()).thenReturn(404);
        when(resp.body()).thenReturn("{\"error\": \"Not Found\"}");

        assertThrows(IllegalArgumentException.class, () -> {
            jelp.business("non-existent-business");
        });
    }
}
