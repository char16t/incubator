import { combineReducers } from 'redux'
import todos from './todos'
import deepReducer from './deepReducer'

export default combineReducers({
  todos,
  deepReducer,
})
