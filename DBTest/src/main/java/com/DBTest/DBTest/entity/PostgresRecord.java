package com.DBTest.DBTest.entity;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Entity
@Table(name = "daily_coal_stocks")
public class PostgresRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String stateName;
    private String stateCode;
    private String modeOfTransport;
    private String powerStationName;
    private String sector;
    private String utility;
    private Double capacityInMw;
    private Double reqForDayInThTonnes;
    private Double normativeStockReqDays;
    private Double normativeStockReqInThTonnes;
    private Double actualStockIndigenousInThTonnes;
    private Double actualStockImportInThTonnes;
    private Double actualStockTotalInThTonnes;
    private Double actualStockDays;
    private String percentageOfActualStockVsNormativeStock;
    private String receiptForDayInThTonnes;
    private String consumptionForDayInThTonnes;
    private String criticalOrSupCritical;
    private String reasonsForCriticalRemarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public String getPowerStationName() {
        return powerStationName;
    }

    public void setPowerStationName(String powerStationName) {
        this.powerStationName = powerStationName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUtility() {
        return utility;
    }

    public void setUtility(String utility) {
        this.utility = utility;
    }

    public Double getCapacityInMw() {
        return capacityInMw;
    }

    public void setCapacityInMw(Double capacityInMw) {
        this.capacityInMw = capacityInMw;
    }

    public Double getReqForDayInThTonnes() {
        return reqForDayInThTonnes;
    }

    public void setReqForDayInThTonnes(Double reqForDayInThTonnes) {
        this.reqForDayInThTonnes = reqForDayInThTonnes;
    }

    public Double getNormativeStockReqDays() {
        return normativeStockReqDays;
    }

    public void setNormativeStockReqDays(Double normativeStockReqDays) {
        this.normativeStockReqDays = normativeStockReqDays;
    }

    public Double getNormativeStockReqInThTonnes() {
        return normativeStockReqInThTonnes;
    }

    public void setNormativeStockReqInThTonnes(Double normativeStockReqInThTonnes) {
        this.normativeStockReqInThTonnes = normativeStockReqInThTonnes;
    }

    public Double getActualStockIndigenousInThTonnes() {
        return actualStockIndigenousInThTonnes;
    }

    public void setActualStockIndigenousInThTonnes(Double actualStockIndigenousInThTonnes) {
        this.actualStockIndigenousInThTonnes = actualStockIndigenousInThTonnes;
    }

    public Double getActualStockImportInThTonnes() {
        return actualStockImportInThTonnes;
    }

    public void setActualStockImportInThTonnes(Double actualStockImportInThTonnes) {
        this.actualStockImportInThTonnes = actualStockImportInThTonnes;
    }

    public Double getActualStockTotalInThTonnes() {
        return actualStockTotalInThTonnes;
    }

    public void setActualStockTotalInThTonnes(Double actualStockTotalInThTonnes) {
        this.actualStockTotalInThTonnes = actualStockTotalInThTonnes;
    }

    public Double getActualStockDays() {
        return actualStockDays;
    }

    public void setActualStockDays(Double actualStockDays) {
        this.actualStockDays = actualStockDays;
    }

    public String getPercentageOfActualStockVsNormativeStock() {
        return percentageOfActualStockVsNormativeStock;
    }

    public void setPercentageOfActualStockVsNormativeStock(String percentageOfActualStockVsNormativeStock) {
        this.percentageOfActualStockVsNormativeStock = percentageOfActualStockVsNormativeStock;
    }

    public String getReceiptForDayInThTonnes() {
        return receiptForDayInThTonnes;
    }

    public void setReceiptForDayInThTonnes(String receiptForDayInThTonnes) {
        this.receiptForDayInThTonnes = receiptForDayInThTonnes;
    }

    public String getConsumptionForDayInThTonnes() {
        return consumptionForDayInThTonnes;
    }

    public void setConsumptionForDayInThTonnes(String consumptionForDayInThTonnes) {
        this.consumptionForDayInThTonnes = consumptionForDayInThTonnes;
    }

    public String getCriticalOrSupCritical() {
        return criticalOrSupCritical;
    }

    public void setCriticalOrSupCritical(String criticalOrSupCritical) {
        this.criticalOrSupCritical = criticalOrSupCritical;
    }

    public String getReasonsForCriticalRemarks() {
        return reasonsForCriticalRemarks;
    }

    public void setReasonsForCriticalRemarks(String reasonsForCriticalRemarks) {
        this.reasonsForCriticalRemarks = reasonsForCriticalRemarks;
    }
}
