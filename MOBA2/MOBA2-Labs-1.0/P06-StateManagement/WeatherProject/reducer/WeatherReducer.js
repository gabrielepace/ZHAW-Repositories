const weatherReducer = (forecast, action) => {
  switch (action.type) {
    case 'ADD_FORECAST':
      return {
        temp: action.forecast.temp,
        main: action.forecast.main,
        description: action.forecast.description
      }
    default:
      console.warn("no match found inside reducer for action-type: " + action.type);
      return forecast;
  }
}

export default weatherReducer;