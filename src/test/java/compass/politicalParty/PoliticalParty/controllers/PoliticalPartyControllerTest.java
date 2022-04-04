package compass.politicalParty.PoliticalParty.controllers;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class PoliticalPartyControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllParty_sucess() throws Exception{
		
		URI uri = new URI("/api/politicalParty");
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test 
	public void getPartyById_sucess() throws Exception{
		Integer id = Integer.valueOf(3);
		URI uri = new URI("/api/politicalParty/" + id);
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test 
	public void getPartyById_notFound() throws Exception{
		Integer id = Integer.valueOf(300);
		URI uri = new URI("/api/politicalParty/" + id);
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}

}
