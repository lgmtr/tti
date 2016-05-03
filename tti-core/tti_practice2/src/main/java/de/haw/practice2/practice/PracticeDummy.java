package de.haw.practice2.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.RDFS;

import de.haw.practice2.model.DummyModel;

public class PracticeDummy {
	
	public final static String URI = "http://tti-practice.com/practice2#";
	
	private Resource createResource(DummyModel dm, Model model){
		return model.createResource(URI + dm.getName()).addProperty(DC.title, dm.getTyp());
	}
	
	private Property createProperty(String propUri, Model model){
		return model.createProperty(URI + propUri);
	}
	
	private void dummyTest() throws FileNotFoundException{
		Model model = ModelFactory.createDefaultModel();
		
		//Properties
		Property forceSensitive = createProperty("forceSensitive", model);
		Property jedi = createProperty("jedi", model);
		Property jediMaster = createProperty("jediMaster", model);
		Property jediPadawan = createProperty("jediPadawan", model);
		Property sith = createProperty("sith", model);
		model.add(jedi, RDFS.subPropertyOf, forceSensitive);
		model.add(jediMaster, RDFS.subPropertyOf, jedi);
		model.add(jediPadawan, RDFS.subPropertyOf, jedi);
		model.add(sith, RDFS.subPropertyOf, forceSensitive);
		
		//Resources
		Resource kenobi = createResource(new DummyModel("Obi-Wan Kenobi", "Mensch"), model);
		Resource skywalker = createResource(new DummyModel("Anakin Skywalker", "Mensch"), model);
		Resource maul = createResource(new DummyModel("Darth Maul", "Zabrak"), model);
		skywalker.addProperty(RDFS.subClassOf, kenobi);
		skywalker.addProperty(jediPadawan, "Padawan");
		kenobi.addProperty(jediMaster, "Master");
		maul.addProperty(sith, "Sith");
		model.write(new FileOutputStream(new File("src/test/resources/force.rdf")), "turtle");
		printModel(ModelFactory.createRDFSModel(model));
	}

	private void printModel(InfModel createRDFSModel) {
		Resource x = createRDFSModel.getResource(URI + "Anakin Skywalker");
		// show all of the resource's properties
		StmtIterator iter = x.listProperties();
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement(); // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property predicate = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object
			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");
			if (object instanceof Resource) {
				System.out.print(object.toString());
			} else {
				// object is a literal
				System.out.print(" \"" + object.toString() + "\"");
			}
			System.out.println(" .");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		PracticeDummy pd = new PracticeDummy();
		pd.dummyTest();
	}

}
