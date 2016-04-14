package de.haw.part2.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.testng.annotations.Test;

import de.haw.part2.ResourceCreator;
import de.haw.part2.model.BibModel;
import de.haw.part2.model.Type;

public class RDFStatementTest {

	private static final String URI = "http://somewhere/lib";

	@Test
	public void createStatement() throws FileNotFoundException {
		List<BibModel> bibModelList = createLib();
		List<Resource> resourceList = new ArrayList<>();
		Model model = ModelFactory.createDefaultModel();

		for (BibModel bibModel : bibModelList)
			resourceList.add(ResourceCreator.createResource(URI, model, bibModel));
		// model.write(System.out, "RDF/XML-ABBREV");
		model.write(new FileOutputStream(new File("src/test/resources/lib.rdf")), "RDF/XML-ABBREV");

	}

	@Test
	public void readRDFFile() {
		String inputFileName = "src/test/resources/sascha.rdf";
		Model model = ModelFactory.createDefaultModel();
		
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}

		model.read(in, "RDF/XML-ABBREV");

		StmtIterator iter = model.listStatements();

		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement(); // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property predicate = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object
			String item = "";

			if (predicate.getLocalName().equalsIgnoreCase("subject")) {
				if (object instanceof Resource) {
					item = object.toString();
				} else {
					// object is a literal
					item = object.toString();
				}
				if (item.equalsIgnoreCase("Architecture") || item.equalsIgnoreCase("Architektur")) {
//					System.out.println(" " + stmt.getSubject() + ".");
					System.out.println(subject.toString());
				}
			}
		}

		// model.write(System.out, "RDF/XML-ABBREV");
	}

	private List<BibModel> createLib() {
		List<BibModel> lib = new ArrayList<>();
		lib.add(new BibModel(UUID.randomUUID(), "Candle in the Wind", "Diana, Princess of Wales", LocalDate.of(1997, 1, 1), "John, Elton",
				Type.SOUND, "Tribute to a dead princess", "IsVersionOf Elton John's 1976 song Candle in the Wind"));
		lib.add(new BibModel(UUID.randomUUID(), "Virtual und Augmented Reality (VR / AR)", "Virtual and Augmented Reality", LocalDate.of(
				2013, 1, 1), "Doerner, Ralf and Broll, Wolfgang and Grimm, Paul and Jung, Bernhard", Type.BOOK,
				"Grundlagen und Methoden der Virtuellen und Augmentierten Realität", "doi={10.1007/978-3-642-28903-3}"));
		lib.add(new BibModel(UUID.randomUUID(), "Masterkurs Computergrafik und Bildverarbeitung", "Computergraphic", LocalDate.of(2004, 8,
				1), "Nischwitz, Alfred and Haberäcker, Peter", Type.BOOK, "Masterkurs Computergrafik und Bildverarbeitung",
				"isbn={3-528-05874-9}"));
		lib.add(new BibModel(UUID.randomUUID(), "Biographia Literaria", "Theory of Virtual Reality", LocalDate.of(2004, 7, 1),
				"Coleridge, Samuel Taylor", Type.BOOK, "Projekt Gutenberg", "Virtual Reality"));
		lib.add(new BibModel(UUID.randomUUID(), "Beyond Software Architecture: Creating and Sustaining Winning Solutions", "Architecture",
				LocalDate.of(2003, 1, 1), "Hohmann, Luke", Type.BOOK, "Creating and Sustaining Winning Solutions", "ACM Library"));
		lib.add(new BibModel(UUID.randomUUID(), "Software Architecture in Practice", "Architecture", LocalDate.of(2012, 1, 1),
				"Bass, Len and Clements, Paul and Kazman, Rick", Type.BOOK,
				"Great reference for class, and also a great reference for preparing technical proposals.",
				"isbn = {0321815734, 9780321815736}"));
		return lib;
	}

}
