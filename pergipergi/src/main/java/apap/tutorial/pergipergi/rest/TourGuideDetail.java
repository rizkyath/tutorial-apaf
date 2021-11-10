package apap.tutorial.pergipergi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TourGuideDetail {
    String status;

    @JsonProperty("guide-license")
    private Integer guideLicense;

    @JsonProperty("valid-until")
    private Date validUntil;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGuideLicense() {
        return guideLicense;
    }

    public void setGuideLicense(Integer guideLicense) {
        this.guideLicense = guideLicense;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
