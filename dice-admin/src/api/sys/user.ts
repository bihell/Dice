import { defHttp } from '/@/utils/http/axios';
import { LoginParams, LoginResultModel, GetUserInfoModel } from './model/userModel';

import { ErrorMessageMode } from '/#/axios';

enum Api {
  Login = '/auth/login',
  Logout = '/auth/logout',
  GetUserInfo = '/auth/getSysUserInfo',
  GetPermCode = '/auth/getPermCode',
  UserList = '/auth/user/getPageList',
  UpdateUser = '/auth/user/update',
  AddUser = '/auth/user/add',
  DeleteUser = '/auth/user/delete',
}

export const delUser = (params: any) => defHttp.post({ url: Api.DeleteUser + '/' + params.id });

export const updateUser = (params: any) => defHttp.post({ url: Api.UpdateUser, params });

export const addUser = (params: any) => defHttp.post({ url: Api.AddUser, params });

export function getUserList(params: any) {
  return defHttp.request({
    url: Api.UserList,
    method: 'GET',
    params,
  });
}

/**
 * @description: user login api
 */
export function loginApi(params: LoginParams, mode: ErrorMessageMode = 'modal') {
  return defHttp.post<LoginResultModel>(
    {
      url: Api.Login,
      params,
    },
    {
      errorMessageMode: mode,
    }
  );
}

/**
 * @description: getUserInfo
 */
export function getUserInfo() {
  return defHttp.get<GetUserInfoModel>({ url: Api.GetUserInfo });
}

export function getPermCode() {
  return defHttp.get<string[]>({ url: Api.GetPermCode });
}

export function doLogout() {
  return defHttp.get({ url: Api.Logout });
}
