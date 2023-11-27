struct AddressModel {
    private (set) var firstName = ""
    private (set) var lastName = ""
    private (set) var street = ""
    private (set) var houseNumber = ""
    private (set) var zipCode = ""
    private (set) var city = ""

    var isValid: Bool {
        lastName.count >= 2 && zipCode.count >= 4 && city.count >= 2
    }
    mutating func dataChanged(
        firstName: String?,
        lastName: String?,
        street: String?,
        houseNumber: String?,
        zipCode: String?,
        city: String?
    )
    {
        if let val = firstName {
            self.firstName = val
        }
        if let val = lastName {
            self.lastName = val
        }
        if let val = street {
            self.street = val
        }
        if let val = houseNumber {
            self.houseNumber = val
        }
        if let val = zipCode {
            self.zipCode = val
        }
        if let val = city {
            self.city = val
        }
    }
}
