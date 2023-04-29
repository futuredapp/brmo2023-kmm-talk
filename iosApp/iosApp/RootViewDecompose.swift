import SwiftUI
import shared

//struct RootView: View {
//
//    @ObservedObject
//    private var kotlinStack: ObservableValue<ChildStack<Destination, AnyObject>>
//    var iosPath: [Child<Destination, AnyObject>] { kotlinStack.value.items }
//
//    private let actions: RootNavigationActions
//
//
//    init(component: RootNavigation) {
//        self.kotlinStack = ObservableValue(component.stack)
//        self.actions = component.actions
//    }
//
//    var body: some View {
//        NavigationStack(
//            path: Binding(
//                get: { iosPath.dropFirst() },
//                set: { actions.iosPopTo(iosPath: Array($0)) }
//            )
//        ) {
//            let homeScreen = iosPath.first?.instance as! HomeScreen
//            HomeView(component: homeScreen)
//                .navigationDestination(for: Child<Destination, AnyObject>.self) { child in
//                    switch child.instance {
//                    case let detailScreen as DetailScreen:
//                        DetailView(component: detailScreen)
//                    default:
//                        EmptyView()
//                    }
//            }
//        }
//    }
//}
//
//struct RootView_Previews: PreviewProvider {
//    static var previews: some View {
//        RootView(component: RootNavigationComponentKt.RootNavigationMock())
//    }
//}
