# Android Nanodegree Stock Hawk

[![Crates.io](https://img.shields.io/crates/l/rustc-serialize.svg?maxAge=2592000)]()

<p align="justify">
Stock Hawk is the third project in Udacity's Android Developer Nanodegree. The purpose of this project was to diagnose problems and practice improving apps. 
</p>

## Getting started
Get the starter code for [Stock Hawk](https://github.com/udacity/StockHawk) here. This sample uses the Gradle build system. To build this project, use the `gradlew build` command or use `Import Project` in Android Studio.

## User Feedback for Stock Hawk:
* Right now I can't use this app with my screen reader. My friends love it, so I would love to download it, but the buttons don't tell my screen reader what they do.
* We need to prepare Stock Hawk for the Egypt release. Make sure our translators know what to change and make sure the Arabic script will format nicely.
* Stock Hawk allows me to track the current price of stocks, but to track their prices over time, I need to use an external program. It would be wonderful if you could show more detail on a stock, including its price over time.
* I use a lot of widgets on my Android device, and I would love to have a widget that displays my stock quotes on my home screen.
* I found a bug in your app. Right now when I search for a stock quote that doesn't exist, the app crashes.
* When I opened this app for the first time without a network connection, it was a confusing blank screen. I would love a message that tells me why the screen is blank or whether my stock quotes are out of date.


## Rubric

### Required components
* Each stock quote on the main screen is clickable and leads to a new screen which graphs the stock’s value over time.
* Stock Hawk does not crash when a user searches for a non-existent stock.
* Stock Hawk Stocks can be displayed in a collection widget.
* Stock Hawk app has content descriptions for all buttons.
* Stock Hawk app supports layout mirroring using both the RTL attribute and the start/end tags.
* Strings are all included in the strings.xml file and untranslatable strings have a translatable tag marked to false.
* Stock Hawk displays a default text on screen when offline, to inform users that the list is empty or out of date.

## Project Review
> "Meets specifications"

## Screenshots
![Stock Hawk MainActivity](https://github.com/DirajHS/StockHawk/blob/master/ScreeenShot1.png)
![Stock Hawk Stock Graph](https://github.com/DirajHS/StockHawk/blob/master/ScreenShot2.png)

![Stock Hawk Widget](https://github.com/DirajHS/StockHawk/blob/master/ScreenShot3.png)

## License

MIT License

Copyright (c) 2016 Diraj H S

<p align="justify">
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
</p>

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

<p align="justify">
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
</p>
