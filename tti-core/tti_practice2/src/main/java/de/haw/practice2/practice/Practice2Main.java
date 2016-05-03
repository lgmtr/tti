package de.haw.practice2.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDFS;

public class Practice2Main {

	public static final String URI = "http://tti-practice.com/practice2#";

	public void createSchema() throws FileNotFoundException {
		Model model = ModelFactory.createDefaultModel();

		// Properties
		Property action = model.createProperty(URI, "action");
		Property fantasy = model.createProperty(URI, "fantasy");
		Property scify = model.createProperty(URI, "scify");
		Property horror = model.createProperty(URI, "horror");
		Property adventure = model.createProperty(URI, "adventure");
		Property rpg = model.createProperty(URI, "rpg");
		Property hachNSlay = model.createProperty(URI, "hachNSlay");
		Property shooter = model.createProperty(URI, "shooter");
		Property fps = model.createProperty(URI, "fps");
		Property tps = model.createProperty(URI, "tps");
		Property historic = model.createProperty(URI, "historic");
		Property modern = model.createProperty(URI, "modern");
		Property koreanRPG = model.createProperty(URI, "koreanRPG");
		Property westernRPG = model.createProperty(URI, "westernRPG");
		Property strategic = model.createProperty(URI, "strategic");
		Property building = model.createProperty(URI, "building");
		Property conquer = model.createProperty(URI, "conquer");
		Property spaceshooter = model.createProperty(URI, "spaceshooter");
		Property simulation = model.createProperty(URI, "simulation");
		Property pointNClick = model.createProperty(URI, "pointNClick");
		model.add(rpg, RDFS.subPropertyOf, action);
		model.add(rpg, RDFS.subPropertyOf, fantasy);
		model.add(rpg, RDFS.subPropertyOf, scify);
		model.add(hachNSlay, RDFS.subPropertyOf, rpg);
		model.add(koreanRPG, RDFS.subPropertyOf, rpg);
		model.add(westernRPG, RDFS.subPropertyOf, rpg);
		model.add(shooter, RDFS.subPropertyOf, action);
		model.add(shooter, RDFS.subPropertyOf, scify);
		model.add(fps, RDFS.subPropertyOf, shooter);
		model.add(tps, RDFS.subPropertyOf, shooter);
		model.add(spaceshooter, RDFS.subPropertyOf, shooter);
		model.add(modern, RDFS.subPropertyOf, shooter);
		model.add(historic, RDFS.subPropertyOf, shooter);
		model.add(strategic, RDFS.subPropertyOf, action);
		model.add(strategic, RDFS.subPropertyOf, fantasy);
		model.add(strategic, RDFS.subPropertyOf, scify);
		model.add(building, RDFS.subPropertyOf, strategic);
		model.add(conquer, RDFS.subPropertyOf, strategic);
		model.add(simulation, RDFS.subPropertyOf, action);
		model.add(simulation, RDFS.subPropertyOf, scify);
		model.add(pointNClick, RDFS.subPropertyOf, adventure);
		model.add(adventure, RDFS.subPropertyOf, action);
		model.add(adventure, RDFS.subPropertyOf, scify);
		model.add(horror, RDFS.subPropertyOf, action);

		// Resources
		Resource battlefield1942 = model.createResource(URI + "battlefield1942").addProperty(fps, "FPS").addProperty(historic, "Historic");
		Resource wow = model.createResource(URI + "wow").addProperty(westernRPG, "westernRPG");
		Resource wowTBC = model.createResource(URI + "wowTBC").addProperty(westernRPG, "westernRPG");
		Resource d3 = model.createResource(URI + "d3").addProperty(hachNSlay, "hackNSlay");
		Resource d3ROS = model.createResource(URI + "d3ROS").addProperty(hachNSlay, "hackNSlay");
		Resource simcity = model.createResource(URI + "simcity").addProperty(building, "building");
		Resource cnc = model.createResource(URI + "cnc").addProperty(conquer, "conquer");
		Resource monkeyisland = model.createResource(URI + "monkeyisland").addProperty(pointNClick, "pointNClick");
		wowTBC.addProperty(RDFS.subClassOf, wow);
		d3ROS.addProperty(RDFS.subClassOf, d3);
		
		model.write(new FileOutputStream(new File("src/test/resources/game.rdf")), "RDF/XML-ABBREV");
		printModel(ModelFactory.createRDFSModel(model), "d3ROS");
		System.out.println();
		printModel(ModelFactory.createRDFSModel(model), "battlefield1942");
	}

	private void printModel(InfModel createRDFSModel, String item) {
		Resource x = createRDFSModel.getResource(URI + item);
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
	
}
