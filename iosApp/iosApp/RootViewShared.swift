import SwiftUI
import shared

struct RootViewShared: View {

//    init(sharedNavigation: SharedNavigation) {
//        self.sharedStack = ObservableStateFlow(sharedNavigation.stack)
//        self.sharedNavigator = sharedNavigation.navigator
//    }
    
    @ObservedObject
    private var sharedStack: ObservableStateFlow<SharedNavigationStack>
    private let sharedNavigator: SharedNavigator

    private var path: [SharedDestination] { sharedStack.value.children }

    var body: some View {
        NavigationStack(
            path: Binding(
                get: { path },
                set: { newPath in
                    sharedNavigator.popTo(newStack: Array(newPath))
                }
            )
        ) {
            HomeView(onButtonClick: { sharedNavigator.goToDetail() })
                .navigationDestination(for: Destination.self) { destination in
                    switch destination {
                        // Display whatever view for provided Destination
                    }
                }
        }
    }

    var body: some View {
        NavigationStack(
            path: Binding(
                get: { iosPath.dropFirst() },
                set: { sharedNavigator.popTo(newStack: [iosPath.first!] + Array($0)) }
            )
        ) {
            LoginView(onLoginClick: { sharedNavigator.goToHomeScreen() })
                .navigationTitle("Login")
                .navigationDestination(for: SharedDestination.self) { destination in
                    switch destination {
                    case _ as SharedDestination.Home:
                        HomeView(onButtonClick: { sharedNavigator.goToDetail() })
                            .navigationTitle("Home")
                    case let detail as SharedDestination.Detail:
                        DetailView(id: detail.id)
                            .navigationTitle("Detail")
                    default:
                        EmptyView()
                    }
                }
        }
    }
    
    private struct LoginView: View {
        
        let onLoginClick: () -> Void
        
        var body: some View {
            VStack {
                Button("Login", action: onLoginClick).buttonStyle(.borderedProminent)
            }
        }
    }

    private struct HomeView: View {
        
        let onButtonClick: () -> Void
        
        var body: some View {
            VStack {
                Text("Home")
                Button("Open detail", action: onButtonClick).buttonStyle(.borderedProminent)
            }
        }
    }

    private struct DetailView: View {
        
        let id: String
        
        var body: some View {
            VStack {
                Text("Detail: \(id)")
            }
        }
    }
}
