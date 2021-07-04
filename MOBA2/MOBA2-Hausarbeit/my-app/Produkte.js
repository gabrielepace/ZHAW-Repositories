import React from 'react';
import { StyleSheet, ScrollView } from 'react-native';
import { ListItem, Avatar, Text } from 'react-native-elements'

const Produkte = () => {
    const list =
        [
            {
                "productId": "GV7549",
                "displayName": "NMD_R1 Schuh",
                "altText": "Originals NMD_R1 Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GV7549-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GV7549-2.jpg",
                "price": 140,
                "color": "Non Dyed / Chalk White / Active Teal"
            },
            {
                "productId": "FY5168",
                "displayName": "ZX 8000 Schuh",
                "altText": "Originals ZX 8000 Schuh Beige",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY5168-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY5168-2.jpg",
                "price": 150,
                "color": "Linen / Brown / Tech Copper"
            },
            {
                "productId": "GX0540",
                "displayName": "NMD_R1 V2 Schuh",
                "altText": "Originals NMD_R1 V2 Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GX0540-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GX0540-2.jpg",
                "price": 160,
                "color": "Core Black / Cloud White / Grey One"
            },
            {
                "productId": "FY9121",
                "displayName": "Ultraboost DNA 4.0 Laufschuh",
                "altText": "Running Ultraboost DNA 4.0 Laufschuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9121-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9121-2.jpg",
                "price": 180,
                "color": "Core Black / Core Black / Active Red"
            },
            {
                "productId": "EG4958",
                "displayName": "Superstar Schuh",
                "altText": "Originals Superstar Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EG4958-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EG4958-2.jpg",
                "price": 120,
                "color": "Cloud White / Core Black / Cloud White"
            },
            {
                "productId": "EE6464",
                "displayName": "OZWEEGO Schuh",
                "altText": "Männer Originals OZWEEGO Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE6464-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE6464-2.jpg",
                "price": 140,
                "color": "Cloud White / Cloud White / Core Black"
            },
            {
                "productId": "FY7939",
                "displayName": "Forum Mid Schuh",
                "altText": "Originals Forum Mid Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY7939-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY7939-2.jpg",
                "price": 130,
                "color": "Cloud White / Core Black / Cloud White"
            },
            {
                "productId": "FX6835",
                "displayName": "Nite Jogger Schuh",
                "altText": "Originals Nite Jogger Schuh Grau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX6835-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX6835-2.jpg",
                "price": 150,
                "color": "Dash Grey / Core Black / Halo Silver"
            },
            {
                "productId": "FX5501",
                "displayName": "Stan Smith Schuh",
                "altText": "Originals Stan Smith Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5501-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5501-2.jpg",
                "price": 120,
                "color": "Cloud White / Cloud White / Collegiate Navy"
            },
            {
                "productId": "G27706",
                "displayName": "Continental 80 Schuh",
                "altText": "Originals Continental 80 Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G27706-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G27706-2.jpg",
                "price": 120,
                "color": "Cloud White / Scarlet / Collegiate Navy"
            },
            {
                "productId": "GZ0525",
                "displayName": "Gazelle Schuh",
                "altText": "Originals Gazelle Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GZ0525-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GZ0525-2.jpg",
                "price": 110,
                "color": "Non Dyed / Chalk White / Core Black"
            },
            {
                "productId": "GX0544",
                "displayName": "NMD_R1 V2 Schuh",
                "altText": "Originals NMD_R1 V2 Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GX0544-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GX0544-2.jpg",
                "price": 160,
                "color": "Cloud White / Core Black / Bold Blue"
            },
            {
                "productId": "FY5172",
                "displayName": "ZX 9000 Schuh",
                "altText": "Originals ZX 9000 Schuh Grau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY5172-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY5172-2.jpg",
                "price": 150,
                "color": "Clear Granite / Dash Green / Charcoal Solid Grey"
            },
            {
                "productId": "FY5173",
                "displayName": "ZX 10,000 Schuh",
                "altText": "Originals ZX 10,000 Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY5173-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY5173-2.jpg",
                "price": 150,
                "color": "Crystal White / Collegiate Navy / Dark Marine"
            },
            {
                "productId": "B22705",
                "displayName": "3MC Vulc Schuh",
                "altText": "Originals 3MC Vulc Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/B22705-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/B22705-2.jpg",
                "price": 75,
                "color": "Cloud White / Cloud White / Gold Met."
            },
            {
                "productId": "D96635",
                "displayName": "NMD_R1 Schuh",
                "altText": "Originals NMD_R1 Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/D96635-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/D96635-2.jpg",
                "price": 160,
                "color": "White / Gum / Crystal White"
            },
            {
                "productId": "FW2770",
                "displayName": "TERREX Swift R3 GORE-TEX Wanderschuh",
                "altText": "TERREX TERREX Swift R3 GORE-TEX Wanderschuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FW2770-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FW2770-2.jpg",
                "price": 170,
                "color": "Core Black / Grey One / Solar Yellow"
            },
            {
                "productId": "FW2769",
                "displayName": "TERREX Swift R3 GORE-TEX Wanderschuh",
                "altText": "Männer TERREX TERREX Swift R3 GORE-TEX Wanderschuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FW2769-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FW2769-2.jpg",
                "price": 170,
                "color": "Core Black / Grey Three / Solar Red"
            },
            {
                "productId": "FV9021",
                "displayName": "NMD_R1 V2 Schuh",
                "altText": "Originals NMD_R1 V2 Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FV9021-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FV9021-2.jpg",
                "price": 160,
                "color": "Core Black / Cloud White / Core Black"
            },
            {
                "productId": "FY9349",
                "displayName": "Ultraboost DNA 5.0 Laufschuh",
                "altText": "Running Ultraboost DNA 5.0 Laufschuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9349-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9349-2.jpg",
                "price": 180,
                "color": "Cloud White / Cloud White / Core White"
            },
            {
                "productId": "FY9120",
                "displayName": "Ultraboost DNA 4.0 Laufschuh",
                "altText": "Running Ultraboost DNA 4.0 Laufschuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9120-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9120-2.jpg",
                "price": 180,
                "color": "Cloud White / Cloud White / Core Black"
            },
            {
                "productId": "GZ1014",
                "displayName": "Nizza RF Schuh",
                "altText": "Originals Nizza RF Schuh Blau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GZ1014-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GZ1014-2.jpg",
                "price": 100,
                "color": "Blue / Off White / Clear Mint"
            },
            {
                "productId": "G27639",
                "displayName": "U_Path Run Schuh",
                "altText": "Originals U_Path Run Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G27639-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G27639-2.jpg",
                "price": 110,
                "color": "Core Black / Ash Grey / Core Black"
            },
            {
                "productId": "CM7492",
                "displayName": "TERREX Swift R2 GORE-TEX Wanderschuh",
                "altText": "TERREX TERREX Swift R2 GORE-TEX Wanderschuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/CM7492-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/CM7492-2.jpg",
                "price": 160,
                "color": "Core Black / Core Black / Core Black"
            },
            {
                "productId": "FY9319",
                "displayName": "Ultraboost DNA 4.0 Laufschuh",
                "altText": "Running Ultraboost DNA 4.0 Laufschuh Grau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9319-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9319-2.jpg",
                "price": 180,
                "color": "Grey Three / Grey Three / Core Black"
            },
            {
                "productId": "FX5502",
                "displayName": "Stan Smith Schuh",
                "altText": "Originals Stan Smith Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5502-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5502-2.jpg",
                "price": 120,
                "color": "Cloud White / Cloud White / Green"
            },
            {
                "productId": "G27637",
                "displayName": "U_Path Run Schuh",
                "altText": "Originals U_Path Run Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G27637-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G27637-2.jpg",
                "price": 110,
                "color": "Cloud White / Cloud White / Core Black"
            },
            {
                "productId": "FX5500",
                "displayName": "Stan Smith Schuh",
                "altText": "Originals Stan Smith Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5500-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5500-2.jpg",
                "price": 120,
                "color": "Cloud White / Cloud White / Core Black"
            },
            {
                "productId": "GY4977",
                "displayName": "Pharrell Williams NMD_R1 Schuh",
                "altText": "Originals Pharrell Williams NMD_R1 Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GY4977-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GY4977-2.jpg",
                "price": 180,
                "color": "Core Black / Core Black / Core Black"
            },
            {
                "productId": "EE6037",
                "displayName": "Supercourt Schuh",
                "altText": "Originals Supercourt Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE6037-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE6037-2.jpg",
                "price": 110,
                "color": "Cloud White / Cloud White / Core Black"
            },
            {
                "productId": "FY9318",
                "displayName": "Ultraboost DNA 4.0 Laufschuh",
                "altText": "Running Ultraboost DNA 4.0 Laufschuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9318-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9318-2.jpg",
                "price": 180,
                "color": "Core Black / Core Black / Cloud White"
            },
            {
                "productId": "GZ7039",
                "displayName": "Samba OG Schuh",
                "altText": "Originals Samba OG Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GZ7039-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GZ7039-2.jpg",
                "price": 110,
                "color": "Cloud White / Royal Blue / Cloud White"
            },
            {
                "productId": "FW2336",
                "displayName": "Continental 80 Vegan Schuh",
                "altText": "Originals Continental 80 Vegan Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FW2336-1.jpg",
                "image2": "FW2336-2.jpg",
                "price": 120,
                "color": "Cloud White / Collegiate Navy / Scarlet"
            },
            {
                "productId": "H05246",
                "displayName": "Ultraboost DNA 4.0 Laufschuh",
                "altText": "Running Ultraboost DNA 4.0 Laufschuh Blau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/H05246-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/H05246-2.jpg",
                "price": 180,
                "color": "Crew Navy / Crew Navy / Cloud White"
            },
            {
                "productId": "FY9338",
                "displayName": "Ultraboost DNA 4.0 Laufschuh",
                "altText": "Running Ultraboost DNA 4.0 Laufschuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9338-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9338-2.jpg",
                "price": 180,
                "color": "Cloud White / Collegiate Green / Core Black"
            },
            {
                "productId": "GV7538",
                "displayName": "Stan Smith Schuh",
                "altText": "Originals Stan Smith Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GV7538-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/GV7538-2.jpg",
                "price": 120,
                "color": "Chalk White / Non Dyed / Bright Cyan"
            },
            {
                "productId": "FZ0145",
                "displayName": "ZX 420 Schuh",
                "altText": "Originals ZX 420 Schuh Blau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FZ0145-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FZ0145-2.jpg",
                "price": 140,
                "color": "Collegiate Navy / Off White / Grey Five"
            },
            {
                "productId": "FX5666",
                "displayName": "München Schuh",
                "altText": "Originals München Schuh Blau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5666-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5666-2.jpg",
                "price": 120,
                "color": "Collegiate Navy / Core White / Gold Metallic"
            },
            {
                "productId": "EE8889",
                "displayName": "Five Ten Trailcross LT Mountainbiking-Schuh",
                "altText": "Five Ten Five Ten Trailcross LT Mountainbiking-Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE8889-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE8889-2.jpg",
                "price": 150,
                "color": "Core Black / Grey Two / Solar Red"
            },
            {
                "productId": "EE8893",
                "displayName": "Five Ten Sleuth Mountainbiking-Schuh",
                "altText": "Five Ten Five Ten Sleuth Mountainbiking-Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE8893-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE8893-2.jpg",
                "price": 100,
                "color": "Core Black / Core Black / Gum M2"
            },
            {
                "productId": "BB5478",
                "displayName": "Gazelle Schuh",
                "altText": "Originals Gazelle Schuh Blau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/BB5478-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/BB5478-2.jpg",
                "price": 110,
                "color": "Collegiate Navy / White / Gold Metallic"
            },
            {
                "productId": "FY9354",
                "displayName": "Ultraboost DNA 5.0 Laufschuh",
                "altText": "Running Ultraboost DNA 5.0 Laufschuh Grau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9354-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FY9354-2.jpg",
                "price": 180,
                "color": "Grey Three / Grey Three / Core Black"
            },
            {
                "productId": "FX5522",
                "displayName": "Stan Smith Schuh",
                "altText": "Originals Stan Smith Schuh Weiß",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5522-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/FX5522-2.jpg",
                "price": 120,
                "color": "Cloud White / Collegiate Green / Off White"
            },
            {
                "productId": "G55574",
                "displayName": "NMD_R1 Schuh",
                "altText": "Originals NMD_R1 Schuh Blau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G55574-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/G55574-2.jpg",
                "price": 160,
                "color": "Collegiate Navy / Cloud White / Gum"
            },
            {
                "productId": "B22713",
                "displayName": "3MC Vulc Schuh",
                "altText": "Originals 3MC Vulc Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/B22713-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/B22713-2.jpg",
                "price": 75,
                "color": "Core Black / Core Black / Grey Two"
            },
            {
                "productId": "CM7500",
                "displayName": "TERREX Swift R2 Mid GORE-TEX Wanderschuh",
                "altText": "TERREX TERREX Swift R2 Mid GORE-TEX Wanderschuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/CM7500-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/CM7500-2.jpg",
                "price": 180,
                "color": "Core Black / Core Black / Core Black"
            },
            {
                "productId": "EG4959",
                "displayName": "Superstar Schuh",
                "altText": "Originals Superstar Schuh Schwarz",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EG4959-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EG4959-2.jpg",
                "price": 120,
                "color": "Core Black / Cloud White / Core Black"
            },
            {
                "productId": "EE8943",
                "displayName": "Gazelle Schuh",
                "altText": "Originals Gazelle Schuh Grau",
                "image1": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE8943-1.jpg",
                "image2": "https://dublin.zhaw.ch/~pacegab1/moba2/hausarbeit-sep/sneakers/EE8943-2.jpg",
                "price": 120,
                "color": "Grey Four / Core Black / Gum5"
            }
        ]

    return (
        <ScrollView style={styles.container}>
            <Text h3 style={styles.h3_title}>Guten Tag Peter Muster</Text>
            {
                list.map((l, i) => (
                    <ListItem key={i} bottomDivider>
                        <Avatar source={{ uri: l.image1 }} />
                        <ListItem.Content>
                            <ListItem.Title>{l.displayName}</ListItem.Title>
                            <ListItem.Subtitle>{l.altText}</ListItem.Subtitle>
                            <Text style={styles.price}>CHF {l.price}</Text>
                        </ListItem.Content>
                    </ListItem>
                ))
            }
        </ScrollView>
    )

};

const styles = StyleSheet.create({
    container: {
        marginTop: 60,
        flex: 1,
        flexDirection: 'column'
    },
    h3_title: {
        marginBottom: 20,
        marginLeft: 60
    },
    price: {
        fontWeight: 'bold',
        fontSize: 20
    }
});

export default Produkte;