# Dagger2Kotlin

#### Dagger2

A fast dependency injector for Android.

Dagger is a compile-time framework for dependency injection. It uses no
reflection or runtime bytecode generation, does all its analysis at
compile-time, and generates plain Java source code.

Dagger is actively maintained by the same team that works on [Guava]. Snapshot
releases are auto-deployed to Sonatype's central Maven repository on every clean
build with the version `HEAD-SNAPSHOT`. The current version builds upon previous
work done at [Square][square].

# Demo
![Dagger2Kotlin](screenshots/Dagger2KotlinDemoGif.gif) 

Dagger2 Installation 

#### Android Gradle
```groovy
// Add Dagger dependencies
dependencies {
  compile 'com.google.dagger:dagger:2.x'
  annotationProcessor 'com.google.dagger:dagger-compiler:2.x'
}
```
#### Kotlin Coroutine
Kotlin 1.1 introduced coroutines, a new way of writing asynchronous, non-blocking code (and much more).Coroutines not only open the doors to asynchronous programming, but also provide a wealth of other possibilities such as concurrency, actors, etc

Coroutine Installation 
```groovy
dependencies {
    ...
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1"
}
```

# Prerequisites
* __Android Studio 3.0__
* __Android Device with USB Debugging Enabled__

# Built With

* __[Android Studio](https://developer.android.com/studio/index.html)__ - The Official IDE for Android
* __[Kotlin Coroutine](https://kotlinlang.org/docs/tutorials/coroutines/coroutines-basic-jvm.html)__ - Kotlin Coroutine
* __[Dagger2](https://github.com/google/dagger)__ Dagger2
* __[Gradle](https://gradle.org)__ - Build tool for Android Studio

Thanks for reading this repo. Be sure to click ★ below to recommend this repo if you found it helpful. It means a lot to me.

For more about programming, follow me on [Medium](https://medium.com/@yash786agg)

Also, Let’s become friends on [Linkedin](http://bit.ly/24t4EVI)
