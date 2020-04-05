package net.jastrab.unleashed.api.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The base abstract class for a request which is expected to return an object of the same type & shape as the request
 * <p>
 * This should be the base for any create item requests, as the API returns an unwrapped object representing the
 * newly created item, as opposed to get requests which typically return a list of items wrapped in an UnleashedResponse
 * object, which contains pagination information
 *
 * @param <T>
 */
public abstract class BaseUnleashedRequest<T> implements UnleashedRequest<T> {
    private final Type responseType;

    protected BaseUnleashedRequest(ParameterizedType parameterizedType) {
        this.responseType = parameterizedType;
    }

    protected BaseUnleashedRequest(Class<T> responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type getResponseType() {
        return responseType;
    }

}

