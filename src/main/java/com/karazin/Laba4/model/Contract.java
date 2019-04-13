package com.karazin.Laba4.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Contract {
    private int id;
    private int idFirm;
    private String numbered;
    private String named;
    private double sumd;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataStart;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataFinish;
    private double avans;

    public Contract(int id, int idFirm, String numbered, String named,
            double sumd, LocalDate dataStart, LocalDate dataFinish,
            double avans) {
        this.id = id;
        this.idFirm = idFirm;
        this.numbered = numbered;
        this.named = named;
        this.sumd = sumd;
        this.dataStart = dataStart;
        this.dataFinish = dataFinish;
        this.avans = avans;
    }

    public Contract() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFirm() {
        return idFirm;
    }

    public void setIdFirm(int idFirm) {
        this.idFirm = idFirm;
    }

    public String getNumbered() {
        return numbered;
    }

    public void setNumbered(String numbered) {
        this.numbered = numbered;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public Double getSumd() {
        return sumd;
    }

    public void setSumd(double sumd) {
        this.sumd = sumd;
    }

    public LocalDate getDataStart() {
        return dataStart;
    }

    public void setDataStart(LocalDate dataStart) {
        this.dataStart = dataStart;
    }

    public LocalDate getDataFinish() {
        return dataFinish;
    }

    public void setDataFinish(LocalDate dataFinish) {
        this.dataFinish = dataFinish;
    }

    public double getAvans() {
        return avans;
    }

    public void setAvans(double avans) {
        this.avans = avans;
    }

    @Override
    public String toString() {
        return "Contract [id=" + id + ", idFirm=" + idFirm + ", numbered="
                + numbered + ", named=" + named + ", sumd=" + sumd
                + ", dataStart=" + dataStart + ", dataFinish=" + dataFinish
                + ", avans=" + avans + "]";
    }

}
