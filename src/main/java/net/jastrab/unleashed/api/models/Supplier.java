package net.jastrab.unleashed.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Supplier {
    private final String guid;
    private final String supplierCode;
    private String bankAccount;
    private String bankBranch;
    private String bankName;
    private String DDINumber;
    private String email;
    private Currency currency;
    private String faxNumber;
    private String GSTVATNumber;
    private String createdBy;
    private LocalDateTime createdOn;
    private String lastModifiedBy;
    private String mobileNumber;
    private String notes;
    private String phoneNumber;
    private String supplierName;
    private Boolean taxable;
    private String tollFreeNumber;
    private String website;
    private String xeroContactId;

    public Supplier(@JsonProperty("Guid") String guid,
                    @JsonProperty("SupplierCode") String supplierCode) {
        this.guid = guid;
        this.supplierCode = supplierCode;
    }

    public String getGuid() {
        return guid;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDDINumber() {
        return DDINumber;
    }

    public void setDDINumber(String DDINumber) {
        this.DDINumber = DDINumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getGSTVATNumber() {
        return GSTVATNumber;
    }

    public void setGSTVATNumber(String GSTVATNumber) {
        this.GSTVATNumber = GSTVATNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getTaxable() {
        return taxable;
    }

    public void setTaxable(Boolean taxable) {
        this.taxable = taxable;
    }

    public String getTollFreeNumber() {
        return tollFreeNumber;
    }

    public void setTollFreeNumber(String tollFreeNumber) {
        this.tollFreeNumber = tollFreeNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getXeroContactId() {
        return xeroContactId;
    }

    public void setXeroContactId(String xeroContactId) {
        this.xeroContactId = xeroContactId;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "guid='" + guid + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
