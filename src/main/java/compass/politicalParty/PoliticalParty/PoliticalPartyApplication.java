package compass.politicalParty.PoliticalParty;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class PoliticalPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliticalPartyApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper() {return new ModelMapper();}

}
