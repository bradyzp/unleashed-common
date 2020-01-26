package net.jastrab.unleashed.api.http;

import net.jastrab.unleashed.api.util.TypeConstructor;
import net.jastrab.unleashed.api.security.ApiCredential;
import net.jastrab.unleashed.api.security.ApiSignatureGenerator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The base abstract class for a request which is expected to return an object of the same type & shape as the request
 *
 * This should be the base for any create item requests, as the API returns an unwrapped object representing the
 * newly created item, as opposed to get requests which typically return a list of items wrapped in an UnleashedResponse
 * object, which contains pagination information
 * @param <T>
 */
public abstract class BaseUnleashedRequest<T> implements UnleashedRequest<T> {
    private String apiId;
    private String authSignature;
    private final Type responseType;

    protected BaseUnleashedRequest(ParameterizedType parameterizedType) {
        this.responseType = parameterizedType;
    }

    protected BaseUnleashedRequest(Class<T> responseType) {
        this.responseType = responseType;
    }

    @Override
    public void sign(ApiCredential credential) {
        this.apiId = credential.getId();
        this.authSignature = ApiSignatureGenerator.getSignature(credential.getKey(), getQuery());
    }

    @Override
    public String getAuthSignature() {
        return authSignature;
    }

    @Override
    public String getApiId() {
        return apiId;
    }

    @Override
    public Type getResponseType() {
        return responseType;
    }
}
