# Trending
This app was written to display "trending" Github repos. There is no "trending" endpoint in the Github API, so we used:

https://github.com/huchenme/github-trending-api

Demonstrates retrieval using Retrofit2 and RxJava.

# Build Flavors

The project now supports two build flavors, business and personal, simply to illustrate how to implement productFlavors in Android.
The business version returns repos for a named Github Id. The personal version returns the trending repos. Both of them animate to 
a single repo display when any repo from the home screen is tapped.
