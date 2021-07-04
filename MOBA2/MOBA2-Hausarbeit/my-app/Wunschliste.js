import React from 'react';
import { StyleSheet, ScrollView } from 'react-native';
import { ListItem, Avatar, Text } from 'react-native-elements'

const Wunschliste = () => {
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
            }
        ]

    return (
        <ScrollView style={styles.container}>
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
            <Text style={styles.totalPrice}>Total: 750 CHF</Text>
        </ScrollView>
    )

};

const styles = StyleSheet.create({
    container: {
        marginTop: 60,
        flex: 1,
        flexDirection: 'column'
    },
    price: {
        fontWeight: 'bold', 
        fontSize: 20
    },
    totalPrice: {
        marginTop: 50,
        marginLeft: 20,
        fontWeight: 'bold', 
        fontSize: 30
    }
});

export default Wunschliste;