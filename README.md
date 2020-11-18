# Transformers Application

This application provides a layout for creating/editing/deleting transformers, as creating elavorate fights that will provide a winner based on their overall skills and other special traits. Was originally developed as a technical test for the Android developer position on Aequilibrium

## Description

In the application you can:
* Create / Edit / Delete transformers from the server
* List all transformers from both teams created on server
* Wage war among created transformers
* CDetermine the winning team and the destroyed transformers

## Prerequisites

The following components must be installed in order to go through the Usage Instructions.

* [Gradle Build Tool](https://gradle.org/).
* Latest release of the [Java JDK version 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
* [Git client](https://git-scm.com/downloads).
* [Android Studio](https://developer.android.com/studio/) version 3.1 or later.
* [Android SDK](https://developer.android.com/studio/index.html#downloads) with the API level 19 or later.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/juanchossbb/transformers.git
```

### Import to Android Studio

1. Open Android Studio and select the "Import project" menu item in the welcome screen. 
2. Navigate to the location where you checked out the repository in your computer, and select the debug folder.
3. Android Studio should be able to import the debug project automatically, which includes the library itself and a minimal test app.

### Build debug APK
To build an APK for testing you must run the command
```bash
gradlew assembleDebug test
```
This creates an APK named app-debug.apk in transformers/app/build/outputs/apk/
This will also run Unit Test created for the main app functionalities

## Maintainers
This project is mantained by:
* [Juan Hurtado](http://github.com/juanchossbb)
