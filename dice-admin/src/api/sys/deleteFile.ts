import { defHttp } from '@/utils/http/axios';

enum Api {
  FileDelete = '/file_delete',
}

export const deleteFile = (params) => {
  const formData = new FormData();
  formData.append('file', params.file);
  formData.append('type', params.type);
  defHttp.post({ url: Api.FileDelete, params: formData });
};
