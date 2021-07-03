import React, { useContext } from 'react';
import { StyleSheet, View, Text, TextInput, ImageBackground, Platform } from 'react-native';

import Forecast from './Forecast';
import { WeatherContext } from '../contexts/WeatherContext';

const WeatherProject = () => {
  const { forecast, dispatch } = useContext(WeatherContext);
  const handleTextChange = (event) => {
    let location = event.nativeEvent.text;
    console.log("The entered location: " + location);

    // API Key: 405838bcb8d3d2abd68831c43a7bc438
    // Name: MOBA2-P06-StateManag
    // User Account: pacegab1
    fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=${location}&APPID=405838bcb8d3d2abd68831c43a7bc438`
    )
      .then(response => response.json())
      .then(data => {
        if (!data || !data.weather) {
          console.error("No data found in response from API");
          return;
        }
        let temp = (data.main.temp - 273.15).toFixed(2);
        let description = data.weather[0].description;
        let main = data.weather[0].main;
        dispatch({ type: 'ADD_FORECAST', forecast: { 'temp': temp, 'description': description, 'main': main } });
      })
      .catch(error => {
        console.log("Error retrieving weather data from API, reason: " + error);
      });
  };

  return (
    <View style={styles.container}>
      <ImageBackground
        source={getImagePath(forecast.main)}
        imageStyle={{ resizeMode: 'cover' }}
        style={styles.backdrop}>
        <View style={styles.overlay}>
          <View style={styles.row}>
            <Text style={styles.mainText}>Current weather for</Text>
            <View style={styles.locationContainer}>
              <TextInput
                style={[styles.locationCode, styles.mainText]}
                onSubmitEditing={handleTextChange.bind(this)}
              />
            </View>
          </View>
          <Forecast
          />
        </View>
      </ImageBackground>
    </View>
  );
};

const getImagePath = (main) => {
  let path;
  switch (main) {
    case 'Clouds':
      path = require('./../resources/clouds.jpg');
      break;
    case 'Clear':
      path = require('./../resources/clear.jpg');
      break;
    case 'Snow':
      path = require('./../resources/snow.jpg');
      break;
    case 'Rain':
      path = require('./../resources/rain.jpg');
      break;
    default:
      path = require('./../resources/clear.jpg');
      break;
  }
  return path;
};

var baseFontSize = 16;


const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
  },
  backdrop: {
    paddingTop: Platform.OS === 'ios' ? 50 : 0,
    flex: 1,
    alignSelf: 'stretch',
  },
  overlay: {
    paddingTop: 5,
    backgroundColor: '#000000',
    opacity: 0.5,
    flexDirection: 'column',
    alignItems: 'center',
  },
  mainText: {
    fontSize: baseFontSize,
    color: '#FFFFFF',
  },
  locationContainer: {
    borderBottomColor: '#DDDDDD',
    borderBottomWidth: 1,
    marginLeft: 5,
    marginTop: 3,
    marginBottom: 15,
  },
  locationCode: {
    width: 100,
    height: baseFontSize + 25,
  },
});

export default WeatherProject;