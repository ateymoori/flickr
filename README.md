
# Android Flickr App


## Features

Search photos in Flickr with paging

## Technologies Used

- Kotlin programming language
- Jetpack Compose UI toolkit
- Hilt dependency injection
- MVVM architecture pattern
- Coroutines for asynchronous programming
- Kotlin Flow 
- Three-layer architecture
    -   Domain layer for use cases
    -   Data layer for APIs
    -   Presentation layer for screens


## Code Structure

The code follows a three-layer architecture:

1.  **Domain Layer**: This layer defines the business logic of the app. It contains the use cases that the app needs to perform, such as searching for photos on Flickr. The domain layer is independent of the data layer and the presentation layer.
    
2.  **Data Layer**: This layer provides an abstraction over the APIs used in the app. It contains the repository classes that fetch data from the APIs and map the data to domain models. The data layer uses Coroutines for asynchronous programming.
    
3.  **Presentation Layer**: This layer handles the UI logic of the app. It contains the Jetpack Compose UI components and the ViewModel classes that connect the UI . 
     

     