import SwiftUI
import shared

struct ContentView: View {
    
    @State
    private var componentHolder = ComponentHolder {
        RootNavigationComponent(context: $0)
    }
    
    var body: some View {
        RootView(component: componentHolder.component)
            .onAppear { LifecycleRegistryExtKt.resume(componentHolder.lifecycle) }
            .onDisappear { LifecycleRegistryExtKt.stop(componentHolder.lifecycle) }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
