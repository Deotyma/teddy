package com.teddy_blue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value="test")
@Sql(scripts= {"classpath:db/schema-test.ddl.sql", "classpath:db/data-test.dml.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class BaseMvcTests {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    void shouldBeTrue() {
	//mvc.perform(null)
	assertTrue(false);
    }
    
   

}
