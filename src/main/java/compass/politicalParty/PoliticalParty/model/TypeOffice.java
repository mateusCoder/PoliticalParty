package compass.politicalParty.PoliticalParty.model;

public enum TypeOffice {

	VEREADOR("Vereadorr"),
	PREFEITO("Prefeito"),
	DEPUTADO_ESTADUAL("Deputado Estadual"),
	DEPUTADO_FEDERAL("Deputado Federal"),
	SENADOR("Senador"),
	GOVERNADOR("Governador"),
	PRESIDENTE("Presidente"),
	NENHUM("Nenhum");
	
	private String description;
	
	TypeOffice(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
