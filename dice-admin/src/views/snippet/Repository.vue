<template xmlns:el-col="http://www.w3.org/1999/html">
  <div class="app-container">
    <div class="tool-container">
      <div>
        <el-input
          v-model="querySnippet.title"
          placeholder="搜索代码段标题"
          clearable
          style="max-width: 300px;"
          @keyup.enter.native="init"
        />
        <el-input
          v-model="querySnippet.content"
          placeholder="搜索代码段内容"
          clearable
          style="max-width: 300px;margin-left: 5px"
          @keyup.enter.native="init"
        />
        <el-button
          type="primary"
          icon="el-icon-edit"
          style="margin-left: 16px;"
          @click="handleNew"
        >新代码段
        </el-button>
      </div>
    </div>
    <el-row>
      <el-col :span="4">
        <el-card shadow="never" :body-style="{ padding: '0px',height: '800px'}">
          <div slot="header">
            <p class="menu-label">Labels</p>
          </div>
          <ul class="menu-list">
            <li v-for="tag in tags" :key="tag.id">
              <a :class="{'is-active': tag.active}" href="#" @click="labelClick">
                <span class="with-text-overflow" @click="clickTag(tag.id, tag.name)">{{
                  tag.name
                }}</span>
                <span style="float: right;clear: both">
                  <span class="tag is-rounded">{{ tag.count }}</span>
                </span>
              </a>
            </li>
          </ul>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card shadow="never" :body-style="{ padding: '0px' ,height: '800px'}">
          <div slot="header">
            <p class="menu-label">Labels</p>
          </div>
          <ul class="menu-list">
            <li v-for="tag in tags" :key="tag.id">
              <a :class="{'is-active': tag.active}" href="#" @click="labelClick">
                <span class="with-text-overflow" @click="clickTag(tag.id, tag.name)">{{
                  tag.name
                }}</span>
                <span style="float: right;clear: both">
                  <span class="tag is-rounded" style="margin-left: 0.25em">{{ tag.count }}</span>
                </span>
              </a>
            </li>
          </ul>
        </el-card>
      </el-col>
      <el-col :span="16">
        <div class="main">
          <snippet-show v-if="showSnippet === 'show'"></snippet-show>
          <snippet-edit v-if="showSnippet === 'edit'"></snippet-edit>
          <snippet-new v-if="showSnippet === 'create'"></snippet-new>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SnippetShow from './snippet/Show.vue'
import SnippetEdit from './snippet/Edit.vue'
import SnippetNew from './snippet/New.vue'

export default {
  components: { SnippetShow, SnippetEdit, SnippetNew },
  data() {
    return {
      tags: [{ name: 'bihell', count: 12, active: 1 }, { name: 'dice', count: 10086, active: 0 }],
      showSnippet: 'create',
      snippet: { title: 'test title', id: 1, description: 'test description', snippetFiles: [{ id: 2 }, { id: 3 }] },
      querySnippet: { title: '', content: '' }
    }
  },
  computed: {
    // showSnippet() {
    //   return this.$store.state.labelSnippets.mode
    // },
    //
    // snippet() {
    //   console.log('active:', this.$store.state.labelSnippets.active)
    //   return this.$store.state.labelSnippets.active
    // }
  },
  mounted() {
    // setTimeout(() => {
    //   Backend.data.get('/api/v1/data/default-state', response => {
    //     console.log(response.data);
    // this.$store.dispatch('setData', { 'snippets': [{ 'id': 10, 'title': '测试标题', 'description': '测试描述', 'label': { 'id': 10, 'name': '测试标签', 'snippets_count': 1 }, 'snippetFiles': [{ 'id': 17, 'title': '测试文件标题1', 'content': '测试代码1', 'language': 'automatically', 'tabs': 4, 'snippet_id': 10, 'created_at': '2019-08-30T14:38:33.145Z', 'updated_at': '2019-08-30T14:38:33.145Z' }, { 'id': 18, 'title': '测试文件2', 'content': '测试代码2', 'language': 'automatically', 'tabs': 4, 'snippet_id': 10, 'created_at': '2019-08-30T14:38:33.154Z', 'updated_at': '2019-08-30T14:38:33.154Z' }] }, { 'id': 11, 'title': 'test title', 'description': 'test description', 'label': { 'id': 11, 'name': 'test lable', 'snippets_count': 1 }, 'snippetFiles': [{ 'id': 19, 'title': 'test title 1', 'content': 'test code 1', 'language': 'automatically', 'tabs': 4, 'snippet_id': 11, 'created_at': '2019-08-30T15:02:46.603Z', 'updated_at': '2019-08-30T15:02:46.603Z' }, { 'id': 20, 'title': 'test file 2', 'content': 'test code 2', 'language': 'automatically', 'tabs': 4, 'snippet_id': 11, 'created_at': '2019-08-30T15:02:46.606Z', 'updated_at': '2019-08-30T15:02:46.606Z' }] }], 'languages': { 'automatically': 'Automatically', 'c': 'C', 'cpp': 'C++', 'cs': 'C#', 'clojure': 'Clojure', 'coffeescript': 'CoffeeScript', 'crystal': 'Crystal', 'css': 'CSS', 'bash': 'Bash', 'd': 'D', 'dart': 'Dart', 'django': 'Django', 'dockerfile': 'Dockerfile', 'elm': 'Elm', 'erlang': 'Erlang', 'fortran': 'Fortran', 'go': 'Go', 'groovy': 'Groovy', 'haml': 'HAML', 'handlebars': 'Handlebars', 'haskell': 'Haskell', 'html': 'HTML', 'http': 'HTTP', 'java': 'Java', 'javascript': 'JavaScript', 'julia': 'Julia', 'less': 'LESS', 'livescript': 'Livescript', 'lua': 'Lua', 'markdown': 'Markdown', 'nginx': 'Nginx', 'objectivec': 'Objective-C', 'perl': 'Perl', 'php': 'PHP', 'powershell': 'Powershell', 'plain': 'Plain Text', 'puppet': 'Puppet', 'python': 'Python', 'r': 'R', 'ruby': 'Ruby', 'rust': 'Rust', 'scss': 'SCSS', 'stylus': 'Stylus', 'smalltalk': 'Smalltalk', 'sql': 'SQL', 'swift': 'Swift', 'tcl': 'Tcl', 'twig': 'Twig', 'xml': 'XML', 'xquery': 'XQuery', 'yaml': 'YAML' }})
    // this.$store.dispatch('setDefaultActiveEntities')
    //   })
    // }, 250)
  },
  methods: {
    labelClick(e) {
      e.preventDefault()
      // this.$store.commit('setActiveLabel', this.label)
      // let labelSnippets = this.computeLabelSnippets(this.$store, this.$store.state.snippets)
      // this.$store.commit('setLabelSnippets', labelSnippets)
    },
    editSnippet() {

    },
    destroySnippet() {}
  }
}
</script>

<style scoped lang="scss">
  @import "~bulma-scss/bulma.scss";
  $menu-top-height: 45px;
  .card-description {
    padding: 1rem 0.75em;
  }

  .sidebar {
    width: auto;
    margin-bottom: 2em;

    .menu-content {
      padding-bottom: 0;
      max-height: calc(300px - #{$menu-top-height});
    }

    &.sidebar-snippets {
      border: none;
    }
  }

  .tool-container {
    padding-bottom: 10px;
    justify-content: space-between;
    background: #fff;
  }
</style>
