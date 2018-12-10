package examples;

import java.util.List;
import java.util.ArrayList;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

/**
 * This class contains methods for reading resources from the FHIR server.
 */
public class SimpleRead {

    IGenericClient client = null;

    public SimpleRead(String baseUrl) {
        FhirContext ctx = FhirContext.forDstu3();
        client = ctx.newRestfulGenericClient(baseUrl);
    }

    /**
     * Find the patient with the given ID and return the full name as a
     * single string.
     */
    public String getNameByPatientID(String id) {

        String url = "/Patient/" + id;
        Patient patient2 = client.read()
                .resource(org.hl7.fhir.dstu3.model.Patient.class)
                .withUrl(url)
                .execute();

        return patient2.getNameFirstRep().getNameAsSingleString();//just so it will compile, return nothing
    }

    /**
     * Find all the patients that have the provided name and return a list
     * of the IDs for those patients.  The search should include matches
     * where any part of the patient name (family, given, prefix, etc.)
     * matches the method 'name' parameter.
     */
    public List<String> getIDByPatientName(String name) {

        String searchUrl = "/Patient?name=" + name + "&_count=100000000";
        Bundle results = client.search()
                .byUrl(searchUrl)
                .returnBundle(org.hl7.fhir.dstu3.model.Bundle.class)
                .execute();

        List<String> id_array = new ArrayList<String>();

        int i = 0;
        while (i < results.getEntry().size()){
            id_array.add(results.getEntry().get(i).getResource().getIdElement().getIdPart());
            i++;
        }

        return id_array;
    }

    public static void main(String[] args){

        String serverBase ="http://hapi.fhir.org/baseDstu3";
        SimpleRead reader = new SimpleRead(serverBase);

        System.out.println(reader.getNameByPatientID("17252"));
        System.out.println(reader.getIDByPatientName("Juan"));
    }
}