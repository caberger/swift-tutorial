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
    var twoFields = TwoFieldsWithLabels()
    var textsChanged: (String?, String?) -> Void
    var body: some View {
        HStack {
            if isEditing {
                EditingTwoFieldsView(twoFields: twoFields, cb: { first, second in
                    textsChanged(first, second)
                })
            } else {
                DisplayingTwoFieldsView(streetAndNumber: twoFields)
                }
        }.font(.title)
    }
}

struct EditingTwoFieldsView : View {
    var twoFields = TwoFieldsWithLabels()
    var cb: (String?, String?) -> Void
    var body: some View {
        TextField(twoFields.firstLabel, text: Binding(
            get: {
                twoFields.firstFieldValue
            },
            set: {
                if $0 != twoFields.firstFieldValue {
                    cb($0, nil)
                }
            }
        )
        )
        .border(/*@START_MENU_TOKEN@*/Color.gray/*@END_MENU_TOKEN@*/, width: /*@START_MENU_TOKEN@*/1/*@END_MENU_TOKEN@*/)
        .frame(width: twoFields.widthOfFirstField != nil ? twoFields.widthOfFirstField : nil)
        TextField(twoFields.secondLabel, text: Binding(
            get: {
                twoFields.secondFieldValue
            },
            set: {
                if $0 != twoFields.secondFieldValue {
                    cb(nil, $0)
                }
                    
            }
        ))
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
        TwoFieldsWithLabelsView(isEditing: false, twoFields: streetAndNumber, textsChanged:  { street, no in
            print("street and no chnaged")
            
        })
        .padding()
    }
}
struct EditingStreetAndHouseNumberView_Previews: PreviewProvider {
    @State static var streetAndNumber = TwoFieldsWithLabels(widthOfFirstField: nil, firstLabel: "Street", firstFieldValue: "Loop", widthOfSecondField: 48, secondLabel: "No.", secondFieldValue: "1")

    static var previews: some View {
        TwoFieldsWithLabelsView(isEditing: true, twoFields: streetAndNumber, textsChanged:  { street, no in
            print("street + no changed")
        })
        .padding()
    }
}
