<template>
  <div class="app-container">
    <el-row :gutter="30">
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <el-card>
          <div slot="header" class="clearfix">
            <span>标签列表</span>
          </div>
          <ul class="meta-list">
            <li v-for="tag in tags" :key="tag.id">
              <span class="meta" @click="clickTag(tag.id, tag.name)">{{
                tag.name
              }}</span>
              <span style="float: right;clear: both">
                <span class="meta-count">{{ tag.count }}</span>
                <el-button
                  size="small"
                  type="danger"
                  @click="deleteTagHandle(tag.name)"
                >删除</el-button>
              </span>
            </li>
          </ul>
          <el-input
            v-model.trim="tagName"
            placeholder="请输入标签名称"
            class="meta-input"
          />
          <el-button
            style="float: right;clear: both"
            @click="saveOrUpdateTag"
          >保存标签
          </el-button>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <el-card>
          <div slot="header" class="clearfix">
            <span>分类列表</span>
          </div>
          <ul class="meta-list">
            <li v-for="category in categories" :key="category.id">
              <span
                class="meta"
                @click="clickCategory(category.id, category.name)"
              >{{ category.name }}</span>
              <span style="float: right;clear: both">
                <span class="meta-count">{{ category.count }}</span>
                <el-button
                  size="small"
                  type="danger"
                  @click="deleteCategoryHandle(category.name)"
                >删除</el-button>
              </span>
            </li>
          </ul>
          <el-input
            v-model.trim="categoryName"
            placeholder="请输入分类名称"
            class="meta-input"
          />
          <el-button
            style="float: right;clear: both"
            @click="saveOrUpdateCategory"
          >保存分类
          </el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
import { getAllCategories, getAllTags, saveCategory, saveTag, updateCategory, updateTag, deleteCategory, deleteTag } from '@/api/blog'

export default {
  data() {
    return {
      tags: [],
      categories: [],
      tagId: '',
      tagName: '',
      categoryId: '',
      categoryName: ''
    }
  },
  mounted() {
    this.getTags()
    this.getCategories()
  },
  methods: {
    getTags() {
      getAllTags().then(response => {
        for (const key in response.data) {
          this.tags.push(response.data[key])
        }
      })
    },
    getCategories() {
      getAllCategories().then(response => {
        for (const key in response.data) {
          this.categories.push(response.data[key])
        }
      })
    },
    clickTag(tagId, tagName) {
      this.tagId = tagId
      this.tagName = tagName
    },
    clickCategory(categoryId, categoryName) {
      this.categoryId = categoryId
      this.categoryName = categoryName
    },
    deleteTagHandle(tagName) {
      this.$confirm('确定删除该标签?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        deleteTag(tagName).then(() => {
          this.refreshTags()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
      }).catch(() => {
      })
    },
    deleteCategoryHandle(categoryName) {
      this.$confirm('确定删除该分类?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        deleteCategory(categoryName).then(() => {
          this.refreshCategories()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
      }).catch(() => {
      })
    },
    saveOrUpdateTag() {
      if (this.tagName === null || this.tagName === '') {
        this.$message({
          message: '标签名称不能为空',
          type: 'error'
        })
        return
      }
      if (this.tagId !== null && this.tagId !== '') {
        updateTag(this.tagId, this.tagName).then(() => {
          this.refreshTags()
          this.$message({
            message: '更新tag成功!',
            type: 'success'
          })
        })
      } else {
        saveTag(this.tagName).then(() => {
          this.refreshTags()
          this.$message({
            message: '新建tag成功!',
            type: 'success'
          })
        })
      }
    },
    saveOrUpdateCategory() {
      if (this.categoryName === null || this.categoryName === '') {
        this.$message({
          message: '分类名称不能为空',
          type: 'error'
        })
        return
      }
      if (this.categoryId !== null && this.categoryId !== '') {
        updateCategory(this.categoryId, this.categoryName).then(() => {
          this.refreshCategories()
          this.$message({
            message: '更新category成功!',
            type: 'success'
          })
        })
      } else {
        saveCategory(this.categoryName).then(() => {
          this.refreshCategories()
          this.$message({
            message: '新建category成功!',
            type: 'success'
          })
        })
      }
    },
    refreshTags() {
      this.tags = []
      this.getTags()
    },
    refreshCategories() {
      this.categories = []
      this.getCategories()
    }
  }
}
</script>

<style scoped>
.meta-list {
  margin: 0 0 30px 0;
  padding: 0;
  list-style: none;
}

.meta-list li {
  line-height: 2.4rem;
}

.meta-list .meta {
  box-shadow: 0 0 3px rgba(14, 14, 14, 0.3);
  margin: 0.4rem;
  max-width: 350px;
  padding: 0.4rem 0.5rem;
  white-space: nowrap;
  cursor: pointer;
  font-size: 14px;
  font-weight: 600;
  color: #333;
  border: 1px solid #ffd740;
  background-color: #ffd740;
}

.meta-list .meta:hover {
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
  transition: all 0.2s;
}

.meta-list .meta:active {
  box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
}

.meta-list .meta-count {
  display: inline-block;
  min-width: 10px;
  line-height: 12px;
  padding: 4px 7px;
  margin-right: 20px;
  font-size: 11px;
  font-weight: 700;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  background-color: #f36a5a;
  border-radius: 10px;
}

.meta-input {
  width: 200px;
  margin-left: 5px;
  display: inline-block;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}
</style>
