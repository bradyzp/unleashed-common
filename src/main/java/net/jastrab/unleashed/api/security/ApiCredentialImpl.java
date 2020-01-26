package net.jastrab.unleashed.api.security;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ApiCredentialImpl implements ApiCredential {
    private String id;
    private SecretKeySpec key;

    public ApiCredentialImpl(String keyId, String key) {
        Objects.requireNonNull(keyId, "API Key ID must not be null");
        Objects.requireNonNull(key, "API Key must not be null");
        this.id = keyId;
        this.key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public SecretKeySpec getKey() {
        return key;
    }
}
