package net.jastrab.unleashed.api.http;

import net.jastrab.unleashed.api.security.ApiCredential;
import net.jastrab.unleashed.api.security.SignatureGenerator;

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
    private final Type responseType;
    private String apiId;
    private String authSignature;
    private String apiAuthority = AUTHORITY;
    private String apiScheme = SCHEME;

    protected BaseUnleashedRequest(ParameterizedType parameterizedType) {
        this.responseType = parameterizedType;
    }

    protected BaseUnleashedRequest(Class<T> responseType) {
        this.responseType = responseType;
    }

    @Override
    public void sign(ApiCredential credential) {
        this.apiId = credential.getId();
        this.authSignature = SignatureGenerator.getSignature(credential.getKey(), getQuery());
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

    @Override
    public String getAuthority() {
        return this.apiAuthority;
    }

    @Override
    public void setAuthority(String authority) {
        this.apiAuthority = authority;
    }

    @Override
    public String getScheme() {
        return this.apiScheme;
    }

    @Override
    public void setScheme(String scheme) {
        if(!"http".equals(scheme) && !"https".equals(scheme)) {
            throw new IllegalArgumentException("Invalid scheme supplied, must be 'http' or 'https'");
        }
        this.apiScheme = scheme;
    }
}

