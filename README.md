# Pexels Android SDK
[![](https://jitpack.io/v/SanjayDevTech/pexels-android.svg)](https://jitpack.io/#SanjayDevTech/pexels-android)

## Add to your project
### Step 1: Add the below line in your Project level build.gradle file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' } // This url
    }
}
```

### Step 2: Add the below dependency
```gradle
implementation 'com.github.SanjayDevTech.pexels-android:<tag>'
```
In the place of &lt;tag&gt; you can replace any [release](releases/) tag

Eg: 0.0.1

## Features
- List of Curated Photos
- Search Photos by query string
- List of Popular Videos
- Search Videos by query string

## TODO
- Paging3 module

## License
Copyright 2022, Sanjay S

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.