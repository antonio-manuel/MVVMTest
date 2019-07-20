Project structure:
- App: contains only the opening activity with its splash theme.
- Feature folder contains all different features, in this case only dashboard
- Infrastructure contains all common modules such as: api, core components, testing utilities, custom exceptions and navigation logic

Feature modules structure:
- Data to contain repositories, datasources and some mappers
- DI to define all dependency injections rules
- Domain contains business models and usecases
- Presentation contains viewmodels and views

Technologies used:
- Kodein for DI
- AndroidX for UI
- arrow for datatypes like: Option or Try
- RXJava to compose asynchronous calls
- Moshi to parse JSON
- Okhttp and retrofit to manage API calls
- MPChart to present chart data
- JUnit5 for unittest
- Espresso for UI tests


Notes:
There is an Espresso test for DashboardFragment, but due to the mocking library uses 
and according to their issues log, it only works with emulators>=28 until fixed.
