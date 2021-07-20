package es.mindata;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class HeroesApplicationTest {

	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void contextLoads() {
		assertNull(null);
	}

	
}
