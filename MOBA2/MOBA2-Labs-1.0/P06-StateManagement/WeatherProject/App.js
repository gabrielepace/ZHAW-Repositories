import React from 'react';
import { View, StyleSheet } from 'react-native';

import WeatherProject from './components/WeatherProject';
import WeatherContextProvider from './contexts/WeatherContext';

export default function App() {
  return (
    <View style={styles.container}>
      <WeatherContextProvider>
        <WeatherProject />
      </WeatherContextProvider>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});