package examples;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;

/**
 * This class contains methods for removing resoruces from the FHIR server.
 */
public class AdvancedDelete {

    private IGenericClient client = null;

    public AdvancedDelete(String baseUrl) {
        FhirContext ctx = FhirContext.forDstu3();
        client = ctx.newRestfulGenericClient(baseUrl);
    }

    /**
     * Delete the patient with the given ID from the FHIR server.
     */
    public void deletePatient(String patientId) {
        //Place your code here
        IBaseOperationOutcome resp = client
                .delete()
                .resourceById(new IdDt("Patient", patientId))
                .execute();
    }

    /**
     * Delete the observation with the given ID from the FHIR server.
     */
    public void deleteObservation(String observationId) {
        //Place your code here
        IBaseOperationOutcome resp = client
                .delete()
                .resourceById(new IdDt("Observation", observationId))
                .execute();
    }

    public static void main(String[] args){
        String serverBase ="http://hapi.fhir.org/baseDstu3";

        AdvancedDelete deleter = new AdvancedDelete(serverBase);

        String patientID = "50780"; //set this to a valid id
        deleter.deletePatient(patientID);

        String obsID = "48661"; //set this to a valid id
        deleter.deleteObservation(obsID);
    }

}
