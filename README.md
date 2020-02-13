# OpenWhyd

OpenWhyd is an Android app that uses the OpenWhyd API to display a list of the top recent hot music titles per genre submitted by users throughout the world.

https://openwhyd.github.io/openwhyd/API#openwhyd-data-export-api

The app utilizes an in memory cache to save network requests for each specific genre. 

Another capability is the app has built in pagination that allows you to load more music titles as you scroll to the end of the list. 

If the specific track has a youtube url, that url will be loaded and played via Youtube Player.

Otherwise, an image will be show.

### Technologies used:
Android Architecture Components(LiveData/ViewModel), RxJava, Dagger, Retrofit, Moshi, Glide, and YoutubePlayer API

### Hot Track Genres

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/first_screen.png width="200" height="400">

### Hot Track Titles List

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/hottracks.png width="200" height="400">

### Hot Track Detail with Youtube

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/youtube.png width="200 height="400">

### Hot Track Detail with Image

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/details.png width="200" height="400">



