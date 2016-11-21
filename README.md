# _Rain Delay_

#### _11-18-2016_
![project screenshot](/resources/img/screenshot.png)
#### By _**Caleb Paul**_

## Description

_Rain Delay is an app that sets an alarm clock based on the current weather._
_It is designed for bike commuters who need to wake up at a different time if the weather is not conducive to safe riding._

## User Stories
* As a user, I want to be able to set my ideal riding conditions.
* As a user, I want to have an alarm that wakes me up earlier when my riding conditions are not met, so that I can catch my bus.
* As a user, I want to be able to edit my riding conditions.

## Setup/Installation Requirements

* _This project needs a stable version of Android Studio (preferably version 2.1) to compile, run and emulate without issues._

* _Clone this repository (https://github.com/CalebPaul/Rain-Delay) to your desktop._
* _Navigate to project folder using terminal._
* _Open Application in Android Studio Emulator, or, load into Android device._


## Known Bugs

_App is in process, API functionality not yet integrated._

## Specifications
* On opening app:
    - App displays main activity with a button for viewing a 5 day forecast, and a button for setting riding conditions.
    - '5 day forecast' button triggers a toast letting the user know that the feature is coming soon.
    - 'set riding conditions' button takes user to a new activity with input fields.

* On Set Conditions Activity:
    - App displays input fields for riding condition parameters, and two buttons; one for setting conditions, and one for example conditions.
    - App takes input from user and passes data to another ViewConditionsActivity where it is displayed.
    - App passes inputted passes data to ViewConditionsActivity on 'Set Riding Conditions' button press.
    - App displays list of example riding conditions, in a new activity on 'Set Riding Conditions' button press.  
        * Details are displayed 'on Click' in dialog fragment.    
    


## Support and contact details

_Caleb Paul: @calebpaulmusic_


## Technologies Used

* _Android Studio_
* _Java_


### License
*This app is licensed under the GPL license.*

Copyright (c) 2016 **_Caleb Paul_**