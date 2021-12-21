<template>
  <BasicDrawer v-bind="$attrs" title="文章设置" width="20%" show-footer @register="register">
    <Form layout="vertical">
      <FormItem label="标签">
        <Select
          v-model:value="tags"
          mode="multiple"
          style="width: 100%"
          placeholder="请选择标签"
          @change="setTags"
        >
          <SelectOption v-for="tag in tagList" :key="tag.name">
            {{ tag.name }}
          </SelectOption>
        </Select>
      </FormItem>
      <FormItem label="分类">
        <Select
          v-model:value="category"
          style="width: 100%"
          placeholder="请选择分类"
          @change="setCategory"
        >
          <SelectOption v-for="category in categoryList" :key="category.name">
            {{ category.name }}
          </SelectOption>
        </Select>
      </FormItem>
      <FormItem label="是否置顶">
        <Switch
          v-model:checked="priority"
          checked-children="是"
          un-checked-children="否"
          default-checked
          @change="setPriority"
        />
      </FormItem>
      <FormItem label="开启评论">
        <Switch
          v-model:checked="comment"
          checked-children="是"
          un-checked-children="否"
          default-checked
          @change="setComment"
        />
      </FormItem>
      <FormItem label="创建日期">
        <DatePicker
          v-model:value="createTime"
          show-time
          placeholder="选择创建日期"
          @change="setCreateTime"
        />
      </FormItem>
      <FormItem label="修改日期">
        <DatePicker
          v-model:value="updateTime"
          show-time
          placeholder="选择修改日期"
          @change="setUpdateTime"
        />
      </FormItem>
    </Form>

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
  import dayjs from 'dayjs';
  import { Form, Select, DatePicker, Switch } from 'ant-design-vue';

  export default defineComponent({
    components: {
      BasicDrawer,
      Form,
      FormItem: Form.Item,
      Select,
      SelectOption: Select.SelectOption,
      DatePicker,
      Switch,
    },
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
        // console.log(moment(store.state.currentPost.createTime).utc().format());
        // console.log(v.utc().format());
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
        createTime: computed(() => dayjs(store.state.currentPost.createTime)),
        updateTime: computed(() => dayjs(store.state.currentPost.updateTime)),
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
