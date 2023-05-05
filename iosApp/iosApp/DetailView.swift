import SwiftUI
import shared

struct DetailView: View {
    
    @ObservedObject
    private var state: ObservableValue<DetailViewState>
    private let actions: DetailScreenActions
    
    init(component: DetailScreen) {
        self.state = ObservableValue(component.state)
        self.actions = component.actions
    }

    var body: some View {
        VStack {
            Text(state.value.text).font(.headline)
            Button(
                "Open another detail",
                action: { actions.openAnotherDetailClicked() }
            )
            .buttonStyle(.borderedProminent)
        }
    }
}
