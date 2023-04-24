import Foundation
import SwiftUI
import Combine
import shared

class ObservableStateFlow<T : AnyObject> : ObservableObject {
    
    @Published
    var value: T
    
    private var cancellables = [AnyCancellable]()
    
    init(_ flow: StateFlowAdapter<T>) {
        self.value = flow.value
        doPublish(flow, onEach: { [weak self] newValue in
            self?.value = newValue
        }).store(in: &cancellables)
    }
    
    deinit {
        cancellables.forEach { $0.cancel() }
    }
}
