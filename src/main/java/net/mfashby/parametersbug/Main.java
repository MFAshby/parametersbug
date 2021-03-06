package net.mfashby.parametersbug;

import static ca.uhn.fhir.context.FhirContext.forDstu3;

import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Parameters;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.SchemaBaseValidator;
import ca.uhn.fhir.validation.schematron.SchematronBaseValidator;

public class Main {
    public static void main(String[] args) {
        FhirContext fhirContext = forDstu3();
        FhirValidator fhirValidator = fhirContext.newValidator();
        fhirValidator.registerValidatorModule(new SchemaBaseValidator(fhirContext));
        fhirValidator.registerValidatorModule(new SchematronBaseValidator(fhirContext));


        // Throws exception due to missing schematron
        try {
            Parameters parameters = new Parameters();
            System.out.println(fhirValidator.validateWithResult(parameters));
        } catch (Throwable t) {
            t.printStackTrace();
        }


        // Validates and returns validation errors
        try {
            Observation observation = new Observation();
            System.out.println(fhirValidator.validateWithResult(observation));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
