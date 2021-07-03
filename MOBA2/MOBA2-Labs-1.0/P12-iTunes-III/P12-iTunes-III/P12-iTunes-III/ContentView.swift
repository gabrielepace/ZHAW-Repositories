//
//  ContentView.swift
//  MOBA2 - P12-iTunes-III
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
    var releaseDate: String?
    var prettyDate: String? {
        get {
                 return String(releaseDate!.prefix(4))
             }
    }
    
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
  
    var largeAlbum : UIImage? {
          get {
            let largeURL =
                self.artworkUrl100?.replacingOccurrences(of: "100x100bb", with: "256x256bb")
              return loadUIImage(urlS: largeURL)
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

struct Track: Identifiable, Decodable {
    var trackId: Int?
    var wrapperType: String?
    var trackName: String?
    var trackNumber: Int?
    var trackTimeMillis: Int?
    var id: Int {
        get {
            return trackId ?? 0
        }
    }
       
    
    var time : String {
        get {
            let cseconds = trackTimeMillis! / 1000
            let minutes = cseconds / 60
            let seconds = cseconds / 60
            var secondss = String(seconds)
            if secondss.count == 1 {
                secondss = "0" + secondss
            }
            return String(minutes) + ":" + secondss
        }
    }
}

struct TrackWrapper: Decodable {
    var results: [Track]
}

struct AlbumDetailsView: View {
    var collectionId: Int
    @State var album: Album
    @State var tracks = [Track] ()
    
    var body: some View {
        VStack(alignment: .center) {
            Text(album.collectionName!).font(.title)
            Text(album.prettyDate!).font(.subheadline)
            Image(uiImage: album.largeAlbum ?? UIImage())
            List(tracks) { track in
                HStack {
                    Text(String(track.trackNumber!))
                    Text(track.trackName!)
                    Spacer()
                    Text(String(track.time    ))
                }
            }
        }
    }
}
    
struct ContentView: View {
    @State var albums = [Album]()
    @State var searchText : String = "Jack Johnson"
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "magnifyingglass")
                TextField("Search", text: $searchText, onCommit: {
                    self.albums = JSONUtil.readAlbumsToJSON(searchTerm: self.searchText)
                }).foregroundColor(.primary)
         }.padding().foregroundColor(.secondary).background(Color(.secondarySystemBackground)).cornerRadius(10.0)
            NavigationView {
                List(albums) { album in
                    NavigationLink(destination: AlbumDetailsView(collectionId: album.id, album: JSONUtil.readSingleAlbumToJSON(collectionId: album.id), tracks: JSONUtil.readSongsToJSON(collectionId: album.id))) {
                        HStack {
                            Image(uiImage: album.album ?? UIImage()).shadow(radius: 2)
                            VStack {
                                Text(album.collectionName!).frame(maxWidth: .infinity, alignment: .leading)
                                Text(album.artistName!).frame(maxWidth: .infinity, alignment: .leading)
                                }
                        }
                    }
                   
                }.onAppear {
                    DispatchQueue.main.async {
                        self.albums = JSONUtil.readAlbumsToJSON(searchTerm: self.searchText)
                    }
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
