# ComicList
A simple android application to browse comics of [xkcd comics](https://xkcd.com/). This app will show
- list of comics
- comic details
- favorite comic list
- search for a comic by number or title.

## project structure
This project implements clean architecture, MVVM structure pattern, Retrofit for APIs, Room for local database, Coroutines, LiveData and DataBinding.

## Screenshots resources
<br>
<p align="start">
   <img width="250" src="https://user-images.githubusercontent.com/5102649/133151710-04a64e90-0a40-43ab-b003-8995b327951d.png">
   <img width="250" src="https://user-images.githubusercontent.com/5102649/133151839-8e40f7b1-844d-4683-89de-395b1f10cd3c.png">
   <img width="250" src="https://user-images.githubusercontent.com/5102649/133151911-fde03de4-6b14-4eb6-aeee-025649588d4a.png">
   <img width="250" src="https://user-images.githubusercontent.com/5102649/133151663-a69a5b03-4f8f-451e-b505-8f0e1bd2b9bf.png">
</p>


## Guide to app architecture
<br>
<p align="center">
  <img src="https://user-images.githubusercontent.com/5102649/133154141-724fc256-a806-410b-bde0-0402ea0fd5a1.PNG">
</p>
<br>

## The app has the following packages
1. **common**: It contains all the base classes of the application.
2. **data**: It contains the database, shared preference, network classes of the application.
3. **di**: It contains setup of koin dependency injection.
4. **feature**: It contains the packages of each feature in the application. Each feature contains two packages, one for module and one for screens.
5. **utils**: It contains the utils classes of the application.


## Library reference resources
1. Coroutines: https://codelabs.developers.google.com/codelabs/kotlin-coroutines/
2. Retrofit: https://square.github.io/retrofit/
3. Room: https://developer.android.com/topic/libraries/architecture/room.html
4. DataBinding: https://developer.android.com/topic/libraries/data-binding
5. Koin: https://insert-koin.io/docs/quickstart/android/
6. Glide: https://github.com/bumptech/glide
7. Skeleton: https://github.com/ethanhua/Skeleton

## License
```
   Copyright (C) 2019 khaled Aboshama

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
