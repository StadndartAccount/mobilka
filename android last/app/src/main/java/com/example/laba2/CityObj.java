package com.example.laba2;

public class CityObj {
    public String Name;
    public String Country;
    public String Language;
    public Integer Population;

    public CityObj(String name, String country, String language, Integer population) {
        Name = name;
        Country = country;
        Language = language;
        Population = population;
    }

    public String GetName() {
        return Name;
    }
}
