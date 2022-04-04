package compass.politicalParty.PoliticalParty.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LinkFormParty {
	
	@NotNull(message="ID Partido é requisitado")
	private Integer partyId;
	
	@NotNull(message="ID Associado é requisitado")
	private Integer associateId;
	
}
