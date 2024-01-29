import { defHttp } from '@/utils/http/axios';
import { BasicPageParams } from '@/api/model/baseModel';
import { NavTypeItem } from '@/api/nav/model/navModel';
// import { NavTypeItem } from './model/navModel';

enum Api {
  NavTypeTreeList = '/nav/type/getNavTypeTree',
  NavTypeAdd = '/nav/type/add',
  NavTypeDelete = '/nav/type/delete',
  NavTypeUpdate = '/nav/type/update',
  NavDetailPageList = '/nav/detail/getPageList',
  NavDetailAdd = '/nav/detail/add',
  NavDetailUpdate = '/nav/detail/update',
  NavDetailDelete = '/nav/detail/delete',
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

export const getNavDetailList = (params: any) =>
  defHttp.get({ url: Api.NavDetailPageList, params });

export const addNavDetail = (params: any) =>
  defHttp.post({ url: Api.NavDetailAdd, params: params });

export const updateNavDetail = (params: any) =>
  defHttp.post({ url: Api.NavDetailUpdate, params: params });

export const delNavDetail = (params: any) =>
  defHttp.post({ url: Api.NavDetailDelete + '/' + params.id });
