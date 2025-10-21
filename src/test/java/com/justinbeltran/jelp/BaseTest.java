package com.justinbeltran.jelp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseTest {

    @BeforeEach
    public void before() {
        // MockitoExtension handles initialization automatically
    }
}
