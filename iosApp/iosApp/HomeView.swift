import SwiftUI
import shared

struct HomeView: View {
    
    private let actions: HomeScreenActions
    
    init(component: HomeScreen) {
        self.actions = component.actions
    }

    var body: some View {
        VStack {
            Text("You're at home").font(.headline)
            Button(
                "Open detail",
                action: { actions.openDetailClicked() }
            ).buttonStyle(.borderedProminent)
        }
    }
}
