import { defHttp } from '/@/utils/http/axios';

enum Api {
  API_LIST = '/auth/api/list',
  Role_LIST = '/auth/role/list',
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
