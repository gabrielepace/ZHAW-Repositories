import React, { useContext } from 'react';
import { Text, View, StyleSheet } from 'react-native';

import { WeatherContext } from '../contexts/WeatherContext';

const Forecast = () => {
  const { forecast } = useContext(WeatherContext);
  return (
    forecast === undefined ?
      undefined :
      (
        <View>
          <Text style={styles.bigText}>
            {forecast.main}
          </Text>
          <Text style={styles.mainText}>
            Current conditions: {forecast.description}
          </Text>
          <Text style={styles.bigText}>
            {forecast.temp}°C
          </Text>
        </View>
      )
  );
};

const styles = StyleSheet.create({
  bigText: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    color: '#FFFFFF'
  },
  mainText: {
    fontSize: 16,
    textAlign: 'center',
    color: '#FFFFFF'
  }
});

export default Forecast;