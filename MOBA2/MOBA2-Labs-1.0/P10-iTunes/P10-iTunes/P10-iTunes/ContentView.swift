//
//  ContentView.swift
//  MOBA2 - P10-iTunes
//
//  Created by Gabriele Pace (pacegab1) & Timothé Laborie (labortim)
//

import SwiftUI

struct Music: Identifiable, Decodable {
    var artistName : String?
    var collectionName : String?
    var collectionId : Int?
    var collectionType : String?
    var id: Int {
        get {
            return collectionId ?? 0
        }
    }
}

struct MusicWrapper: Decodable {
    var results: [Music]
}
    
struct ContentView: View {
    @State var musicList = [Music] ()
    
    var body: some View {
        List(musicList) { music in
            VStack(alignment: .leading) {
                Text(music.collectionName!)
                Text(music.artistName!).font(.footnote)
            }
        }.onAppear {
            DispatchQueue.main.async {
                self.musicList = loadJSON()
            }
        }
    }
}

func loadJSON() -> [Music] {
    do {
        let file = Bundle.main.url(forResource: "itunes", withExtension: "json")
        let data = try Data(contentsOf: file!)
        let decoder = JSONDecoder()
        let decodedData = try decoder.decode(MusicWrapper.self, from: data)
        return decodedData.results.filter({ return $0.collectionType != nil})
        } catch {
            fatalError("Couldn't load itunes.json from main bundle\n\(error)")
        }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
