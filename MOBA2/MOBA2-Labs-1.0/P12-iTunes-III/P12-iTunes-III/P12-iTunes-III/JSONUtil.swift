//
//  JSONUtil.swift
//  MOBA2 - P12-iTunes-III
//
//  Created by Gabriele Pace (pacegab1) & Timothé Laborie (labortim)
//

import Foundation

class JSONUtil {
    class func readAlbumsToJSON(searchTerm: String) -> [Album] {
        do {
            let apiUrlString = "https://itunes.apple.com/search?term=" +
                searchTerm + "&entity=album"
            let encodedUrl = apiUrlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? ""
            let url = URL(string: encodedUrl)!
            let data = try Data(contentsOf: url)
            let decoder = JSONDecoder()
            let decodedData = try decoder.decode(AlbumWrapper.self, from: data)
            return decodedData.results.filter({ return $0.collectionType != nil})
        } catch {
            fatalError("Couldn't load url from main bundle\n\(error)")
        }
    }
    
    class func readSongsToJSON(collectionId: Int) -> [Track] {
           do {
               let apiUrlString = "https://itunes.apple.com/lookup?id=" +
                         String(collectionId) + "&entity=song"
               print(apiUrlString)
               let encodedUrl = apiUrlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? ""
               let url = URL(string: encodedUrl)!
               let data = try Data(contentsOf: url)
               let decoder = JSONDecoder()
               let decodedData = try decoder.decode(TrackWrapper.self, from: data)
               return decodedData.results.filter({ return $0.wrapperType != "collection"})
           } catch {
               fatalError("Couldn't load url from main bundle\n\(error)")
           }
    }
    
    class func readSingleAlbumToJSON(collectionId: Int) -> Album {
        do {
            let apiUrlString = "https://itunes.apple.com/lookup?id=" +
                      String(collectionId) + "&entity=song"
            print(apiUrlString)
            let encodedUrl = apiUrlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? ""
            let url = URL(string: encodedUrl)!
            let data = try Data(contentsOf: url)
            let decoder = JSONDecoder()
            let decodedData = try decoder.decode(AlbumWrapper.self, from: data)
            return decodedData.results.filter({ return $0.collectionType != nil})[0]
        } catch {
            fatalError("Couldn't load url from main bundle\n\(error)")
        }
    }
}
