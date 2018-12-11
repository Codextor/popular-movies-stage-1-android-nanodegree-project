# Popular Movies, Stage 1

## The Movie Database API Key is required.

In order for the Popular Movies Stage 1 app to function properly, an API key for themoviedb.org must be included with the build.

We recommend you obtain a key, and include the unique key for the build by updating the following line in [USER_HOME]/.gradle/gradle.properties

MyTheMovieDatabaseApiKey="insert_api_key_here"  
Replace the string <insert_api_key_here> with your unique key.

## Project Overview
Most of us can relate to kicking back on the couch and enjoying a movie with friends and family. In this project, you’ll build an app to allow users to discover the most popular movies playing. We will split the development of this app in two stages. First, let's talk about stage 1.

In this stage you’ll build the core experience of your movies app.

Your app will:
- Present the user with a grid arrangement of movie posters upon launch.
- Allow your user to change sort order via a setting:
  - The sort order can be by most popular or by highest-rated
- Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
  - original title
  - movie poster image thumbnail
  - A plot synopsis (called overview in the api)
  - user rating (called vote_average in the api)
  - release date

## Why this Project
To become an Android developer, you must know how to bring particular mobile experiences to life. Specifically, you need to know how to build clean and compelling user interfaces (UIs), fetch data from network services, and optimize the experience for various mobile devices. You will hone these fundamental skills in this project.

By building this app, you will demonstrate your understanding of the foundational elements of programming for Android. Your app will communicate with the Internet and provide a responsive and delightful user experience.

## What Will I Learn After Stage 1?
- You will fetch data from the Internet with theMovieDB API.
- You will use adapters and custom list layouts to populate list views.
- You will incorporate libraries to simplify the amount of code you need to write


## How Do I Complete this Project?

### Supporting Course Material
You should have the skills you need to complete this app after completing Lessons 1-4 of Developing Android Apps.

Required Tasks
- Build a UI layout for multiple Activities.
- Launch these Activities via Intent.
- Fetch data from themovieDB API

### Implementation Guide
For step-by-step support, we've provided details on how to approach each task in this [Implementation Guide](https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true).
