package com.mandisoft.services.member.vo;

import java.util.Date;
import java.util.Objects;

public class BuyEntryVO {

	private Date entryDate;
	private String sellerName;
	private String town;
	private int grainTypeId;
	private float weight;
	private float ratePerKg;
	private float labourCharges;
	private float totalAmount;
	private String transactionReference;

	public BuyEntryVO() {
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public int getGrainTypeId() {
		return grainTypeId;
	}

	public void setGrainTypeId(int grainTypeId) {
		this.grainTypeId = grainTypeId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getRatePerKg() {
		return ratePerKg;
	}

	public void setRatePerKg(float ratePerKg) {
		this.ratePerKg = ratePerKg;
	}

	public float getLabourCharges() {
		return labourCharges;
	}

	public void setLabourCharges(float labourCharges) {
		this.labourCharges = labourCharges;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BuyEntryVO that = (BuyEntryVO) o;
		return Objects.equals(entryDate, that.entryDate) &&
				Objects.equals(sellerName, that.sellerName) &&
				Objects.equals(town, that.town) &&
				Objects.equals(weight, that.weight) &&
				Objects.equals(ratePerKg, that.ratePerKg) &&
				Objects.equals(labourCharges, that.labourCharges) &&
				Objects.equals(totalAmount, that.totalAmount) &&
				Objects.equals(transactionReference, that.transactionReference);
	}

	@Override
	public String toString() {
		return "BuyEntryVO{" +
				"entryDate='" + entryDate + '\'' +
				", sellerName='" + sellerName + '\'' +
				", town='" + town + '\'' +
				", weight='" + weight + '\'' +
				", ratePerKg='" + ratePerKg + '\'' +
				", labourCharges='" + labourCharges + '\'' +
				", totalAmount='" + totalAmount + '\'' +
				", transactionReference='" + transactionReference + '\'' +
				'}';
	}
}
