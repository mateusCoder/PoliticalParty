package compass.politicalParty.PoliticalParty.controllers;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.TypeGender;
import compass.politicalParty.PoliticalParty.model.TypeOffice;
import compass.politicalParty.PoliticalParty.repository.AssociateRepository;
import compass.politicalParty.PoliticalParty.repository.PoliticalPartyRepository;

@WebMvcTest(AssociateController.class)
class AssociateControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    ObjectMapper mapper;
	
	@MockBean
	PoliticalPartyRepository partyRepository;
	
	@MockBean
	private AssociateRepository associateRepository;
	
	Associate associate1 = new Associate("Gilberto Nascimento JR", TypeOffice.VEREADOR, LocalDate.now(), TypeGender.MASCULINO);
	Associate associate2 = new Associate("Robert Willians", TypeOffice.PREFEITO, LocalDate.now(), TypeGender.MASCULINO);
	Associate associate3 = new Associate("Maria Dolores da Cunha", TypeOffice.DEPUTADO_ESTADUAL, LocalDate.of(2019, 10, 10), TypeGender.FEMININO);
	
	@Test
	public void deleteAssociateById_success() throws Exception {
	    Mockito.when(associateRepository.findById(associate2.getId())).thenReturn(Optional.of(associate2));
	
	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/associate/2")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void deleteAssociateById_notFound() throws Exception {
	    Mockito.when(associateRepository.findById(5)).thenReturn(null);
	    
	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/associate/5")
	            .contentType(MediaType.APPLICATION_JSON))
	    .andExpect(status().isBadRequest())
	            .andExpect(result ->
	            assertTrue(result.getResolvedException() instanceof NotFoundException))
	    .andExpect(result ->
	            assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
	}

}
