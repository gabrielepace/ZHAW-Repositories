//
//  JSONUtil.swift
//  MOBA2 - P11-iTunes-II
//
//  Created by Gabriele Pace (pacegab1) & Timothé Laborie (labortim)
//

import Foundation

class JSONUtil {
    class func readJSON(searchTerm: String) -> [Album] {
        do {
            // let file = Bundle.main.url(forResource: "itunes", withExtension: "json")
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
}
