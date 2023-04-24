import SwiftUI
import shared

private enum Destination : Hashable {
    case home
    case detail(String)
}

struct RootViewPureSwift: View {
    
@State private var path: [Destination] = []

var body: some View {
    NavigationStack(path: Binding(get: { path },set: { path = $0 })) {
        LoginView(onButtonClick: { path.append(Destination.home) })
            .navigationTitle("Login")
            .navigationDestination(for: Destination.self) { destination in
                switch destination {
                case .home:
                    HomeView(onButtonClick: { path.append(.detail("Hello, Brno!"))})
                        .navigationTitle("Home")
                case .detail(let id):
                    DetailView(id: id)
                        .navigationTitle("Detail")
                }
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
