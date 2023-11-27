

import SwiftUI

struct AddressFormLine: View {
    var isEditing: Bool
    var label: String
    var text: String
    var onTextChanged: (String) -> Void
    var body: some View {
        HStack(alignment: .center) {
            if isEditing {
                VStack {
                    TextField(label, text: Binding(
                        get: { text },
                        set: {
                            if $0 != text {
                                onTextChanged($0)
                            }
                        }
                    ))
                }
                .font(.title).border(/*@START_MENU_TOKEN@*/Color.gray/*@END_MENU_TOKEN@*/, width: /*@START_MENU_TOKEN@*/1/*@END_MENU_TOKEN@*/)
            } else {
                Text("\(label):")
                Spacer()
                Text(text)
                    .font(.title)
            }
        }
        
    }
}

struct AddressFormLineDisplaying_Previews: PreviewProvider {
    @State static var text = "Mustermann"
    static var previews: some View {
        AddressFormLine(isEditing: false, label: "Vorname", text: text) {
            print($0)
        }
        .padding()
    }
}

struct AddressFormLineEditing_Previews: PreviewProvider {
    @State static var text = "Mustermann"
    static var previews: some View {
        AddressFormLine(isEditing: true, label: "Vorname", text: text) {
            print($0)
        }
        .padding()
    }
}
