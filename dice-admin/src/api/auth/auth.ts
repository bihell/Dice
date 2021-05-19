import { defHttp } from '/@/utils/http/axios';

enum Api {
  USER_LIST = '/auth/user/list',
  API_LIST = '/auth/api/list',
  Role_LIST = '/auth/role/list',
}

export function apiUserList(params: any) {
  return defHttp.request({
    url: Api.USER_LIST,
    method: 'POST',
    params,
  });
}

export function apiApiList(params: any) {
  return defHttp.request({
    url: Api.API_LIST,
    method: 'POST',
    params,
  });
}

export function apiRoleList(params: any) {
  return defHttp.request({
    url: Api.Role_LIST,
    method: 'POST',
    params,
  });
}
