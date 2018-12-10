package be.vdab.groenetenen.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import be.vdab.groenetenen.constraints.Postcode;
import be.vdab.groenetenen.constraints.VanTotPostcodeFormVanKleinerDanOfGelijkAanTot;

@VanTotPostcodeFormVanKleinerDanOfGelijkAanTot
public class VanTotPostcodeForm {
	@NotNull
	@Range(min = 1000, max = 9999)
	private Integer van;
	@NotNull
	//@Range(min = 1000, max = 9999)
	@Postcode
	private Integer tot;

	public Integer getVan() {
		return van;
	}
	public Integer getTot() {
		return tot;
	}
	public void setVan(Integer van) {
		this.van = van;
	}
	public void setTot(Integer tot) {
		this.tot = tot;
	}
	
}
