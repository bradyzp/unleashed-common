package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface DeletableResource extends UnleashedResource {
    @JsonIgnore
    default String getDeleteEndpoint() {
        return getBasePath() + getGuid();
    }
}
