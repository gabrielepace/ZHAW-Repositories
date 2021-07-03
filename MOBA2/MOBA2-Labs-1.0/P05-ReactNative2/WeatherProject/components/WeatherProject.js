'use strict';

import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  ImageBackground,
  Platform
} from 'react-native';
import Forecast from './Forecast';

class WeatherProject extends Component {

  constructor (props) {
    super(props);
    this.state = {
      location: '',
      forecast: {   }
    };
  }

  handleTextChange (event) {
    let location = event.nativeEvent.text;
    
    // API Key: c80c5a470be5b57e68b157d9abfe4629
    // Name: MOBA2-P05-RNative2
    // User Account: pacegab1
    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${location}&APPID=c80c5a470be5b57e68b157d9abfe4629`)
      .then((response) => response.json())
      .then((data) => {
        if(!data) {
          return;
        }
        let temp = (data.main.temp - 273.15).toFixed(2);
        let description = data.weather[0].description;
        let main = data.weather[0].main;
        this.setState({forecast: {'temp': temp, 'description': description, 'main': main}});
      })
      .catch((error) => {
        console.error('error retrieving weather data, reason: ' + error);
      });    
  }

  getImagePath() {
    let path;
    switch(this.state.forecast.main) {
      case "Clouds":
      path = require("./../resources/clouds.jpg");
      break;
      case "Clear":
      path = require("./../resources/clear.jpg");
      break;
      case "Snow":
      path = require("./../resources/snow.jpg");
      break;
      case "Rain":
      path = require("./../resources/rain.jpg");
      break;
      default:
      path = require("./../resources/clear.jpg");
      break;
    }
    return path;
  }

  render () { 
    let content = undefined;
    if (this.state.forecast !== undefined) {
      content = <Forecast 
                  style={styles.forecast}
                  main={this.state.forecast.main}
                  description={this.state.forecast.description}
                  temp={this.state.forecast.temp} />;
    }
    let imagePath = this.getImagePath();
    return (
      <View style={styles.container}>
        <ImageBackground source={imagePath}
               imageStyle={{resizeMode:'cover'}}
               style={styles.backdrop}>
          <View style={styles.overlay}>
           <View style={styles.row}>
             <Text style={styles.mainText}>
               Current weather for 
             </Text>
             <View style={styles.locationContainer}>
               <TextInput
                 style={[styles.locationCode, styles.mainText]}
                 onSubmitEditing={this.handleTextChange.bind(this)}/>
             </View>
           </View>
           {content}
         </View>
        </ImageBackground>
      </View>
    );
  }

}


var baseFontSize = 16;

const styles = StyleSheet.create({ 
  container: {
    flex: 1,
    alignItems: 'center',
  },
  backdrop: {
    paddingTop: (Platform.OS === 'ios') ? 50 : 0,
    flex: 1,
    alignSelf: 'stretch',
  },
  overlay: {
    paddingTop: 5,
    backgroundColor: '#000000',
    opacity: 0.5,
    flexDirection: 'column',
    alignItems: 'center'
  },
  mainText: {
    fontSize: baseFontSize,
    color: '#FFFFFF'
  },
  locationContainer: {
    borderBottomColor: '#DDDDDD',
    borderBottomWidth: 1,
    marginLeft: 5,
    marginTop: 3,
    marginBottom: 15
  },
  locationCode: {
    width: 100,
    height: baseFontSize+25,
  },
});

export default WeatherProject;