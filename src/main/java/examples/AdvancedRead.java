package examples;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.Bundle;

import java.util.ArrayList;
import java.util.List;

//import ca.uhn.fhir.parser.IParser;


/**
 * This class contains methods for reading resoruces from the FHIR server.
 */
public class AdvancedRead {

    private IGenericClient client = null;

    public AdvancedRead(String baseUrl) {
        FhirContext ctx = FhirContext.forDstu3();
        client = ctx.newRestfulGenericClient(baseUrl);
    }

    /**
     * Find all observations with the givin loinc code and return the total
     * number of patients referenced.  Note that patients may have multiple 
     * observations, so the number of observations >= number of patients.
     */
    public int getTotalNumPatientsByObservation(String loincCode) {
        String searchUrl = "/Observation?_include=*&code=http://loinc.org|" + loincCode + "&_count=100000000";
        Bundle results = client.search()
                .byUrl(searchUrl)
                .returnBundle(Bundle.class)
                .execute();

        int i = 0;
        List<String> patient_array = new ArrayList<String>();

        while (i < results.getEntry().size()){
            if (results.getEntry().get(i).getResource().fhirType() == "Patient"){
                if(!(patient_array.contains(results.getEntry().get(i).getResource().getId()))){
                    patient_array.add(results.getEntry().get(i).getResource().getId());
                }
            }
            i++;
        }

        return patient_array.size();
    }

    public static void main(String[] args){
        String serverBase ="http://hapi.fhir.org/baseDstu3";

        AdvancedRead getter = new AdvancedRead(serverBase);
        String loincCode = "2339-0";//pick what you like here
        int actual_count = getter.getTotalNumPatientsByObservation(loincCode);
        System.out.println(actual_count);

    }

}
