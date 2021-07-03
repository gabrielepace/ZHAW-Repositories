import React, { createContext, useReducer } from 'react';
import weatherReducer from '../reducer/WeatherReducer';

export const WeatherContext = createContext();

let initState = {
  main: '',
  temp: '',
  description: ''
}

const WeatherContextProvider = (props) => {
  const [forecast, dispatch] = useReducer(weatherReducer, initState);

  return (
    <WeatherContext.Provider value={{ forecast, dispatch }}>
      {props.children}
    </WeatherContext.Provider>
  );
}

export default WeatherContextProvider;

