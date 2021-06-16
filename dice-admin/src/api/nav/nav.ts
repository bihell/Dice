import { defHttp } from '/@/utils/http/axios';
import { BasicPageParams } from '/@/api/model/baseModel';
import { NavTypeItem } from '/@/api/nav/model/navModel';
// import { NavTypeItem } from './model/navModel';

enum Api {
  NavTypeTreeList = '/nav/type/getNavTypeTree',
}

export function apiNavTypeList(params: BasicPageParams) {
  return defHttp.request({
    url: Api.NavTypeTreeList,
    method: 'GET',
    params,
  });
}

export const getNavTypeTreeList = (params?: NavTypeItem) =>
  defHttp.get({ url: Api.NavTypeTreeList, params });
