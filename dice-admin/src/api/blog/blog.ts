import { defHttp } from '/@/utils/http/axios';
import { ArticleListParams, ArticleListGetResultModel, PostItem } from './model/blogModel';

enum Api {
  ARTICLE_LIST = '/article/getPageList',
  COMMENT_LIST = '/comment/getPageList',
  POST = '/article/',
  PAGE = '/page/',
  PAGE_LIST = '/page/getPageList',
  COMMENT = '/comment/',
  META_LIST = '/meta/getList',
  META = '/meta/',
  Option = '/option',
}

export function apiGetBlogSetting() {
  return defHttp.request({
    url: Api.Option,
    method: 'GET',
  });
}

export function saveBlogSetting(setting: any) {
  return defHttp.request({
    url: Api.Option,
    method: 'POST',
    params: setting,
  });
}
export function apiGetPost(id: number) {
  return defHttp.request<PostItem>({
    url: Api.POST + id,
    method: 'GET',
  });
}

export function apiGetPage(id: number) {
  return defHttp.request<PostItem>({
    url: Api.PAGE + id,
    method: 'GET',
  });
}

export function apiDeletePost(id: number) {
  return defHttp.request({
    url: Api.POST + id,
    method: 'DELETE',
  });
}

export function apiDeletePage(id: number) {
  return defHttp.request({
    url: Api.PAGE + id,
    method: 'DELETE',
  });
}

export function apiDeleteMeta(name: string, type: string) {
  return defHttp.request({
    url: Api.META,
    params: { name, type },
    method: 'DELETE',
  });
}

export function apiDeleteComment(id: number) {
  return defHttp.request({
    url: Api.COMMENT + id,
    method: 'DELETE',
  });
}

export function apiSavePost(params: PostItem) {
  return defHttp.request({
    url: Api.POST,
    method: 'post',
    params,
  });
}

export function apiSavePage(params: PostItem) {
  return defHttp.request({
    url: Api.PAGE,
    method: 'post',
    params,
  });
}

export function apiPostList(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.ARTICLE_LIST,
    method: 'POST',
    params,
  });
}

export function apiPageList(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.PAGE_LIST,
    method: 'POST',
    params,
  });
}

export function apiCommentList(params: ArticleListParams) {
  return defHttp.request<ArticleListGetResultModel>({
    url: Api.COMMENT_LIST,
    method: 'POST',
    params,
  });
}

export function apiMetaList(type: string) {
  return defHttp.request({
    url: Api.META_LIST,
    method: 'GET',
    params: { type },
    headers: {
      ignoreCancelToken: true,
    },
  });
}

export function apiSaveMeta(name: string, type: string) {
  return defHttp.request({
    url: Api.META,
    method: 'POST',
    params: { name, type },
  });
}

export function apiUpdateMeta(id: number, name: string, type: string) {
  return defHttp.request({
    url: Api.META + id,
    method: 'POST',
    params: { id, name, type },
  });
}

export function postStatus() {
  return {
    PUBLISHED: {
      value: 'PUBLISHED',
      color: 'green',
      status: 'success',
      text: '已发布',
    },
    DRAFT: {
      value: 'DRAFT',
      color: 'warning',
      status: 'warning',
      text: '草稿',
    },
    RECYCLE: {
      value: 'RECYCLE',
      color: 'danger',
      status: 'error',
      text: '回收站',
    },
    INTIMATE: {
      value: 'INTIMATE',
      color: 'blue',
      status: 'success',
      text: '私密',
    },
  };
}
