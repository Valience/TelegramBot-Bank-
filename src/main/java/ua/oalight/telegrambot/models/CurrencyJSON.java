package ua.oalight.telegrambot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyJSON {
    //cc
    @JsonProperty("cc")
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String curency) {
        this.currency = curency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @JsonProperty("rate")
    private double rate;
}
