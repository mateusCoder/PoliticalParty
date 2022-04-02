package compass.politicalParty.PoliticalParty.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LinkFormParty {
	
	@NotNull
	private Integer partyId;
	private Integer associateId;
	
}
