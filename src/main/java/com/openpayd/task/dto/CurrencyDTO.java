
package com.openpayd.task.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@Data
public class CurrencyDTO {

    @JsonProperty("base")
    private String base;
    
    @JsonProperty("rates")
    private Rates rates;
    
    @JsonProperty("date")
    private String date;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



}
