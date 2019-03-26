import axios from 'axios';
import QS from 'qs';
// import {Toast} from 'vant';

axios.defaults.baseURL = '/api';
axios.defaults.timeout = 3000;
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

export function get(url, params) {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: params
    }).then(res => {
      resolve(res.data);
    }).catch(err => {
      reject(err.data)
    })
  });
}

export function post(url, params) {
  return new Promise((resolve, reject) => {
    axios.post(url, params)
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err.data)
      })
  });
}
