import React from 'react';
import { createMaterialBottomTabNavigator } from '@react-navigation/material-bottom-tabs';
import { NavigationContainer } from '@react-navigation/native';
import { SafeAreaProvider } from 'react-native-safe-area-context'
import Produkte from './Produkte';
import Wunschliste from './Wunschliste';


const Tab = createMaterialBottomTabNavigator();

export default function App() {
  return (
    <SafeAreaProvider>
      <NavigationContainer>
          <Tab.Navigator>
            <Tab.Screen name="Produkte" component={Produkte} />
            <Tab.Screen name="Wunschliste" component={Wunschliste} />
          </Tab.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
}
