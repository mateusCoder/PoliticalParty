package compass.politicalParty.PoliticalParty.model;

public enum TypeGender {
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	private String description;

	private TypeGender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
