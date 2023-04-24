import SwiftUI
import shared

struct ContentView: View {
    
//    @State private var componentHolder = ComponentHolder {
//        RootNavigationComponent(componentContext: $0)
//    }
    
    let appNavigation: AppNavigation = AppNavigationImpl()
    
    var body: some View {
        RootViewPureSwift()
//        RootViewShared(appNavigation: appNavigation)
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
