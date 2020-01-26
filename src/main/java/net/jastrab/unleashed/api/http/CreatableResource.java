package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CreatableResource extends UnleashedResource {
    enum ResourceOrigin {
        LOCAL, REMOTE;
    }

    @JsonIgnore
    ResourceOrigin getOrigin();

    @JsonIgnore
    default HttpMethod getCreateMethod() {
        return HttpMethod.POST;
    }
}
