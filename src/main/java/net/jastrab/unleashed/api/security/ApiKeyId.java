package net.jastrab.unleashed.api.security;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class ApiKeyId implements ApiCredential {
    private String id;
    private SecretKeySpec key;

    public ApiKeyId(String keyId, String privateKey) {
        this.id = keyId;
        this.key = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
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
