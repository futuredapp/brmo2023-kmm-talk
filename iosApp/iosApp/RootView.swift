import SwiftUI
import shared

struct RootView: View {
    
    @ObservedObject
    private var stack: ObservableValue<ChildStack<StackDestination, StackChild>>
    var stackArray: [Child<StackDestination, StackChild>] { stack.value.items }
    
    private let actions: RootNavigationActions
    
    init(component: RootNavigation) {
        self.stack = ObservableValue(component.stack)
        self.actions = component.actions
    }
    
    var body: some View {
        NavigationStack(
            path: Binding(
                get: { stackArray.dropFirst() },
                set: { actions.iosPop(newStack: [stackArray.first!] + $0) }
            )
        ) {
            // You must pull the first component from the stack manually
            // You must ensure in Kotlin this component will always stay on the stack
            let homeScreen = stackArray.first?.instance as! HomeScreen
            HomeView(component: homeScreen)
                .navigationDestination(for: Child<StackDestination, StackChild>.self) { child in
                    switch child.instance {
                    case let detailScreen as DetailScreen:
                        DetailView(component: detailScreen)
                    default:
                        EmptyView()
                    }
                }
        }
    }
}
