import SwiftUI
import shared

private enum Destination : Hashable {
    case home
    case detail
}

struct RootViewPureSwift: View {
    
    @State private var path: [Destination] = []

    var body: some View {
        NavigationStack(
            path: Binding(
                get: { path },
                set: { newPath in
                    path = newPath
                }
            )
        ) {
            HomeView(onButtonClick: { path.append(Destination.detail) })
                .navigationDestination(for: Destination.self) { destination in
//                    switch destination {
//                        // Display whatever view for provided Destination
//                    }
                }
        }
    }
    
    private struct LoginView: View {
        
        let onButtonClick: () -> Void
        
        var body: some View {
            VStack {
                Button("Log In", action: onButtonClick).buttonStyle(.borderedProminent)
            }
        }
    }
    
    private struct HomeView: View {
        
        let onButtonClick: () -> Void
        
        var body: some View {
            VStack(spacing: 10) {
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
