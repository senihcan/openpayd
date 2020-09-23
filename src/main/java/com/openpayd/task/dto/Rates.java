
package com.openpayd.task.dto;

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
public class Rates {

    @JsonProperty("GBP")
    private Double gbp;
       
    @JsonProperty("EUR")
    private Double eur; 

    @JsonProperty("TRY")
    private Double tl;
    		
    @JsonProperty("JPY")
    private Double jpy;
    		
    @JsonProperty("RUB")
    private Double rub;
    		
    @JsonProperty("USD")
    private Double usd;
    		

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
