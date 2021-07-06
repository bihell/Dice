import {
  AccountParams,
  DeptListItem,
  MenuParams,
  RoleParams,
  RolePageParams,
  MenuListGetResultModel,
  DeptListGetResultModel,
  AccountListGetResultModel,
  RolePageListGetResultModel,
  RoleListGetResultModel,
} from './model/systemModel';
import { defHttp } from '/@/utils/http/axios';

enum Api {
  AccountList = '/system/getAccountList',
  DeptList = '/system/getDeptList',
  setRoleStatus = '/system/setRoleStatus',
  MenuTreeList = '/auth/sysPermission/getAllMenuTree',
  RolePageList = '/auth/sysRole/getPageList',
  UpdateRolePermission = '/auth/sysRole/updateSysRolePermission',
  RoleAdd = '/auth/sysRole/add',
  GetAllRoleList = '/system/getAllRoleList',
  GetThreeLevelPermissionIdsByRoleId = '/auth/sysPermission/getThreeLevelPermissionIdsByRoleId',
  PermissionAdd = '/auth/sysPermission/add',
  PermissionUpdate = '/auth/sysPermission/update',
  PermissionDelete = '/auth/sysPermission/delete',
}

export const getThreeLevelPermissionIdsByRoleId = (roleId: Number) =>
  defHttp.get({ url: Api.GetThreeLevelPermissionIdsByRoleId + '/' + roleId });

export const addRole = (params: any) => defHttp.post({ url: Api.RoleAdd, params });

export const updateRolePermission = (params: any) =>
  defHttp.post({ url: Api.UpdateRolePermission, params });

export const getAccountList = (params: AccountParams) =>
  defHttp.get<AccountListGetResultModel>({ url: Api.AccountList, params });

export const getDeptList = (params?: DeptListItem) =>
  defHttp.get<DeptListGetResultModel>({ url: Api.DeptList, params });

export const getMenuTreeList = (params?: MenuParams) =>
  defHttp.get<MenuListGetResultModel>({ url: Api.MenuTreeList, params });

export const getRoleListByPage = (params?: RolePageParams) =>
  defHttp.get<RolePageListGetResultModel>({ url: Api.RolePageList, params });

export const getAllRoleList = (params?: RoleParams) =>
  defHttp.get<RoleListGetResultModel>({ url: Api.GetAllRoleList, params });

export const setRoleStatus = (id: number, status: string) =>
  defHttp.post({ url: Api.setRoleStatus, params: { id, status } });

export const addPermission = (params: any) => defHttp.post({ url: Api.PermissionAdd, params });

export const updatePermission = (params: any) =>
  defHttp.post({ url: Api.PermissionUpdate, params });

export const delPermission = (params: any) =>
  defHttp.post({ url: Api.PermissionDelete + '/' + params.id });
