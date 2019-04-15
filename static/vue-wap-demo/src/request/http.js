import axios from 'axios';
import { showLoading, hideLoading } from '../util/loading';
// import QS from 'qs';
// import {Toast} from 'vant';

axios.defaults.baseURL = '/api';
axios.defaults.timeout = 4000;
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

/* 请求拦截器（请求之前的操作） */
axios.interceptors.request.use((req) => {
  showLoading();
  return req;
}, err => Promise.reject(err));

/* 请求之后的操作 */
axios.interceptors.response.use((res) => {
  hideLoading();

  return res;

  return Promise.reject(res);
}, (err) => {
  hideLoading();
  return Promise.reject(err);
});

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
