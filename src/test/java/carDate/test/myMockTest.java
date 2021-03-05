package carDate.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest (webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class myMockTest {
	
	@Autowired
	MockMvc mockMvc;
	
	/* Only work on /login, other not work, guess due to security, or need login password*/
//	@Disabled
//	@Test
	public void testBasicGet() throws Exception {
		mockMvc.perform(
			get("/login"))
			.andDo(print())
			.andExpect(status().isOk());
	}

}