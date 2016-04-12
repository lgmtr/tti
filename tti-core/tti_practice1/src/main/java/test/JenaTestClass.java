package test;

import java.io.FileNotFoundException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class JenaTestClass {
	
	public static void main(String args[]) throws FileNotFoundException {

		// some definitions
		String personURI1 = "http://somewhere/JohnSmith";
		String personURI2 = "http://somewhere/JaneSmith";
		String givenName1 = "John";
		String givenName2 = "Jane";
		String familyName = "Smith";
		String fullName1 = givenName1 + " " + familyName;
		String fullName2 = givenName2 + " " + familyName;
		// create an empty model
		Model model = ModelFactory.createDefaultModel();

		// create the resource
		// and add the properties cascading style
		Resource johnSmith = model.createResource(personURI1).addProperty(VCARD.FN, fullName1)
				.addProperty(VCARD.N, model.createResource().addProperty(VCARD.Given, givenName1).addProperty(VCARD.Family, familyName));
		
		Resource janeSmith = model.createResource(personURI2).addProperty(VCARD.FN, fullName2)
				.addProperty(VCARD.N, model.createResource().addProperty(VCARD.Given, givenName2).addProperty(VCARD.Family, familyName));
		
//		model.write(new FileOutputStream(new File("john.ttl")));
		model.write(System.out);
	}

}
