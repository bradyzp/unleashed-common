package net.jastrab.unleashed.api.http;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

/**
 * Describes a standard read-only Unleashed Resource
 */
public interface UnleashedResource {
    UUID getGuid();

    @JsonIgnore
    String getBasePath();

}
