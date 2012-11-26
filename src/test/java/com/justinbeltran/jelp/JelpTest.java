package com.justinbeltran.jelp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.scribe.model.Response;

import com.justinbeltran.jelp.model.Results;

public class JelpTest extends BaseTest {

	@Mock
	private Api api;

	@InjectMocks
	private Jelp jelp = new Jelp("consumerKey", "consumerSecret", "tokenKey",
			"tokenSecret");

	@Test
	public void search() throws Exception {

		Response resp = mock(Response.class);
		when(api.search(Mockito.anyString(), Mockito.anyString())).thenReturn(
				resp);
		
		when(resp.isSuccessful()).thenReturn(true);
		
		String exampleResponse = IOUtils.toString(this.getClass().getResourceAsStream("/example_search_result.json"));
		when(resp.getBody()).thenReturn(exampleResponse);

		Results results = jelp.search("sushi", "Irvine, CA");
		assertThat(results.getTotal(), is(176));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void search_unsuccessful(){
		Response resp = mock(Response.class);
		when(api.search(Mockito.anyString(), Mockito.anyString())).thenReturn(
				resp);
		
		when(resp.isSuccessful()).thenReturn(false);

		jelp.search("sushi", "Irvine, CA");
	}

}
