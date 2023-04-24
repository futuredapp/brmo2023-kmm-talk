import SwiftUI
import shared

struct RootNavigationView: View {
    
    @ObservedObject
    private var kotlinStack: ObservableValue<ChildStack<Destination, AnyObject>>
    var iosPath: [Child<Destination, AnyObject>] { kotlinStack.value.items }
    
    private let actions: RootNavigationActions

    
    init(component: RootNavigation) {
        self.kotlinStack = ObservableValue(component.stack)
        self.actions = component.actions
    }
    
    var body: some View {
        NavigationStack(
            path: Binding(
                get: { iosPath.dropFirst() },
                set: { actions.iosPopTo(iosPath: Array($0)) }
            )
        ) {
            let homeScreen = iosPath.first?.instance as! HomeScreen
            HomeView(component: homeScreen)
                .navigationTitle("Home")
                .navigationDestination(for: Child<Destination, AnyObject>.self) { child in
                    switch child.instance {
                    case let detailScreen as DetailScreen:
                        DetailView(component: detailScreen)
                            .navigationTitle("Detail")
                    default:
                        EmptyView()
                    }
                }
        }
    }
}

struct RootNavigationView_Previews: PreviewProvider {
    static var previews: some View {
        RootNavigationView(component: RootNavigationComponentKt.RootNavigationMock())
    }
}
