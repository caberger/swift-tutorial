

import SwiftUI

struct AddressFormLine: View {
    var isEditing: Bool
    var label: String
    var text: String
    
    var body: some View {
        HStack(alignment: .center) {
            if isEditing {
                VStack {
                    TextField(label, text: Binding(
                        get: {
                            text
                        },
                        set: {print("new Value \($0)")}
                    ))
                    /*
                    TextField(label, text: $text, onEditingChanged: { isBegin in
                        if !isBegin {
                            print("done: \(text)")
                        }
                    },
                    onCommit: {
                        print("commit \(text)")
                    })
                    
                    //Text("debug: \(text)")
                     */
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
        AddressFormLine(isEditing: false, label: "Vorname", text: text)
            .padding()
    }
}

struct AddressFormLineEditing_Previews: PreviewProvider {
    @State static var text = "Mustermann"
    static var previews: some View {
        AddressFormLine(isEditing: true, label: "Vorname", text: text)
            .padding()
    }
}
