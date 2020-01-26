package net.jastrab.unleashed.api.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
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
public final class ApiSignatureGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ApiSignatureGenerator.class);
    private static final String algorithmName = "HmacSHA256";

    private ApiSignatureGenerator() {

    }

    public static String getSignature(Key key, String queryString) {
        byte[] signature = getInstance(key).doFinal(queryString.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(signature);  // TODO: This is only available in Java 11
    }

    private static Mac getInstance(Key key) {
        try {
            final Mac mac = Mac.getInstance(algorithmName);
            mac.init(key);
            return mac;
        } catch (NoSuchAlgorithmException e) {
            logger.error("Unexpected Error, unable to instantiate HmacSHA256 Mac instance", e);
            throw new RuntimeException(algorithmName + " is not available on this platform");
        } catch (InvalidKeyException e) {
            logger.error("Invalid signing key provided");
            throw new RuntimeException("Invalid signing key provided");
        }
    }

}
