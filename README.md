# TotalFitness- An Android application for tracking progress

The name of my application is TotalFitness and it is an android based fitness tracking and nutrition application. 
TotalFitness aims to tackle the notion of having training and nutrition applications as separate entities by combining them into one application. 


### Features

   1. Users can register and sign-in
   2. Users can see exercises within the database
   3. Users can add and delete their weight, body fat percentage, water percentage and muscle percentage. 
   4. Users can add and delete the size of each body part 
   5. The system calculates a users recommended daily intake for 
   6. Users can change their password 
   7. Users can change their emails
   8. Users can delete account
   9. Users can retrieve a new password if their old one is forgotten
    

## Getting Started
1. Download Project
2. Install Android Studio:
        Google provides Android Studio for the Windows, Mac OS X, and Linux platforms. 
        You can download Android Studio from the Android Studio homepage (https://developer.android.com/studio/index.html), 
        where you'll also find the traditional SDKs with Android Studio's command-line tools. Before downloading Android Studio, make sure your platform meets the following requirements:
        
        * Windows requirements
        Microsoft Windows 7/8/10 (32-bit or 64-bit)
        3 GB RAM minimum, 8 GB RAM recommended (plus 1 GB for the Android Emulator)
        2 GB of available disk space minimum, 4 GB recommended (500 MB for IDE plus 1.5 GB for Android SDK and emulator system image)
        1280 x 800 minimum screen resolution
       
        *Mac OS requirements
        Mac OS X 10.10 (Yosemite) or higher, up to 10.13 (High Sierra)
        3 GB RAM minimum, 8 GB RAM recommended (plus 1 GB for the Android Emulator)
        2 GB of available disk space minimum, 4 GB recommended (500 MB for IDE plus 1.5 GB for Android SDK and emulator system image)
        1280 x 800 minimum screen resolution
        
        *Linux OS requirements
        GNOME or KDE desktop. Tested on Ubuntu 14.04 LTS, Trusty Tahr (64-bit distribution capable of running 32-bit applications)
        64-bit distribution capable of running 32-bit applications
        GNU C Library (glibc) 2.19 or later
        3 GB RAM minimum, 8 GB RAM recommended (plus 1 GB for the Android Emulator)
        2 GB of available disk space minimum, 4 GB recommended (500 MB for IDE plus 1.5 GB for Android SDK and emulator system image)
        1280 x 800 minimum screen resolution
3. Set up an emulator for android studio
4. Run application on emulator 

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

1. The database and php scripts for the application is hosted on 
https://login.siteground.com/users?lang=en 

The database can be accessed through SQL workbench using the following information:

$db_name= "peterp15_totalFitness";
$mysql_username="peterp15_osa2";
$mysql_password ="project123";
$server_name= "77.104.130.132";
$conn= mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);


The PHP files used to communicate with the database are stored in the project, check the folder called PHP Scripts.

Requests within the application is handled by a REST API Client called Retrofit. 

## Running the tests

1. Go into the android view
2. Naivigate to java
3. Right click on the android test folder and click on run tests. 

### Break down into end to end tests

* Tests are broken down into classes, each class represents a feature of the application e.g login. 
The classes consists of both Expresso UI tests and Unit tests. 
The tests are meant to emulate a users actions within the application. For example, registering and trying to use a particular feature such as weight tracking. 

## Built With

* [Retrofit](https://square.github.io/retrofit/) - API Client 
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) - Charts view
* [AlertDialog](https://github.com/pedant/sweet-alert-dialog) - Used to display alerts
* [G suite developer] (https://developers.google.com/gmail/api/guides/sending) - used to send emails to the user


## Authors

* **Osato Osagie** - *Initial work* - [University Of Leicester](https://campus.cs.le.ac.uk/svn/oo115/code/trunk/)

## Acknowledgments

* Inspiration taken from https://play.google.com/store/apps/details?id=com.easyfitness


