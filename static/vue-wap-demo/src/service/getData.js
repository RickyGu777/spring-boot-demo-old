/****************************************** */
/*  src/service/getData.js                  */
/****************************************** */

// import fetch from '../utils/fetch';
import axios from '../utils/axios';

/**
 * 账号密码登录
 */
export const accountLogin = (email, password) =>  axios('/loginController/login', {email, password}, 'POST');
