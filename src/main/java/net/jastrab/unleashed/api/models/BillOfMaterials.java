package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.HttpMethod;
import net.jastrab.unleashed.api.http.MutableResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BillOfMaterials implements MutableResource {
    private static final String BASE_PATH = "/BillOfMaterials/";
    private final UUID guid;
    private final ResourceOrigin origin;

    private List<BillOfMaterialsLine> billOfMaterialsLines = new ArrayList<>();
    private Product product;
    private BigDecimal totalCost;

    private String createdBy;
    private String lastModifiedBy;
    private LocalDateTime createdOn;
    private LocalDateTime lastModifiedOn;

    private String billNumber;
    private boolean canAutoAssemble;
    private boolean canAutoDisassemble;
    private boolean sortByProductCode;
    private boolean obsolete;

    @JsonCreator
    private BillOfMaterials(@JsonProperty("Guid") UUID guid) {
        this.origin = ResourceOrigin.REMOTE;
        this.guid = guid;
    }

    @Override
    public UUID getGuid() {
        return guid;
    }

    @Override
    public String getBasePath() {
        return BASE_PATH;
    }

    @Override
    public HttpMethod getUpdateMethod() {
        return HttpMethod.PUT;
    }

    @Override
    public ResourceOrigin getOrigin() {
        return origin;
    }
}
