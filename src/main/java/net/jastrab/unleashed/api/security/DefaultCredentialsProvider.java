package net.jastrab.unleashed.api.security;

public class DefaultCredentialsProvider implements UnleashedCredentialsProvider {
    private final ApiCredential credential;

    private DefaultCredentialsProvider(ApiCredential credential) {
        this.credential = credential;
    }

    public DefaultCredentialsProvider(final String apiId, final String apiKey) {
        this.credential = new ApiCredentialImpl(apiId, apiKey);
    }

    @Override
    public ApiCredential get() {
        return credential;
    }

    public static UnleashedCredentialsProvider ofCredential(ApiCredential credential) {
        return new DefaultCredentialsProvider(credential);
    }
}
