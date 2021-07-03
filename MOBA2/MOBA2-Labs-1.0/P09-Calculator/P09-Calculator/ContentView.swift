//
//  ContentView.swift
//  MOBA2 - P09-Calculator
//
//  Created by Gabriele Pace (pacegab1) & Timothé Laborie (labortim)
//

import SwiftUI

struct CalculationButton : View {
    var text : String
    enum ButtonType {
        case operation
        case number
        case change
    }
    var type = ButtonType.number
    
    let colors : [ButtonType : Color] = [
        ButtonType.number : Color.white,
        ButtonType.operation : Color.orange,
        ButtonType.change : Color.green
    ]
    
    var action : () -> Void = {}
    
    var body: some View {
        Button(action: self.action, label: {
            VStack {
                Spacer()
                HStack {
                    Spacer()
                    Text(self.text).font(Font.system(size: 40)).foregroundColor(.black)
                    Spacer()
                }
                Spacer()
            }
        })
        .background(self.colors[self.type])
        .border(Color.gray, width: 1)
        .clipShape(RoundedRectangle(cornerRadius: 10))
    }
}

struct ContentView: View {
    @State var fieldNumber : String = "0"
    @State var number1 : Int?
    @State var operation1 : String?
    @State var geometry : Int = 0
    
    func number(_ item : String) {
        if (self.fieldNumber.count > 9) {
            // do nothing
        }
        else if (self.fieldNumber == "0") {
            self.fieldNumber = item
        }
        else {
            self.fieldNumber += item
        }
    }
    
    func change (_ item : String) {
        if (item == "AC") {
            self.fieldNumber = "0"
        }
    }
    
    func operation (_ item : String) {
        if (item != "=") {
            self.operation1 = item
            self.number1 = Int(self.fieldNumber)
            self.fieldNumber = "0"
        }
        else if (self.operation1 != nil && self.number1 != nil) {
            if (self.operation1 == "*") {
                self.fieldNumber = String(Int(self.fieldNumber)! * self.number1!)
            }
        }
    }
    
    var body: some View {
        VStack {
            HStack {
                Spacer()
                Text(self.fieldNumber).foregroundColor(.black).font(Font.system(size: 60)).padding(EdgeInsets(top: 20, leading: 2, bottom: 20, trailing: 2))
            }.background(Color.white)
            
            HStack {
                CalculationButton(
                    text: "AC",
                    type: CalculationButton.ButtonType.change,
                    action: {
                        self.change("AC")
                    })
                CalculationButton(
                    text: "+/-",
                    type: CalculationButton.ButtonType.change,
                    action: {
                        self.change("+-")
                    })
                CalculationButton(
                    text: "%",
                    type: CalculationButton.ButtonType.change,
                    action: {
                        self.change("%")
                    }
                )
                CalculationButton(
                    text: "/",
                    type: CalculationButton.ButtonType.operation,
                    action: {
                        self.change("/")
                    })
            }
            HStack() {
                CalculationButton(
                    text: "7",
                    action: {
                        self.number("7")
                    })
                CalculationButton(
                    text: "8",
                    action: {
                        self.number("8")
                    })
                CalculationButton(
                    text: "9",
                    action: {
                        self.number("9")
                    })
                CalculationButton(
                    text: "*",
                    type: CalculationButton.ButtonType.operation,
                    action: {
                        self.operation("*")
                    })
            }
            HStack() {
                CalculationButton(
                    text: "4",
                    action: {
                        self.number("4")
                    })
                CalculationButton(
                    text: "5",
                    action: {
                        self.number("5")
                    })
                CalculationButton(
                    text: "6",
                    action: {
                        self.number("6")
                    })
                CalculationButton(
                    text: "-",
                    type: CalculationButton.ButtonType.operation,
                    action: {
                        self.operation("-")
                    })
            }
            HStack() {
                CalculationButton(
                    text: "1",
                    action: {
                        self.number("1")
                    })
                CalculationButton(
                    text: "2",
                    action: {
                        self.number("2")
                    })
                CalculationButton(
                    text: "3",
                    action: {
                        self.number("3")
                    })
                CalculationButton(
                    text: "+",
                    type: CalculationButton.ButtonType.operation,
                    action: {
                        self.operation("+")
                    })
            }
            GeometryReader { geometry in
                HStack {
                    CalculationButton(text: "0")
                    CalculationButton(text: ".")
                    CalculationButton(
                        text: "=",
                        type: CalculationButton.ButtonType.operation,
                        action: {
                            self.operation("=")
                        }).frame(width: geometry.size.width / 2)
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
