/**
  * api接口统一管理
  */
import {get, post} from './http'

export const uploadJoke = data => post('/Joke/insert', data);

export const selectJokeList = data => post('/Joke/selectJokeList', data);

export const selectJokeByUUID = data => post('/Joke/selectByUUID', data);

export const getMenuList = data => post('/Menu/getMenuList', data);

export const selectAuthTree = data => post('/Auth/selectAuthTree', data);

export const selectDiaryList = data => post('/Diary/selectDiaryList', data);

export const selectDiaryByUUID = data => post('/Diary/selectByUUID', data);

export const updateDiaryByUUID = data => post('/Diary/updateDiaryByUUID', data);

export const addDiary = data => post('/Diary/addDiary', data);

export const getBaiduAccessToke = data => post('/Baidu/getAccessToke', data);
