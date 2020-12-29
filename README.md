Nerde Yesem
=================

A random restaurant recommendation nearby your location app.

Introduction
------------
1.	The application should be entered via Firebase Email/Password Authentication
	- Note: please create an account to test
	Email: *
	Password: *

2.	Gets restaurants whose location are close to the user and user selects one of them.
	● List at least 5 restaurants.
	● To get user location, use mobile phone location.

3.	Selected restaurant information should be shown in a different page when the user clicked to a restaurant.
	● Show last 5 reviews of the restaurant (use zomato API)

4.	The application should contain at least two pages, the "BACK" key must be implemented as "HistoryBack".

5.	The application should know its state when the user pushes it at the background. For example,
	●	I found a restaurant and opened restaurant details in the "Nerde Yesem"
	●	I received a Whatsapp message and opened Whatsapp message
	●	I then returned to "Nerde Yesem", the state should be the same when I left "Nerde Yesem".

6.	(BONUS) Create a cloud service on any cloud service provider (Amazon, DigitalOcean, Google etc.) 
	free services provided are enough to handle. Create a primitive user login and dummy user database
    on cloud. Keep most preferred restaurants of dummy users on the cloud, and make appropriate changes
    on mobile application.

7.	(BONUS) Additional to step 7, user able to take photos of favorite restaurants via application,
     and upload them to cloud, so that other users are able to see these photos.

Screenshots
-----------
to be added soon...

Getting Started
---------------
This project uses the Gradle build system. To build this project, use the
`gradlew build` command or use "Import Project" in Android Studio.


### Zomato API key

Nerde yesem uses the [Zomato API](https://developers.zomato.com/api) to Search for restaurants by restaurant name,
cuisine or location. To view detailed information including ratings, location and culinary restaurant collections.

To use the API, you will need to obtain a free developer API key. See the
[Zomato API Documentation](https://developers.zomato.com/api#headline1) for instructions.



Libraries Used
--------------

  * [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
   multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [FirebaseAuth][2] Firebase Email Authentication.
  * [Location Data][3] Location information about user.
  * [Architecture][10] - A collection of libraries that help you design robust, testable, and
   maintainable apps. Start with classes for managing your UI component lifecycle and handling data
   persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycle][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
  * [WorkManager][18] - Manage your Android background jobs.
  * [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Lay out widgets using different algorithms.
  * Third party
  * [Glide][90] for image loading


[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://firebase.google.com/docs/auth
[3]: https://developers.google.com/maps/documentation/android-sdk/location
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[18]: https://developer.android.com/topic/libraries/architecture/workmanager
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[90]: https://bumptech.github.io/glide/


