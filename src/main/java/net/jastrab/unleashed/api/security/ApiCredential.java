package net.jastrab.unleashed.api.security;

import javax.crypto.spec.SecretKeySpec;

public interface ApiCredential {
    String getId();

    SecretKeySpec getKey();
}
