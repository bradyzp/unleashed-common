package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface MutableResource extends CreatableResource {
    @JsonIgnore
    HttpMethod getUpdateMethod();
}
