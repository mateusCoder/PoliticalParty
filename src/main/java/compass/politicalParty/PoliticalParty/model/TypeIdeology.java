package compass.politicalParty.PoliticalParty.model;

public enum TypeIdeology {
	
	DIREITA("Direita"),
	CENTRO("Centro"),
	ESQUERDA("Esquerda");
	
	private String description;

	TypeIdeology(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
