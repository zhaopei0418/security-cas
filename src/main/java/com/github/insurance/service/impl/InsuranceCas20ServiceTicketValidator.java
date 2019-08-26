package com.github.insurance.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;

public class InsuranceCas20ServiceTicketValidator extends Cas20ServiceTicketValidator {

    private static Log logger = LogFactory.getLog(InsuranceCas20ServiceTicketValidator.class);
    /**
     * Constructs an instance of the CAS 2.0 Service Ticket Validator with the supplied
     * CAS server url prefix.
     *
     * @param casServerUrlPrefix the CAS Server URL prefix.
     */
    public InsuranceCas20ServiceTicketValidator(String casServerUrlPrefix) {
        super(casServerUrlPrefix);
    }

    @Override
    protected Assertion parseResponseFromServer(String response) throws TicketValidationException {
        logger.info("cas response: [" + response + "]");
        return super.parseResponseFromServer(response);
    }
}
