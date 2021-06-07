import { defHttp } from '/@/utils/http/axios';
import { BasicPageParams } from '/@/api/model/baseModel';

enum Api {
  NAV_TYPE_LIST = '/nav/type/getPageList',
}

export function apiNavTypeList(params: BasicPageParams) {
  return defHttp.request({
    url: Api.NAV_TYPE_LIST,
    method: 'POST',
    params,
  });
}
