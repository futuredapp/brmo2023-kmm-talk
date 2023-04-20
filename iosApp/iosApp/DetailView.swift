import SwiftUI
import shared

struct DetailView: View {
    
    private var state: ObservableValue<DetailState>
    private let actions: DetailScreenActions
    
    @ObservedObject
    private var childSlot: ObservableValue<ChildSlot<SlotDestination, AnyObject>>
    private var isSheetPresented: Bool { childSlot.value.child != nil }
    
    init(component: DetailScreen) {
        self.state = ObservableValue(component.state)
        self.actions = component.actions
        self.childSlot = ObservableValue(component.slot)
    }

    var body: some View {
        VStack {
            Text(state.value.text).font(.headline)
            
            HStack {
                Button(
                    "Open another detail",
                    action: { actions.openAnotherDetail() }
                )
                .buttonStyle(.borderedProminent)
                
                Button(
                    "Open Sheet",
                    action: { actions.openSheet() }
                )
                .buttonStyle(.bordered)
            }
        }
        .navigationTitle(state.value.text)
        .sheet(
            isPresented: Binding(
                get: { isSheetPresented },
                set: { _ in actions.dismissSheet() }
            ),
            content: {
                switch childSlot.value.child?.instance {
                case let navigationRoot as RootNavigationComponent:
                    RootView(component: navigationRoot)
                default:
                    EmptyView()
                }
            }
        )
    }
}

struct DetailView_Previews: PreviewProvider {
    static var previews: some View {
        DetailView(component: DetailScreenComponentKt.DetailScreenMock())
    }
}
