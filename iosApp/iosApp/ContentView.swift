import SwiftUI
import inAppReviewKMP_sample

struct ContentView: View {
    
    private let reviewComponent = ReviewComponent()

	var body: some View {
        VStack {
            Button {
                reviewComponent.requestInAppReview()
            } label: {
                Text("In app review")
            }
            Button {
                reviewComponent.requestInMarketReview()
            } label: {
                Text("In market review")
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
