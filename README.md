# OpenWhyd

OpenWhyd is an Android app that uses the OpenWhyd API to display a list of the top recent hot music titles per genre submitted by users throughout the world.

https://openwhyd.github.io/openwhyd/API#openwhyd-data-export-api

The app utilizes an in-memory cache to store network requests made for each music genre.

Another capability is the app has built in pagination that allows you to load more music titles as you scroll to the end of the list. A network call is made again and the result of new call is concatenated with the old call and stored in the cache.

If the specific track has a youtube url, that url will be loaded and played via Youtube Player.

Otherwise, an image will be show.

### Technologies used:
Android Architecture Components(LiveData/ViewModel/Navigation Component), RxJava, Dagger, Retrofit, Moshi, Glide, and YoutubePlayer API

### Hot Track Genres

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/first_screen.png width="200" height="400">

### Hot Track Titles List

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/list.png width="200" height="400">

### Hot Track List in Grid Format
<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/grid.png width="200" height="400">

### Hot Track Detail with Youtube

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/youtube.png width="200" height="400">

### Hot Track Detail with Image

<img src=https://github.com/sidthekidgowda/OpenWhyd/blob/master/app/src/main/res/drawable/details.png width="200" height="400">



