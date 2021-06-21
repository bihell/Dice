import { defHttp } from '/@/utils/http/axios';
import { BasicPageParams } from '/@/api/model/baseModel';
import { NavTypeItem } from '/@/api/nav/model/navModel';
// import { NavTypeItem } from './model/navModel';

enum Api {
  NavTypeTreeList = '/nav/type/getNavTypeTree',
  NavTypeAdd = '/nav/type/add',
  NavTypeDelete = '/nav/type/delete',
  NavTypeUpdate = '/nav/type/update',
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

export const addNavType = (params: any) => defHttp.post({ url: Api.NavTypeAdd, params: params });

export const delNavType = (params: any) =>
  defHttp.post({ url: Api.NavTypeDelete + '/' + params.id });

export const updateNavType = (params: any) =>
  defHttp.post({ url: Api.NavTypeUpdate, params: params });
