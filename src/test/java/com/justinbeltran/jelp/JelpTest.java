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

import com.justinbeltran.jelp.model.Business;
import com.justinbeltran.jelp.model.Results;

public class JelpTest extends BaseTest {

    @Mock
    private Api api;

    @InjectMocks
    private Jelp jelp = new Jelp("consumerKey", "consumerSecret", "tokenKey", "tokenSecret");

    @Test
    public void search() throws Exception {

        Response resp = mock(Response.class);
        when(api.search(Mockito.anyString(), Mockito.anyString())).thenReturn(resp);

        when(resp.isSuccessful()).thenReturn(true);

        String exampleResponse = IOUtils.toString(this.getClass().getResourceAsStream("/example_search_result.json"));
        when(resp.getBody()).thenReturn(exampleResponse);

        Results results = jelp.search("sushi", "Irvine, CA");
        assertThat(results.getTotal(), is(176));
    }

    @Test(expected = IllegalArgumentException.class)
    public void search_unsuccessful() {
        Response resp = mock(Response.class);
        when(api.search(Mockito.anyString(), Mockito.anyString())).thenReturn(resp);

        when(resp.isSuccessful()).thenReturn(false);

        jelp.search("sushi", "Irvine, CA");
    }

    @Test
    public void business() throws Exception {

        Response resp = mock(Response.class);
        when(api.business(Mockito.anyString())).thenReturn(resp);

        when(resp.isSuccessful()).thenReturn(true);

        String exampleResponse = IOUtils.toString(this.getClass().getResourceAsStream("/example_business.json"));
        when(resp.getBody()).thenReturn(exampleResponse);

        Business business = jelp.business("yelp-san-francisco");
        assertThat(business.getDisplay_phone(), is("+1-415-677-9744"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void business_unsuccessful() {
        Response resp = mock(Response.class);
        when(api.search(Mockito.anyString(), Mockito.anyString())).thenReturn(resp);

        when(resp.isSuccessful()).thenReturn(false);

        jelp.search("sushi", "Irvine, CA");
    }

    private class Name implements Comparable<Name> {
        private String firstName;
        private String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
            result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Name other = (Name) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (firstName == null) {
                if (other.firstName != null)
                    return false;
            } else if (!firstName.equals(other.firstName))
                return false;
            if (lastName == null) {
                if (other.lastName != null)
                    return false;
            } else if (!lastName.equals(other.lastName))
                return false;
            return true;
        }

        private JelpTest getOuterType() {
            return JelpTest.this;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int compareTo(Name other) {
            return this.lastName.compareTo(other.getLastName());
        }

        public String toString() {
            return firstName + " " + lastName;
        }

    }

}
