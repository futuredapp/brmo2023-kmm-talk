import SwiftUI
import shared

struct DetailView: View {
    
    @ObservedObject
    private var state: ObservableValue<DetailViewState>
    private let actions: DetailScreenActions
    
    @ObservedObject
    private var slot: ObservableValue<ChildSlot<SlotDestination, SlotChild>>
    
    init(component: DetailScreen) {
        self.state = ObservableValue(component.state)
        self.slot = ObservableValue(component.slot)
        self.actions = component.actions
    }

    var body: some View {
        VStack(spacing: 10) {
            Text(state.value.text).font(.headline)
            Text("Dialog result: \(state.value.dialogResult)")
            Text("Sheet result: \(state.value.sheetResult)")
            Button(
                "Open another detail",
                action: actions.openAnotherDetailClicked
            )
            .buttonStyle(.borderedProminent)
            
            Button(
                "Confirmation Alert",
                action: actions.openConfirmationDialogClicked
            )
            .buttonStyle(.borderless)
            
            Button(
                "Confirmation Sheet",
                action: actions.openConfirmationSheetClicked
            )
            .buttonStyle(.borderless)
        }
        .confirmationAlert(slot: slot.value)
        .confirmationSheet(slot: slot.value)
    }
}

extension View {
    
    @ViewBuilder
    func confirmationAlert(slot: ChildSlot<SlotDestination, SlotChild>) -> some View {
        let dialog = slot.child?.instance as? ConfirmationDialog
        alert(
            "Are you positive?",
            isPresented: Binding(
                get: { dialog != nil },
                set: { _ in dialog?.actions.onDismiss() }
            ),
            actions: {
                Button("Nope", action: { dialog?.actions.onNegative() })
                Button("Yep", action: { dialog?.actions.onPositive() })
            },
            message: {
                Text("It's important to stay positive")
            }
        )
    }
    
    @ViewBuilder
    func confirmationSheet(slot: ChildSlot<SlotDestination, SlotChild>) -> some View {
        let shieeet = slot.child?.instance as? ConfirmationSheet
        sheet(
            isPresented: Binding(
                get: { shieeet != nil },
                set: { _ in shieeet?.actions.onDismiss() }
            ),
            content: {
                VStack(spacing: 10) {
                    Text("Are you positive?").font(.headline)
                    Text("It's important to stay positive")
                    HStack(spacing: 10) {
                        Button("Nope", action: { shieeet?.actions.onNegative()})
                        Button("Yep", action: { shieeet?.actions.onPositive()})
                    }
                }
                .presentationDetents([.fraction(0.3),.large])
            }
        )
    
    }
}
