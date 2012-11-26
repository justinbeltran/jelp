package com.justinbeltran.jelp;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTest {

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
}
