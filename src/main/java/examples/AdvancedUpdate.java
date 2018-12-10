package examples;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Quantity;

/**
 * This class contains methods for updating resources in the FHIR server.
 */
public class AdvancedUpdate {

    private IGenericClient client = null;

    public AdvancedUpdate(String baseUrl) {
        FhirContext ctx = FhirContext.forDstu3();
        client = ctx.newRestfulGenericClient(baseUrl);
    }

    /**
     * Find the patient with the given ID and update the home phone number.  If the
     * patient does not already have a home phone number, add it.  Return the ID
     * of the updated resource.
     */
    public String updatePatientHomePhone(String patientId, String homePhoneNumber) {
        String url2 = "/Patient/" + patientId;
        Patient patient2 = client.read()
                .resource(Patient.class)
                .withUrl(url2)
                .execute();
        ContactPoint contact = patient2.addTelecom();
        contact.setSystem(ContactPoint.ContactPointSystem.PHONE);
        contact.setUse(ContactPointUse.HOME);
        contact.setValue(homePhoneNumber);

        MethodOutcome outcome = client.update()
                .resource(patient2)
                .execute();

        System.out.println("Got ID: " + outcome.getId());

        return outcome.getId().getIdPart();
    }

    /**
     * Find the observation with the given ID and update the value.  Recall that
     * observations have a unit of measure value code, units, and a value - this
     * method only updates the value.  Return the ID of the updated resource.
     */
    public String updateObservationValue(String observationId, double value) {
        //Place your code here
        String url2 = "/Observation/" + observationId;
        Observation observation2 = client.read()
                .resource(Observation.class)
                .withUrl(url2)
                .execute();

        observation2.setValue(new Quantity().setValue(value));


        MethodOutcome outcome = client.update()
                .resource(observation2)
                .execute();

        System.out.println("Got ID: " + outcome.getId());

        return outcome.getId().getIdPart();
    }

    public static void main(String[] args){
        String serverBase ="http://hapi.fhir.org/baseDstu3";

        AdvancedUpdate updater = new AdvancedUpdate(serverBase);

        String homePhone = "856-555-1212";
        String patientID = "49276";//find a good patient to work with
        System.out.println(updater.updatePatientHomePhone(patientID, homePhone));

        String obsID = "48661";//find a good observation to update
        double value = 121.23;
        updater.updateObservationValue(obsID, value);
    }

}
