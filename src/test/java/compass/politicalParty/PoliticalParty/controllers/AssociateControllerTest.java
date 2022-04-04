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
	public void getAssociateById_sucess() throws Exception{
		Integer id = Integer.valueOf(3);
		URI uri = new URI("/api/associate/" + id);
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test 
	public void getAssociateById_notFound() throws Exception{
		Integer id = Integer.valueOf(300);
		URI uri = new URI("/api/associate/" + id);
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
	
	@Test
	public void postAssociateById_badRequest() throws Exception{
		URI uri = new URI("/api/associate/");
		String json = "{" +
				"\"name\":\"\",\n"+
				"\"politicalOffice\":\"PRESIDENTE\",\n" +
				"\"date\":\"2020-03-30\",\n" +
				"\"gender\":\"MASCULINO\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void postAssociateOfParty_sucess() throws Exception{
		URI uri = new URI("/api/associate/politicalParty");
		String json = "{" +
				"\"associateId\":\"5\",\n"+
				"\"partyId\":\"1\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void postAssociateOfParty_badRequest() throws Exception{
		URI uri = new URI("/api/associate/politicalParty");
		String json = "{" +
				"\"associateId\":\"\",\n"+
				"\"partyId\":\"1\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void deleteAssociateOfParty_sucess() throws Exception{
		Integer idAssociate = Integer.valueOf(5);
		Integer idParty = Integer.valueOf(1);
		URI uri = new URI("/api/associate/" + idAssociate + "/politicalParty/" + idParty);

		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void updateAssociateById_sucess() throws Exception{
		
		Integer id = Integer.valueOf(1);
		URI uri = new URI("/api/associate/" + id);
		String json = "{" +
				"\"name\":\"Atualizado\",\n"+
				"\"politicalOffice\":\"PRESIDENTE\",\n" +
				"\"date\":\"2020-03-30\",\n" +
				"\"gender\":\"MASCULINO\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void updateAssociateById_notFound() throws Exception{
		
		Integer id = Integer.valueOf(300);
		URI uri = new URI("/api/associate/" + id);
		String json = "{" +
				"\"name\":\"Atualizado\",\n"+
				"\"politicalOffice\":\"PRESIDENTE\",\n" +
				"\"date\":\"2020-03-30\",\n" +
				"\"gender\":\"MASCULINO\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deleteAssociateById_sucess() throws Exception{
		Integer id = Integer.valueOf(1);
		URI uri = new URI("/api/associate/" + id);

		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deleteAssociateById_notFound() throws Exception{
		Integer id = Integer.valueOf(300);
		URI uri = new URI("/api/associate/" + id);

		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}
}
