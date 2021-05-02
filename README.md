# SpeedRoomMatingApp

SpeedRoomMatingApp is an android application that enables people to meet potential roommates and the core function of this application is listing Upcoming events. This application is built with Kotlin language using MVVM architectural pattern and design.


## Design Implementation

Tab Layout

-   Used tab layout to show horizontal tabs in a tabular format such that users can switch from one tab to another easily on the same page.

ViewPager

-   Used ViewPager to enable swipes between tabs to display different layouts on swipe or on  tab

AppBar and ToolBar

-   Used AppBar to serve as a wrapper for Toolbar and it  helps to achieve desired scrolling behaviour

-   Used a ToolBar to display the Title on the app



## Dependency Injection

Dagger2

-   Used Dagger2 for dependency injection of classes because it allows easy partitioning of related dependencies into different containers.

-   It helps to construct instances of my classes and satisfies their dependencies which means I don't need to keep instantiating my classes all over the app and this helps in app optimization and reduces boilerplate code hence making it more readable and testable




## Api Request

Retrofit

-   Used Retrofit to make Api requests to the server to fetch upcoming event data because it makes it relatively easy to retrieve and upload JSON data via a REST based web service.




## Testing

-   Used Mockito for unit testing in order to test the performance requirements of the application because it allows me to mock external dependencies, interfaces or objects.

-   Used Espresso for UI integration testing because  it is light-weight and also provides automatic synchronization of test actions with the UI of the application.





### Loading Images

Glide

-   Used glide to load image into imageView because it load and display images in an optimized manner, it allows disk caching and loading images  in background threads without affecting the main thread

## State Management

-   Used a generic Resources class  to maintain the state of the app, this resource class captures both the state and the data.




### Crashlytics

-   Used firebase Crashlytics because it provides real time crash reports  that helps to track, prioritize, and fix stability issues in the application.

-   It helps to gain user insight by helping to solve crashes users run into that I can't seem to reproduce on my end.
