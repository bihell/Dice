<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="20%" show-footer @register="register">
    <a-form layout="vertical">
      <a-form-item label="标签">
        <a-select
          v-model:value="tags"
          mode="multiple"
          style="width: 100%;"
          placeholder="请选择标签"
          @change="setTags"
        >
          <a-select-option v-for="tag in tagList" :key="tag.name">
            {{ tag.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="分类">
        <a-select
          v-model:value="category"
          style="width: 100%;"
          placeholder="请选择分类"
          @change="setCategory"
        >
          <a-select-option v-for="category in categoryList" :key="category.name">
            {{ category.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="是否置顶">
        <a-switch
          v-model:checked="priority"
          checked-children="是"
          un-checked-children="否"
          default-checked
          @change="setPriority"
        />
      </a-form-item>
      <a-form-item label="开启评论">
        <a-switch
          v-model:checked="comment"
          checked-children="是"
          un-checked-children="否"
          default-checked
          @change="setComment"
        />
      </a-form-item>
      <a-form-item label="创建日期">
        <a-date-picker
          v-model:value="createTime"
          show-time
          placeholder="选择创建日期"
          @change="setCreateTime"
        />
      </a-form-item>
      <a-form-item label="修改日期">
        <a-date-picker
          v-model:value="updateTime"
          show-time
          placeholder="选择修改日期"
          @change="setUpdateTime"
        />
      </a-form-item>
    </a-form>

    <template #footer>
      <a-button class="mr-2" type="dashed" @click="savePost('DRAFT')"> 保存草稿 </a-button>
      <a-button class="mr-2" type="primary" @click="savePost('PUBLISHED')"> 发布 </a-button>
      <a-button disabled>发布并查看</a-button>
    </template>
  </BasicDrawer>
</template>
<script>
  import { computed, defineComponent } from 'vue';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { store } from '../store';
  import moment from 'moment';

  export default defineComponent({
    components: { BasicDrawer },
    setup() {
      const [register] = useDrawerInner();

      function setTags(v) {
        store.setTags(v);
      }

      function setCategory(v) {
        store.setCategory(v);
      }

      function setPriority(v) {
        store.setPriority(v);
      }

      function setComment(v) {
        store.setComment(v);
      }

      function savePost(v) {
        store.savePost(v);
      }

      function setCreateTime(v) {
        console.log(moment(store.state.currentPost.createTime).utc().format());
        console.log(v.utc().format());
        store.setCreateTime(v.utc().format());
      }

      function setUpdateTime(v) {
        store.setUpdateTime(v.utc().format());
      }
      store.fetchMetaList();

      return {
        register,
        tags: computed(() =>
          store.state.currentPost.tags === undefined ||
          store.state.currentPost.tags === null ||
          store.state.currentPost.tags.length === 0
            ? []
            : store.state.currentPost.tags.split(',')
        ),
        category: computed(() => store.state.currentPost.category),
        tagList: computed(() => store.state.tagList),
        categoryList: computed(() => store.state.categoryList),
        priority: computed(() => store.state.currentPost.priority),
        comment: computed(() => store.state.currentPost.allowComment),
        createTime: computed(() => moment(store.state.currentPost.createTime)),
        updateTime: computed(() => moment(store.state.currentPost.updateTime)),
        // createTime: computed(() => moment.utc(store.state.currentPost.createTime).local()),
        // updateTime: computed(() => moment.utc(store.state.currentPost.updateTime).local()),
        setTags,
        setCategory,
        setPriority,
        setComment,
        savePost,
        setCreateTime,
        setUpdateTime,
      };
    },
  });
</script>
