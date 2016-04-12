package de.haw.part2.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.testng.annotations.Test;

import de.haw.part2.ResourceCreator;
import de.haw.part2.model.BibModel;
import de.haw.part2.model.Type;

public class RDFStatementTest {
	
	private static final String URI = "http://somewhere/lib";
	
	@Test
	public void createStatement(){
		List<BibModel> bibModelList = createLib();
		Model model = ModelFactory.createDefaultModel();
		Resource eltonJohn = ResourceCreator.createResource(URI, model, bibModelList.get(0));
		model.write(System.out);
	}

	private List<BibModel> createLib() {
		List<BibModel> lib = new ArrayList<>();
		lib.add(new BibModel(
				UUID.randomUUID(), 
				"Candle in the Wind", 
				"Diana, Princess of Wales", 
				LocalDate.of(1997, 1, 1), 
				"John, Elton", 
				Type.SOUND, 
				"Tribute to a dead princess", 
				"IsVersionOf Elton John's 1976 song Candle in the Wind"));
		return lib;
	}

}
