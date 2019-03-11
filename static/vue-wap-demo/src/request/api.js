/**
  * api接口统一管理
  */
import {get, post} from './http'

export const uploadJoke = data => post('/Joke/insert', data);

export const selectJokeList = data => post('/Joke/selectJokeList', data);