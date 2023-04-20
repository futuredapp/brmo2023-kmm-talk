import SwiftUI
import shared

struct HomeView: View {
    
    private let actions: HomeScreenActions
    
    init(component: HomeScreen) {
        self.actions = component.actions
    }

    var body: some View {
        VStack {
            Text("HomeScreen").font(.headline)
            Button(
                "Go to detail",
                action: { actions.openDetail(argument: "Detail") }
            ).buttonStyle(.borderedProminent)
        }.navigationTitle("Home")
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView(component: HomeScreenComponentKt.HomeScreenMock())
    }
}
