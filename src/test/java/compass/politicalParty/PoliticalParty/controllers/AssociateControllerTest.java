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
class AssociateControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllAssociate_sucess() throws Exception{
		
		URI uri = new URI("/api/associate");
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test 
	public void getAssociateById_notFound() throws Exception{
		URI uri = new URI("/api/associate/141");
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void postAssociateById_sucess() throws Exception{
		URI uri = new URI("/api/associate/");
		String json = "{" +
				"\"name\":\"Atualizado\",\n"+
				"\"politicalOffice\":\"PRESIDENTE\",\n" +
				"\"date\":\"2020-03-30\",\n" +
				"\"gender\":\"MASCULINO\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	
	
}
