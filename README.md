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
[Covid-19 Tracker Storyboard](https://projects.invisionapp.com/prototype/Covid19App-ckasbjsa000lyub0113lvsvq7/play/665064e1)
<br />
<img src="https://github.com/devinqw13/Images/blob/master/totalScreen.png" width="250" height="500"><img src="https://github.com/devinqw13/Images/blob/master/ByCountry.png" width="250" height="500"><img src="https://github.com/devinqw13/Images/blob/master/News.png" width="250" height="500">

## Functional Requirements

### Requirement 1: View up-to-date coronavirus stats by location  

#### Scenario  
As a concerned user, I want to view up-to-date, accurate coronavirus stats in any specified area, such as total case numbers, recoveries, and deaths, as well as a graph depicting the incline or decline in daily new cases. I want to be able to view this all on one application with ease.  

#### Dependencies  
Up-to-date coronavirus data is available and accessible.

#### Assumptions
Data we are using for coronavirus stats is accurate.  

#### Examples  

**1.1**  

**Given** current coronavirus data is available  

**When** I search my current country or select my current country from the country list  

**Then** I should receive current:  

- Country and global case totals  

- Country and global death totals  

- Country and global case incline/decline graphs  
  
  
**1.2**  

**Given** current coronavirus data is available  

**When** I search my current state or select my current state from the state list  

**Then** I should receive current:  

- State, country, and global case totals  

- State, country, and global death totals  

- State, country, and global case incline/decline graphs  
  
  
**1.3**  

**Given** current coronavirus data is available  

**When** I search for location, “dskffdjgkdfgwer”  

**Then** I should receive an error message prompting me to enter a valid location.  
  
  
### Requirement 2: View up-to-date coronavirus news by location  

#### Scenario  
As a concerned user, I want to view up-to-date coronavirus related news in my area or any other specified area.   

#### Dependencies  
Up-to-date coronavirus data is available and accessible.

#### Assumptions
Data we are using for coronavirus news is accurate.  

#### Examples  

**2.1**  

**Given** current coronavirus data is available  

**When** I click on the news tab on the bottom of the screen and  

**When** I select my current country from the country list  

**Then** I should receive a list of recent news articles regarding coronavirus in my country.  
  
  
**2.2**  

**Given** current coronavirus data is available  

**When** I click on the news tab on the bottom of the screen and  

**When** I select my current country from the country list and then my current state from the state list  

**Then** I should receive a list of recent news articles regarding coronavirus in my state.  


**2.3**  

**Given** current coronavirus data is available  

**When** I click on the news tab on the bottom of the screen and  

**When** I select my country from the country list and   

**When** I click on a specific news article  

**Then** I should be directed to the original news article to learn more.  



## Class Diagram

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

## Scrum Roles

DevOps/Product Owner/Scrum Master: Benjamin Tenhover

Frontend Developer: Devin Williams

Integration Developer: Sukhbir Sekhon

## Weekly Meeting

Saturday at 4 P.M. on Microsoft Teams.

