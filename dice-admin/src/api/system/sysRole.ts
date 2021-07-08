import { defHttp } from '/@/utils/http/axios';

enum Api {
  listRoleMenus = '/auth/sysRole/listRoleMenus',
}

export function listRoleMenus(params?: object) {
  return defHttp.get({ url: Api.listRoleMenus, params: params });
}
