/****************************************** */
/*  src/utils/axios.js                      */
/****************************************** */

import axios from 'axios';
import { baseUrl } from '../../config/dev.env';

export default (url = '', data = {}, type = 'GET', method = 'fetch') => {
  return new Promise((resolve, reject) => {
    const instance = axios.create({
      baseURL: baseUrl,
      timeout: 20000 //,
    });
    instance({
      url: url,
      method: type,
      params: data
    })
      .then(response => {
        const res = response.data;
        // 50001:token已过期
        // 50002:token非法
        if (res.retCode === "50001" || res.retCode === "50002") {
          router.push({ path: '/login' })
          reject(res);
        }
        resolve(res);
      })
      .catch(error => {
        router.push({ path: '/404', query: { error: error } });
        reject(error);
      });
  });
}
