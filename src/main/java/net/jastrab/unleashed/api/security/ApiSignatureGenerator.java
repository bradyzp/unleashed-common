package net.jastrab.unleashed.api.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Utility Class used to calculate an authentication signature for an http request to the Unleashed API
 * <p>
 * The signature is generated using the HMAC-SHA-256 algorithm, using the users API key, and the query string of the
 * request to be sent.
 * When a request does not utilize a query string, an empty String "" should be supplied.
 *
 * @apiNote The query string should not include the '?' prefix
 * @see <a href="https://apidocs.unleashedsoftware.com/AuthenticationHelp">Unleashed API - Authentication</a>
 */
public class ApiSignatureGenerator {
    private Logger logger = LoggerFactory.getLogger(ApiSignatureGenerator.class);
    private static final String algorithmName = "HmacSHA256";
    private Mac mac;

    {
        try {
            mac = Mac.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            // This should never happen, as HmacSHA256 /should/ be supported on all Java VMs
            logger.error("Unexpected Error, unable to instantiate HmacSHA256 Mac instance", e);
            throw new RuntimeException(algorithmName + " is not available on this platform/system");
        }
    }

    public ApiSignatureGenerator(ApiCredential credential) throws InvalidKeyException {
        mac.init(credential.getKey());
    }

    public String getSignature(String queryString) {
        byte[] signature = mac.doFinal(queryString.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(signature);
    }

}
