package de.haw.part2;

import java.time.format.DateTimeFormatter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC;

import de.haw.part2.model.BibModel;

public class ResourceCreator {
	
	public static Resource createResource(String uri, Model model, BibModel bibModel){
		return model.createResource(uri + "#" + bibModel.getId().toString())
				.addProperty(DC.title, bibModel.getTitle())
				.addProperty(DC.subject, bibModel.getSubject())
				.addProperty(DC.date, bibModel.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
				.addProperty(DC.creator, bibModel.getCreator())
				.addProperty(DC.type, bibModel.getType().getEnglish())
				.addProperty(DC.description, bibModel.getDescription())
				.addProperty(DC.relation, bibModel.getRelation());
	}

}
