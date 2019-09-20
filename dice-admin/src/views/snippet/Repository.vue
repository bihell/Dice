<template>
  <div class="app-container">
    <div class="tool-container">
      <div>
        <el-input
          v-model="querySnippet.title"
          placeholder="搜索代码段标题"
          clearable
          style="max-width: 400px;"
          @keyup.enter.native="handleSearch"
        />
        <el-input
          v-model="querySnippet.snippetFileContent"
          placeholder="搜索代码段内容"
          clearable
          style="max-width: 400px;margin-left: 5px"
          @keyup.enter.native="handleSearch"
        />
        <el-button
          v-waves
          type="primary"
          size="small"
          icon="el-icon-search"
          style="margin-left: 16px;"
          @click="handleSearch"
        >搜索
        </el-button>
        <el-button
          v-waves
          type="primary"
          size="small"
          icon="el-icon-document-add"
          style="margin-left: 16px;"
          @click="handleNew"
        >新建
        </el-button>
      </div>
    </div>
    <el-row>
      <el-col :span="4">
        <el-card shadow="never" :body-style="{ padding: '0px',height:labelHeight}">
          <div slot="header">
            <span>标签列表</span>
          </div>
          <ul class="menu-list">
            <li v-for="item in label" :key="item.id">
              <a :class="{'is-active': item.id === labelActiveId}" href="#" @click="labelClick(item.id)">
                <span class="with-text-overflow">{{
                  item.name
                }}</span>
                <span style="float: right;clear: both">
                  <span class="tag is-rounded">{{ item.count }}</span>
                </span>
              </a>
            </li>
          </ul>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card shadow="never" :body-style="{ padding: '0px',height:labelHeight}">
          <div slot="header">
            <span>标题列表</span>
          </div>
          <ul class="menu-list">
            <li v-for="item in labelSnippets" :key="item.id">
              <a :class="{'is-active': item.id === labelSnippetsActiveId}" href="#" @click="labelSnippetsClick(item.id)">
                <span class="with-text-overflow">{{
                  item.title
                }}</span>
              </a>
            </li>
          </ul>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-card shadow="never" :body-style="{ padding: '0px'}">
          <snippet-show v-if="showSnippet === 'show'"></snippet-show>
          <snippet-edit v-if="showSnippet === 'edit'"></snippet-edit>
          <snippet-new v-if="showSnippet === 'create'"></snippet-new>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SnippetShow from './snippet/Show.vue'
import SnippetEdit from './snippet/Edit.vue'
import SnippetNew from './snippet/New.vue'
import { getAllTags, getSnippetByMeta, getSnippetById } from '@/api/snippet'
import Factory from './mixins/factory'
import waves from '@/directive/waves' // waves directive

export default {
  components: { SnippetShow, SnippetEdit, SnippetNew },
  directives: { waves },
  data() {
    return {
      querySnippet: { title: '', snippetFileContent: '' },
      labelHeight: document.documentElement.clientHeight - 205 + 'px'
    }
  },
  computed: {
    showSnippet() {
      return this.$store.state.labelSnippets.mode
    },
    label() {
      return this.$store.state.labels.items
    },
    labelSnippets() {
      return this.$store.state.labelSnippets.items
    },
    labelActiveId() {
      return this.$store.state.labels.active
    },
    labelSnippetsActiveId() {
      return this.$store.state.labelSnippets.active.id
    }
  },
  mounted() {
    this.getLabels()
    this.$store.dispatch('setDefaultActiveEntities')
  },
  methods: {
    labelClick(labelId) {
      getSnippetByMeta(labelId).then(response => {
        this.$store.commit('setLabelSnippets', response.data)
        this.$store.commit('setActiveLabel', labelId)
      })
    },
    labelSnippetsClick(snippetId) {
      getSnippetById(snippetId).then(response => {
        this.$store.commit('setActiveLabelSnippet', response.data)
        this.$store.commit('setSnippetMode', 'show')
      })
    },
    handleSearch() {
      this.getLabels()
      this.$store.commit('setQuerySnippet', this.querySnippet)
    },
    handleNew() {
      this.$store.commit('setActiveLabelSnippet', Factory.methods.factory().snippet)
      this.$store.commit('setSnippetMode', 'create')
    },
    getLabels() {
      getAllTags(this.querySnippet).then(response => {
        this.$store.commit('setLabels', response.data)
      })
    }
  }
}
</script>

<style scoped lang="scss">
  @import "~bulma-scss/bulma.scss";
  .card-description {
    padding: 1rem 0.75em;
  }
  .tool-container {
    padding-bottom: 10px;
    justify-content: space-between;
    background: #fff;
  }
  .menu-text {
    margin: 1em;
  }
</style>
