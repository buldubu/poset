# Poset

- Developed to help people who has unique eating patterns such as vegans, fructose allergics, and non-pork eaters

## Getting Started

- To get this android project on android studio, clone this repo to your computer using ```https://github.com/ekrembal/poset.git```

#### Prerequisites

- Your version of Android Studio should be 3.5.3 or higher.

#### Setup
- After cloning or downloading this project, you need to start android studio from ```poset/Barcode```. Doing so will open an android stuido project in which you will able to see codes and layout that are used in this project.
- In this version, it isn't possible to run the project without setting up the server and editing ScannedBarcodeActivity.java.

##### Setting up the server on localhost
- Run ```index.php``` in server folder. Then, edit the IP in ScannedBarcodeActivity.java in line 151 to your IP.
- Now, you are able to reach server. Then, you need to enter products to table in this server.
- After doing that, you should be able to reach server when you run Poset app.

#### Running Poset App

- Open Barcode project in Android Studio as said above.
 - After building and running from the toolbar above the Android Studio screen, you can start using Poset app.

#### How to use the app

- Upon opening the app, you will see a screen where you will be asked which of the options given in the page applies for you. Select freely. Press save and you will proceed to another page.
 - In this page, there is a camera. YOu can scan barcodes using it and if the product is entered in database, the app will tell you wheter eating said product is appropiate for you.

## Built With

* Android Studio
* Apache Web Server

## What's next?

 - Detecting product using camera in real time, instead of barcode.
 - A calori calculator for those who wants to keep track of what they eat.

## Authors
 
 * Ekrem BAL (github.com/ekrembal)
 * Kadir Burak BULDU (github.com/buldubu)
 * Ahmet Burkay KINIK (github.com/burkaykinik)
