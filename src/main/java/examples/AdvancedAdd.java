package examples;

//import ca.uhn.fhir.model.primitive.IdDt;
//import org.hl7.fhir.dstu3.model.Patient;
//import org.hl7.fhir.dstu3.model.Observation;
//import org.hl7.fhir.dstu3.model.Observation.ObservationStatus;
//import org.hl7.fhir.dstu3.model.Quantity;
//import org.hl7.fhir.dstu3.model.Reference;
//import ca.uhn.fhir.rest.api.MethodOutcome;
//import ca.uhn.fhir.rest.client.api.IGenericClient;
//import ca.uhn.fhir.context.FhirContext;
//import org.hl7.fhir.dstu3.model.Bundle;
//import org.hl7.fhir.dstu3.model.Bundle.BundleType;
//import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.*;


/**
 * This class contains methods for adding resoruces to the FHIR server.
 */
public class AdvancedAdd {

    private IGenericClient client = null;

    public AdvancedAdd(String baseUrl) {
        FhirContext ctx = FhirContext.forDstu3();
        client = ctx.newRestfulGenericClient(baseUrl);
    }

    /**
     * Add a new patient to the FHIR server with the given first and last name.
     * Return the ID of the newly created patient.
     */

    public String addPatient(String firstName, String lastName) {
        Patient pat = new Patient();
        HumanName name = pat.addName();
        name.setFamily(lastName).addGiven(firstName);

        MethodOutcome outcome = client.create()
                .resource(pat)
                .execute();

        return outcome.getId().getIdPart();
    }

    /**
     * Add a new observation to the FHIR server with a reference to the specified patient ID.
     * Assume the patient already exists in the FHIR server.
     * The observation will have a loinc code and display name, a unit of measure value code,
     * units, and value for the observation.
     * Return the ID of the newly created observation.
     */
    public String addObservation(String patientId, String loincCode, String loincDisplayName,
                                double value, String valueUnit, String valueCode) {
        Observation observation = new Observation();
        observation
                .getCode()
                .addCoding()
                .setSystem("http://loinc.org")
                .setCode(loincCode)
                .setDisplay(loincDisplayName);
        observation.setValue(
                new Quantity()
                        .setValue(value)
                        .setUnit(valueUnit)
                        .setCode(valueCode));

        String url2 = "/Patient/" + patientId;
        Patient patient2 = client.read()
                .resource(Patient.class)
                .withUrl(url2)
                .execute();

        observation.setSubject(new Reference(patient2.getId()));

        MethodOutcome outcome = client.create()
                .resource(observation)
                .prettyPrint()
                .encodedJson()
                .execute();

        return outcome.getId().getIdPart();
    }

    public static void main(String[] args){
        String serverBase ="http://hapi.fhir.org/baseDstu3";

        AdvancedAdd adder = new AdvancedAdd(serverBase);

        String pid1 = adder.addPatient("Aaafirst", "Aaalast");
        System.out.println(pid1);

        String pid2 = "48307";
        String loincCode = "2339-0";
        String loincDisplayName = "Glucose";
        double value = 118;
        String valueUnit = "mg/dL";
        String valueCode = "mg/dL";
        String oid = adder.addObservation(pid2, loincCode, loincDisplayName, value, valueUnit, valueCode);
        System.out.println(oid);

    }

}
