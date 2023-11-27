import SwiftUI


struct TwoFieldsWithLabels {
    var widthOfFirstField: CGFloat?
    var firstLabel = ""
    var firstFieldValue = ""
    
    var widthOfSecondField: CGFloat?
    var secondLabel = ""
    var secondFieldValue = ""
}
struct TwoFieldsWithLabelsView: View {
    var isEditing: Bool
    @State var twoFields = TwoFieldsWithLabels()
    var body: some View {
        HStack {
            if isEditing {
                EditingTwoFieldsView(twoFields: twoFields)
            } else {
                DisplayingTwoFieldsView(streetAndNumber: twoFields)
                }
        }.font(.title)
    }
}

struct EditingTwoFieldsView : View {
    @State var twoFields = TwoFieldsWithLabels()
    var body: some View {
        TextField(text: $twoFields.firstFieldValue) {
            Text(twoFields.firstLabel)
        }
        .border(/*@START_MENU_TOKEN@*/Color.gray/*@END_MENU_TOKEN@*/, width: /*@START_MENU_TOKEN@*/1/*@END_MENU_TOKEN@*/)
        .frame(width: twoFields.widthOfFirstField != nil ? twoFields.widthOfFirstField : nil)
        TextField(text: $twoFields.secondFieldValue) {
            Text(twoFields.secondLabel)
        }
        .border(/*@START_MENU_TOKEN@*/Color.gray/*@END_MENU_TOKEN@*/, width: /*@START_MENU_TOKEN@*/1/*@END_MENU_TOKEN@*/)
        .frame(width: twoFields.widthOfSecondField != nil ? twoFields.widthOfSecondField : nil)
    }
}
struct DisplayingTwoFieldsView: View {
    @State var streetAndNumber = TwoFieldsWithLabels()
    var body: some View {
        VStack {
            HStack {
                Text(streetAndNumber.firstFieldValue)
                Text(streetAndNumber.secondFieldValue)
                Spacer()
            }
        }
    }
}

struct DisplayingStreetAndHouseNumberView_Previews: PreviewProvider {
    @State static var streetAndNumber = TwoFieldsWithLabels(firstLabel: "Street", firstFieldValue: "Loop", secondLabel: "No.", secondFieldValue: "1")
   
    static var previews: some View {
        TwoFieldsWithLabelsView(isEditing: false, twoFields: streetAndNumber)
            .padding()
    }
}
struct EditingStreetAndHouseNumberView_Previews: PreviewProvider {
    @State static var streetAndNumber = TwoFieldsWithLabels(widthOfFirstField: nil, firstLabel: "Street", firstFieldValue: "Loop", widthOfSecondField: 48, secondLabel: "No.", secondFieldValue: "1")

    static var previews: some View {
        TwoFieldsWithLabelsView(isEditing: true, twoFields: streetAndNumber)
            .padding()
    }
}
