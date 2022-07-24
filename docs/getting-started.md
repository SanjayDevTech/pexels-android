# Getting Started

## Build status
[![](https://jitpack.io/v/SanjayDevTech/pexels-android.svg)](https://jitpack.io/#SanjayDevTech/pexels-android)

## Setup
```groovy title="build.gradle"
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' } // This url
    }
}
```

```groovy title="app/build.gradle"
implementation 'com.github.SanjayDevTech.pexels-android:<version>' //(1)
```

1.  In the place of &lt;version&gt; you can replace any [release](https://github.com/SanjayDevTech/pexels-android/releases/) tag
    
    Eg: 0.0.1

## API Key
Pexels service needs an API Key to work.
Get an API key from their official website [Pexels](https://www.pexels.com/)

## Pexels client
Use `Pexels` class to get a `PexelsClient`.

=== "Kotlin"

    ``` kotlin
    val apiKey = "..."
    val pexelsClient: PexelsClient = Pexels.createClient(apiKey)
    ```

=== "Java"

    ``` java
    String apiKey = "...";
    PexelsClient pexelsClient = Pexels.createClient(apiKey);
    ```

## JVM compatibility
It uses Java 11 as source and target compile versions.
It requires the projects needs to be Java 11.

=== "Kotlin"

    ``` groovy title="app/build.gradle"
    android {
        //...
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_11
            targetCompatibility JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = '11'
        }
    }
    ```

=== "Java"

    ``` groovy title="app/build.gradle"
    android {
        //...
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_11
            targetCompatibility JavaVersion.VERSION_11
        }
    }
    ```


## BuildConfig (Optional)
Since the API Keys can be extracted from public git repo, if it is used in code directly. I suggest to create secrets file and integrating into your projects.

### Secrets file
Create a `secrets.properties` file in root of the project.
Make sure you added the `secrets.properties` entry in `.gitignore`, so that it won't get exposed to public.

Paste the api key in `secrets.properties` file as:

``` properties title="secrets.properties"
PEXELS_API_KEY=<apikey>
```

### Extract key
Define the below function `getApiKey()` in `app/build.gradle` file.

``` groovy title="app/build.gradle"
static def getApiKey(){
    def props = new Properties()
    try {
        props.load(new FileInputStream(new File('secrets.properties')))
        return props['PEXELS_API_KEY']
    } catch(ignored) {
        return ""
    }
}
```

And add the API Key in the Build configuration
``` groovy title="app/build.gradle"
android {
    //...
    buildTypes {
        debug {
            // for debug
            buildConfigField "String", "API_KEY", "\"${getApiKey()}\""
        }
        release {
            // for release
            buildConfigField "String", "API_KEY", "\"${getApiKey()}\""
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

### Access from code
Consume the API Key using the `BuildConfig` class.
This class will be generated after every successful gradle build.

=== "Kotlin"

    ``` kotlin
    val apiKey = BuildConfig.API_KEY // (1)
    ```

    1. This shows error if the project hasn't been build yet.

=== "Java"

    ``` java
    String apiKey = BuildConfig.API_KEY; // (1)
    ```

    1. This shows error if the project hasn't been build yet.
