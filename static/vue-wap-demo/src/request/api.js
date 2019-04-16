/**
  * api接口统一管理
  */
import {get, post} from './http'

export const uploadJoke = data => post('/Joke/insert', data);

export const selectJokeList = data => post('/Joke/selectJokeList', data);

export const selectJokeByUUID = data => post('/Joke/selectByUUID', data);

export const getMenuList = data => post('/Menu/getMenuList', data);

export const createUUID = data => post('/Menu/createUUID', data);

export const selectAuthTree = data => post('/Auth/selectAuthTree', data);

export const selectDiaryList = data => post('/Diary/selectDiaryList', data);

export const selectDiaryByUUID = data => post('/Diary/selectByUUID', data);

export const updateDiaryByUUID = data => post('/Diary/updateDiaryByUUID', data);

export const deleteDiaryByUUID = data => post('/Diary/deleteDiaryByUUID', data);

export const addDiary = data => post('/Diary/addDiary', data);

export const getBaiduAccessToke = data => post('/Baidu/getAccessToke', data);

export const getOCRList = data => post('/Baidu/getOCRList', data);

export const deleteBaiduOCR = data => post('/Baidu/delete', data);

export const insertAhri = data => post('/AhriUrl/insert', data);

export const downloadAhri = data => post('/AhriUrl/download', data);

export const selectAllAhri = data => post('/AhriUrl/selectAll', data);

export const updateImageTitle = data => post('/UploadImage/updateTitle', data);

export const ShareTicketUrlInsertList = data => post('/ShareTicketUrl/insertList', data);

export const ShareTicketUrlSelectTitleAndTipsName = data => post('/ShareTicketUrl/selectTitleAndTipsName', data);
