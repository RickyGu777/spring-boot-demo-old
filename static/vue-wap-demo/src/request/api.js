/**
  * api接口统一管理
  */
import {get, post} from './http'

export const uploadJoke = data => post('/config', data);
