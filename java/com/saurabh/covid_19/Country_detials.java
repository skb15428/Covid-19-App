package com.saurabh.covid_19;

public class Country_detials {
    private String flag,country,cases,today_cases,deaths,today_deaths,recovered,active,critical,tests,population,continent;

    public Country_detials() {
    }

    public Country_detials(String flag, String country, String cases, String today_cases, String deaths, String today_deaths, String recovered, String active, String critical, String tests, String population, String continent) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.today_cases = today_cases;
        this.deaths = deaths;
        this.today_deaths = today_deaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.tests = tests;
        this.population = population;
        this.continent = continent;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getToday_cases() {
        return today_cases;
    }

    public void setToday_cases(String today_cases) {
        this.today_cases = today_cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getToday_deaths() {
        return today_deaths;
    }

    public void setToday_deaths(String today_deaths) {
        this.today_deaths = today_deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
