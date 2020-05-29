# COVID19-Tracker  

Design Document

Sukhbir Sekhon

Devin Williams

Benjamin Tenhover

## Introduction

COVID-19 Tracker allows you to monitor the number of COVID-19 cases around the globe and different countries as the spread of disease is increasing. The sole purpose of this application is to keep you aware of the spread of disease around you and help you share credible and trustworthy information with your friends and family. This is a one-stop application, which means it includes all the things you need to track the corona-virus. 
- You can check global statistics of COVID-19.
- You can also check statistics or cases of different countries individually.
- You can keep yourself updated with latest coronavirus news from different countries.
- Covid-19 Tracker would also provide you with safety tips and helpful resources to keep yourself safe and healthy during pandemic. 

## Storyboard
<img src="https://github.com/devinqw13/Images/blob/master/totalScreen.png" width="300" height="700">

# Requirements

# Class Diagram


![ClassDiagram](https://user-images.githubusercontent.com/43282559/82764317-ccf18380-9ddb-11ea-942d-64c05d27baee.jpg)


## Class Diagram Description

**MainActivity**:  This is the first screen where the user will land once they open the app. This screen will have a list of global statistics, combine data of all the countries, like total cases, active cases, recovered cases, new cases, new deaths. 

**CasesByCountryActivity**: This page will show user the statistics like total cases, active cases, recovered cases, new cases, and new deaths of each country.

**NewsActivity**: This page will show user the news related to coronavirus from different countries.

**RetrofitClientInstance**: This is a bootstrap class which is required for Retrofit which is used to create and process HTTP request and response from REST API. 

**Cases**: It is a noun class that represent cases. It has different attributes which displays different types of cases.

**News**: It is a also a noun class that represent news. It contains different attributes for news which helps make a news body in the page. 

**ICasesDao**: It is a interface for Retrofit to display and parse cases JSON data.

**INewsDao**: This will be an interface for Room to contain news data for different countries. User will have ability to select different countries to obtain news therefore, Room database or local database will allow us to do that. 

# Scrum Roles

DevOps/Product Owner/Scrum Master: Benjamin Tenhover

Frontend Developer: Devin Williams

Integration Developer: Sukhbir Sekhon

# Weekly Meeting

Saturday at 4 P.M. on Microsoft Teams.

