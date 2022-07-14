# My Book Library -Esther-Idan-Project
![FormExample](/src/main/resources/static/im3.jpeg)
 
 
Esther Wahnon - estherwa@edu.hac.ac.il, Idan Baumer - idanba@edu.hac.ac.il


## Description

We have implemented an online book store including one admin zone 
controlled by login that can be accessed by  (at url “/admin”) to administer the store.
The shop offers options to adding products ,updating products and deleting products
in order to access the admin section the following credentials have to be used
 username "admin" and password "password"

The time the user completes the payment, the stock changes. If a product is not available anymore, 
 the payment will be cancelled  with a relevant message.The website recordS all store payments in the SQLITE database.
 We use session beans to implements the shopping basket.
 
 There are links/buttons to navigate between pages.

The website prints out in console the current number of active sessions in the website.
The architecture is according to Spring Model View Controller using scoped beans and IOC (injection).
Using as well Thymeleaf for views, including validation
and annotations for all features 



## Table of Contents 



- [Installation](#installation)
- Dowload XAMMP.app and all the spring pluggins.

FrontEnd:
    • As long as all the depencies all correctly installed in the local computer and the pom.xml it should run just as fine.

BackEnd:
• RuN XAMMP.app and create a database call ex4


## Usage
![FormExample](/src/main/resources/static/im2.jpeg)
![FormExample](/src/main/resources/static/im4.jpeg)
![FormExample](/src/main/resources/static/im5.jpeg)
![FormExample](/src/main/resources/static/im1.jpeg)



## Credits


Local exercises of the course 


---



## Features
Framework frontend: bootstrap 5.1, CSS, Java.

Framework Backend:  
•Mysql, Spring.