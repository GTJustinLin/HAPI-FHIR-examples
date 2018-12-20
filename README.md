# HAPI-FHIR-examples

Examples of the Java FHIR API, [HAPI FHIR](http://hapifhir.io/), developed for a class.  [FHIR](https://www.hl7.org/fhir/summary.html) is a database standard required by U.S. Hospitals to use per a HL7 directive.

### To run the examples:
1. Download .zip of this project and unzip it
1. Import the folder into your favorite IDE as a Maven project
   1. If you are using IntelliJ, simply go to `File`>`New`>`Project from existing sources`, then add the project with the default options
1. Run each file under the `examples` package to test them
1. View results of these examples on this public FHIR server:
   1. http://hapi.fhir.org/baseDstu3/
   
### Additional FHIR resources:
1. [Introduction - HAPI FHIR](http://hapifhir.io/doc_intro.html)
1. [RESTful Client - HAPI FHIR](http://hapifhir.io/doc_rest_client.html)
1. [FHIR APIs | FHIR® tutorials](https://fhir-drills.github.io/fhir-api.html)
1. [Java Code Examples ca.uhn.fhir.rest.client.api.IGenericClient](https://www.programcreek.com/java-api-examples/?api=ca.uhn.fhir.rest.client.api.IGenericClient)
1. [Programcreek examples](https://www.programcreek.com/java-api-examples/?api=org.hl7.fhir.dstu3.model.ContactPoint)
1. [FHIR search url](http://hapi.fhir.org/baseDstu3/Patient?family=duck)
1. [medication lookup](http://hapi.fhir.org/baseDstu3/MedicationRequest/629898)
1. [easy HAPI FHIR](http://hapi.fhir.org/search?serverId=home_21&pretty=true&resource=Observation&param.0.qualifier=&param.0.0=&param.0.name=_language&param.0.type=string&_include=*&sort_by=&sort_direction=&resource-search-limit=)
1. [FHIR methods search](https://www.google.com/search?ei=XgKwW6GjLKOt_QazhaiADw&q=site%3Ahttp%3A%2F%2Fhapifhir.io%2Fapidocs-dstu3%2Findex.html+identifier&oq=site%3Ahttp%3A%2F%2Fhapifhir.io%2Fapidocs-dstu3%2Findex.html+identifier&gs_l=psy-ab.3...2889.49596354..49596498...0.0..0.82.663.12......0....1..gws-wiz.iiEboETGcgo)

### Additional health data resources:
1. [Pillbox data](https://datadiscovery.nlm.nih.gov/Drugs-and-Supplements/Pillbox/crzr-uvwg/data)
1. [Pillbox drug pictures, etc](https://pillbox.nlm.nih.gov/developers.html)
1. [UMLS for similar medication terms, helpful for parsing medical text](https://www.nlm.nih.gov/research/umls/)
1. [ICD-10 Version:2015](https://icd.who.int/browse10/2015/en)
1. [ICD-11 - Mortality and Morbidity Statistics](https://icd.who.int/dev11/l-m/en)
1. [Valueset-icd-10 - FHIR v3.0.1](http://hl7.org/fhir/valueset-icd-10.html)
1. [RVU Free Search | CPT Code Search](https://login.ama-assn.org/account/login)
1. [2.07: Intro to CPT Coding](https://www.medicalbillingandcoding.org/intro-to-cpt/)
1. [Search LOINC](https://search.loinc.org/searchLOINC/search.zul)
1. [UCUM](http://unitsofmeasure.org/trac)
1. [RELMA — LOINC](https://loinc.org/relma/)
1. [Drugs@FDA: FDA Approved Drug Products](https://www.accessdata.fda.gov/scripts/cder/daf/index.cfm)
1. [National Drug Code Directory](https://www.accessdata.fda.gov/scripts/cder/ndc/index.cfm)
1. [Drug Approvals and Databases](https://www.fda.gov/Drugs/InformationOnDrugs/default.htm)
1. [RxNorm API](https://mor.nlm.nih.gov/download/rxnav/RxNormAPIs.html#)
1. [RxNav](https://mor.nlm.nih.gov/RxNav/search?searchBy=RXCUI&searchTerm=1652064)
1. [SNOMED CT - Home](https://browser.ihtsdotools.org/?)
1. [ndclist.com](https://ndclist.com/)
1. [SMART App Gallery](https://apps.smarthealthit.org/apps/)
1. [i2b2 Web Client](https://www.i2b2.org/webclient/)
1. [The SMART Cell - SMART - i2b2 Community Wiki](http://community.i2b2.org/wiki/display/SMArt/The+SMART+Cell)
