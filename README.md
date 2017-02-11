#Android_ND_P4_BuildItBigger

This is an Android project developed as part of Udacity Android nanodegree course.

The application has 2 flavors, free and paid.
It uses multiple libraries and Google Cloud Endpoints and contains of four modules:

1. A Java library that provides jokes.

2. A Google Cloud Endpoints (GCE) project that serves those jokes.

3. An Android Library containing an activity for displaying jokes.

4. An Android app that fetches jokes from the GCE module and passes them to the Android Library for display.

To tie it all together, Gradle task has been created that:

1. Launches the GCE local development server.

2. Runs all tests.

3. Shuts the server down again.

Firebase - The application also displays an Interstitial Ad in the free version, after the user hits the button, and before the joke is shown. 
