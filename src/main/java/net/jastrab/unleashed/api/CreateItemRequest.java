package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.BaseUnleashedRequest;
import net.jastrab.unleashed.api.http.CreatableResource;
import net.jastrab.unleashed.api.http.HttpMethod;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateItemRequest<T extends CreatableResource> extends BaseUnleashedRequest<T> {
    private static final Pattern ENDPOINT_PATTERN = Pattern.compile("/?(\\w(/?\\w)*)/?");

    private final T item;

    public CreateItemRequest(T item, Class<T> itemType) {
        super(itemType);
        this.item = item;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return item.getCreateMethod();
    }

    @Override
    public String getPath() {
        return joinEndpointPath(item.getBasePath(), item.getGuid());
    }

    @Override
    public T getRequestBody() {
        return item;
    }

    static String joinEndpointPath(String endpoint, UUID guid) {
        Matcher matcher = ENDPOINT_PATTERN.matcher(endpoint);
        if(matcher.matches()) {
            String name = matcher.group(1);
            return "/" + name + "/" + guid.toString();
        } else {
            throw new IllegalStateException("Invalid endpoint string provided: " + endpoint);
        }
    }

}
