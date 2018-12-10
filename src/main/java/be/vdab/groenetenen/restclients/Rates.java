package be.vdab.groenetenen.restclients;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

class Rates {
	@JsonProperty("USD")
	private BigDecimal usd;

	public BigDecimal getUSD() {
		return usd;
	}

	public void setUSD(BigDecimal usd) {
		this.usd = usd;
	} 
	
}
