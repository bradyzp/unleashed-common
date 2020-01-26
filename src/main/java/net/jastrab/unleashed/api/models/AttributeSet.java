package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jastrab.unleashed.api.http.CreatableResource;
import net.jastrab.unleashed.api.http.SimpleResource;

import java.util.*;

public class AttributeSet implements CreatableResource, SimpleResource {
    private final ResourceOrigin origin;
    private final UUID guid;
    private final String name;
    private final List<Attribute> attributes;

    public AttributeSet(final String name) {
        this.origin = ResourceOrigin.LOCAL;
        this.guid = UUID.randomUUID();
        this.name = name;
        this.attributes = new ArrayList<>();
    }

    @JsonCreator
    private AttributeSet(@JsonProperty("Guid") UUID guid,
                         @JsonProperty("SetName") String name,
                         @JsonProperty("Attributes") List<Attribute> attributes) {
        this.origin = ResourceOrigin.REMOTE;
        this.guid = guid;
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public UUID getGuid() {
        return guid;
    }

    @Override
    public String getBasePath() {
        return "/AttributeSets/";
    }

    @Override
    public ResourceOrigin getOrigin() {
        return origin;
    }

    public String getName() {
        return name;
    }

    /**
     * Get the Attribute Set's Type
     *
     * @return the type of the attribute set
     * @apiNote as of Unleashed API version 2019-Jan-16 the only valid value for type is 'Product'
     */
    public String getType() {
        return "Product";
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    @Override
    public String toString() {
        return "AttributeSet{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeSet that = (AttributeSet) o;
        return origin == that.origin &&
                guid.equals(that.guid) &&
                name.equals(that.name) &&
                attributes.equals(that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, guid, name, attributes);
    }
}
