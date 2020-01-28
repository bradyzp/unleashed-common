package net.jastrab.unleashed.api.http;


import net.jastrab.unleashed.api.models.UnleashedResponse;
import net.jastrab.unleashed.api.util.TypeConstructor;

/**
 * Base abstract class for a request which is expected to return a paginated response
 * i.e. a response which is wrapped in a {@link UnleashedResponse}
 *
 * @param <T>
 */
public abstract class PaginatedUnleashedRequest<T> extends BaseUnleashedRequest<T> {
    protected PaginatedUnleashedRequest(Class<T> responseType) {
        super(TypeConstructor.constructUnleashedResponse(responseType));
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public PaginatedUnleashedRequest<T> forPage(int page) {
        throw new UnsupportedOperationException("Pagination is not supported for this request object");
    }

}
