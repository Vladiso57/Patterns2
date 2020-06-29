package com.Labs.Patterns.dto;

public class Printer {

    private int idPrinter;
    private String manufacturer;
    private String printingTechnology;
    private int speedPrint;
    private int price;
    private String model;

    public Printer(String manufacturer, String printingTechnology, int speedPrint, int price,String model,int idPrinter) {
        this.manufacturer = manufacturer;
        this.printingTechnology = printingTechnology;
        this.speedPrint = speedPrint;
        this.price = price;
        this.model=model;
        this.idPrinter=idPrinter;
    }

    public Printer() { }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrintingTechnology() {
        return printingTechnology;
    }

    public void setPrintingTechnology(String printingTechnology) {
        this.printingTechnology = printingTechnology;
    }

    public int getSpeedPrint() {
        return speedPrint;
    }

    public void setSpeedPrint(int speedPrint) {
        this.speedPrint = speedPrint;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getIdPrinter() {
        return idPrinter;
    }

    public void setIdPrinter(int idPrinter) {
        this.idPrinter = idPrinter;
    }
}
