package com.DBTest.DBTest.entity;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Entity
@Table(name = "daily_coal_stocks")
@Document(collection = "daily_coal_stocks")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @Field("state_name")
    private String stateName;
    @Field("state_code")
    private String stateCode;
    @Field("mode_of_transport")
    private String modeOfTransport;
    @Field("power_station_name")
    private String powerStationName;
    private String sector;
    private String utility;
    @Field("capacity_in_mw")
    private Double capacityInMw;
    @Field("req_for_day_in_th_tonnes")
    private Double reqForDayInThTonnes;
    @Field("normative_stock_req_days")
    private Double normativeStockReqDays;
    @Field("normative_stock_req_in_th_tonnes")
    private Double normativeStockReqInThTonnes;
    @Field("actual_stock_indigenous_in_th_tonnes")
    private Double actualStockIndigenousInThTonnes;
    @Field("actual_stock_import_in_th_tonnes")
    private Double actualStockImportInThTonnes;
    @Field("actual_stock_total_in_th_tonnes")
    private Double actualStockTotalInThTonnes;
    @Field("actual_stock_days")
    private Double actualStockDays;
    @Field("percentage_of_actual_stock_vs_normative_stock")
    private String percentageOfActualStockVsNormativeStock;
    @Field("receipt_for_day_in_th_tonnes")
    private String receiptForDayInThTonnes;
    @Field("consumption_for_day_in_th_tonnes")
    private String consumptionForDayInThTonnes;
    @Field("critical_or_sup_critical")
    private String criticalOrSupCritical;
    @Field("reasons_for_critical_remarks")
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
