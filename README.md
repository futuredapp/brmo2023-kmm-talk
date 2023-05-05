## KMM: Shared Navigation - Finding the Sweet Spot

This is a sample implementation of shared navigation logic in KMM for the talk "KMM: Shared Navigation - Finding the Sweet Spot" given by @matejsemancik at Brno Mobile 2023.

This project leverages [Decompose](https://github.com/arkivanov/Decompose) to fully share navigation logic 
between both Android and iOS platforms and uses SwiftUI's `NavigationStack` View to achieve native screen presentation on iOS.

The following cases are implemented here:
1. Basic push and pop of the screens
2. Support for back button callout on iOS
3. Dialogs / Alerts
4. Sheets with nested navigation on iOS
5. Typesafe arguments and results
