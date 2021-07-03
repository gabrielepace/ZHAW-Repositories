//
//  ContentView.swift
//  MOBA2 - P11-iTunes-II
//
//  Created by Gabriele Pace (pacegab1) & Timothé Laborie (labortim)
//

import SwiftUI

struct Album: Identifiable, Decodable {
    var artistName : String?
    var collectionName : String?
    var collectionId : Int?
    var collectionType : String?
    var artworkUrl60: String?
    var artworkUrl100 : String?
    var id: Int {
        get {
            return collectionId ?? 0
        }
    }
    
    var album : UIImage? {
        get {
            return loadUIImage(urlS: self.artworkUrl60)
        }
    }
    
    func loadUIImage(urlS: String?) -> UIImage? {
        if urlS != nil {
            let url = NSURL(string: urlS!)! as URL
            if let imageData: NSData = NSData(contentsOf: url) {
                let image = UIImage(data: imageData as Data)
                return image
            }
        }
        
        return nil
    }
}

struct AlbumWrapper: Decodable {
    var results: [Album]
}
    
struct ContentView: View {
    @State var albums = [Album] ()
    @State var searchText : String = "Jack Johnson"
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "magnifyingglass")
                TextField("Search", text: $searchText, onCommit: {
                    self.albums = JSONUtil.readJSON(searchTerm: self.searchText)
                }).foregroundColor(.primary)
         }.padding().foregroundColor(.secondary).background(Color(.secondarySystemBackground)).cornerRadius(10.0)
            List(albums) { album in
                HStack {
                    Image(uiImage: album.album ?? UIImage()).shadow(radius: 2)
                    VStack {
                        Text(album.collectionName!).frame(maxWidth: .infinity, alignment: .leading)
                        Text(album.artistName!).font(.footnote).frame(maxWidth: .infinity, alignment: .leading)
                        }
                }
               
            }.onAppear {
                DispatchQueue.main.async {
                    self.albums = JSONUtil.readJSON(searchTerm: self.searchText)
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
