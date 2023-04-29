import SwiftUI
import shared

struct ContentView: View {
    
//    @State private var componentHolder = ComponentHolder {
//        RootNavigationComponent(componentContext: $0)
//    }
    
    let sharedNavigation: SharedNavigation = SharedNavigationImpl()
    
    var body: some View {
        RootViewPureSwift()
//        RootViewShared(sharedNavigation: sharedNavigation)
//        RootView(component: componentHolder.component)
//            .onAppear { LifecycleRegistryExtKt.resume(componentHolder.lifecycle) }
//            .onDisappear { LifecycleRegistryExtKt.stop(componentHolder.lifecycle) }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
