package net.jastrab.unleashed.api;

import net.jastrab.unleashed.api.http.BaseUnleashedRequest;
import net.jastrab.unleashed.api.http.HttpMethod;
import net.jastrab.unleashed.api.http.PaginatedUnleashedRequest;
import net.jastrab.unleashed.api.http.SimpleResource;
import net.jastrab.unleashed.api.models.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleGetRequest<T extends SimpleResource> extends PaginatedUnleashedRequest<T> {
    private final static Map<Class<? extends SimpleResource>, String> pathMap = new HashMap<>();

    static {
        pathMap.put(AttributeSet.class, "/AttributeSets");
        pathMap.put(CustomerType.class, "/CustomerTypes");
        pathMap.put(PaymentTerm.class, "/PaymentTerms");
        pathMap.put(ProductGroup.class, "/ProductGroups");
        pathMap.put(SellPriceTier.class, "/SellPriceTiers");
        pathMap.put(UnitOfMeasure.class, "/UnitOfMeasures");
    }

    private final String path;
    private UUID guid;

    public SimpleGetRequest(Class<T> itemType) {
        super(itemType);
        if (!pathMap.containsKey(itemType)) {
            throw new IllegalStateException("No Path entry for item class: " + itemType.getName());
        }
        path = pathMap.get(itemType);
    }

    public SimpleGetRequest<T> guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getPath() {
        if(guid != null)
            return path + "/" + guid.toString();
        else
            return path;
    }
}
