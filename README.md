# Android Clean Architecture in Kotlin
Started project for Android

It includes:
*   Kotlin **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** for background operations.
*   A single-activity architecture, using the **Navigation component** to manage fragment operations.
*   Multi-layered modules following the principles of **Clean Architecture**.
*   A **presentation layer** that contains a fragment (View) and a **ViewModel** per screen.
*   Reactive UIs using **LiveData** observables and **Data Binding**.
*   A **data layer** with a repository and two data sources (local using Room and remote) that are queried with one-shot operations

