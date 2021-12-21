import { reactive } from 'vue';
import {
  apiMetaList,
  apiSavePost,
  apiGetPost,
  apiGetPage,
  apiSavePage,
  apiGetBlogSetting,
  saveBlogSetting,
} from '/@/api/blog/blog';
import { useMessage } from '/@/hooks/web/useMessage';
const { createMessage } = useMessage();
const { success } = createMessage;
import { formatToDateTime } from '/@/utils/dateUtil';
// import { useGo } from '/@/hooks/web/usePage';
// const go = useGo();

class Store {
  constructor() {
    this.state = reactive({
      currentPost: {},
      currentPage: {},
      tagList: [],
      categoryList: [],
      blogSetting: {},
    });
  }

  setTitle(title) {
    this.state.currentPost.title = title;
  }

  setPageTitle(title) {
    this.state.currentPage.title = title;
  }

  setContent(content) {
    this.state.currentPost.content = content;
  }

  setPageContent(content) {
    this.state.currentPage.content = content;
  }

  setTags(tags) {
    this.state.currentPost.tags = tags.toString();
  }

  setCategory(category) {
    this.state.currentPost.category = category;
  }

  setPriority(priority) {
    priority ? (this.state.currentPost.priority = 1) : (this.state.currentPost.priority = 0);
  }

  setPagePriority(priority) {
    this.state.currentPage.priority = priority;
  }

  setCreateTime(datetime) {
    this.state.currentPost.createTime = formatToDateTime(datetime);
  }

  setUpdateTime(datetime) {
    this.state.currentPost.updateTime = formatToDateTime(datetime);
  }

  setComment(allowComment) {
    this.state.currentPost.allowComment = allowComment;
  }

  setPageComment(allowComment) {
    this.state.currentPage.allowComment = allowComment;
  }

  async savePost(status) {
    this.state.currentPost.status = status;
    const postId = await apiSavePost(this.state.currentPost);
    await this.fetchPost(postId);
    // go('/blog/edit?id='+postId)
    success('保存成功');
  }

  async savePage(status) {
    this.state.currentPage.status = status;
    const postId = await apiSavePage(this.state.currentPage);
    await this.fetchPage(postId);
    // go('/blog/edit?id='+postId)
    success('保存成功');
  }

  async fetchMetaList() {
    this.state.tagList = await apiMetaList('tag');
    this.state.categoryList = await apiMetaList('category');
  }

  async fetchPost(postId) {
    if (postId) {
      this.state.currentPost = await apiGetPost(postId);
    } else {
      this.state.currentPost = {};
    }
  }

  async fetchBlogSetting() {
    this.state.blogSetting = await apiGetBlogSetting();
  }

  async fetchPage(pageId) {
    if (pageId) {
      this.state.currentPage = await apiGetPage(pageId);
    } else {
      this.state.currentPage = {};
    }
  }

  async saveSetting(v) {
    Object.assign(this.state.blogSetting, v);
    await saveBlogSetting(this.state.blogSetting);
    await this.fetchBlogSetting();
  }
}
export const store = new Store();
