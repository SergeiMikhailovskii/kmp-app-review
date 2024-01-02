import SwiftUI
import inAppReviewKMP

struct ContentView: View {

	var body: some View {
        Button {
            InAppReviewManagerImpl(params: InAppReviewInitParams()).requestReview { error in
                print("Error =\(error)")
            }
        } label: {
            Text("Review app")
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
