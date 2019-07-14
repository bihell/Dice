<template>
  <div id="comment" class="comment">
    <transition-group name="list" tag="span">
      <div :key="1" class="comment-header">
        <span class="total">{{ total }}条评论</span>
        <span class="line"></span>
      </div>
      <div :key="2" class="comment-box">
        <form @submit="submitComment">
          <div class="comment-edit">
            <div class="gravatar gravatar-comment">
              <img :src="user.gravatar || '/avator.jpg'" alt="avatar" />
            </div>
            <div class="editor">
              <transition-group id="reply" tag="div" name="list">
                <div v-if="isReply" :key="1" class="reply">
                  <div class="reply-name">
                    <span>回复:{{ replyComment.name }}</span>
                    <a @click="closeReply"><span class="icon-close"></span></a>
                  </div>
                  <div
                    v-highlight
                    class="reply-preview markdown-body"
                    v-html="replyComment.content"
                  ></div>
                </div>
                <div :key="2" class="markdown">
                  <div
                    ref="content"
                    class="markdown-editor"
                    :class="{ editing: isEdit }"
                    contenteditable="true"
                    :placeholder="placeHolder"
                    @keyup="commentEditListen($event)"
                    @blur="commentBlurListen"
                    @click="commentClickListen"
                  ></div>
                </div>
              </transition-group>
            </div>
          </div>
          <div class="comment-user">
            <div class="user-input">
              <div class="name">
                <input
                  v-model="user.name"
                  required="required"
                  type="text"
                  name="name"
                  placeholder="称呼（必填）"
                  maxlength="10"
                />
              </div>
              <div class="email">
                <input
                  v-model="user.email"
                  type="email"
                  name="email"
                  placeholder="邮箱（非必填，不会公开）"
                  maxlength="50"
                  @blur="updateUserGravatar"
                />
              </div>
              <div class="website">
                <input
                  v-model="user.website"
                  type="url"
                  name="url"
                  placeholder="网站（http, https:// 开头，非必填）"
                  maxlength="50"
                />
              </div>
            </div>
            <button type="submit" class="submit" :disabled="isComment">
              <span>{{ isComment ? '发布中...' : '发布' }}</span
              ><i class="icon-rocket"></i>
            </button>
          </div>
        </form>
      </div>
      <div :key="3" class="comment-body">
        <transition-group name="list" tag="ul" class="comment-list">
          <li
            v-for="comment in comments"
            :key="comment.id"
            class="comment-item"
          >
            <div class="gravatar">
              <img :src="gravatar(comment.email)" alt="avatar" />
            </div>
            <div class="comment-item-body">
              <div class="comment-item-header">
                <a class="user-name">{{ comment.name }}</a>
                <span class="comment-date">{{
                  comment.created | time('yyyy.MM.dd hh:mm')
                }}</span>
              </div>
              <div v-highlight class="comment-item-content markdown-body">
                <div
                  v-if="comment.pId !== -1 && comment.pComment"
                  class="comment-item-reply-box"
                >
                  <a class="user-name">{{ comment.pComment.name }}</a>
                  <div
                    class="comment-item-replay-content"
                    v-html="comment.pComment.content"
                  ></div>
                </div>
                <div v-html="comment.content"></div>
              </div>
              <div class="comment-item-footer">
                <a
                  class="comment-item-agree"
                  :class="{ active: isAgree(comment.id) }"
                  @click="assessComment(comment.id, 'agree')"
                  ><span class="icon-thumbs-o-up"></span
                  ><span>顶({{ comment.agree }})</span></a
                >
                <a
                  class="comment-item-disagree"
                  :class="{ active: isDisagree(comment.id) }"
                  @click="assessComment(comment.id, 'disagree')"
                  ><span class="icon-thumbs-o-down"></span
                  ><span>踩({{ comment.disagree }})</span></a
                >
                <a
                  class="comment-item-reply"
                  @click="replyFor(comment.id, comment.name, comment.content)"
                  ><span class="icon-reply"></span><span>回复</span></a
                >
              </div>
            </div>
          </li>
        </transition-group>
      </div>
    </transition-group>
  </div>
</template>
<script type="text/ecmascript-6">
const defaultPlaceholder = '写下你的评论,支持markdown语法哟...'
// 设置999不分页
const defaultLimit = 999
const defaultPage = 1
export default {
  props: {
    articleId: {
      type: [String, Number],
      required: true
    }
  },
  data: function() {
    return {
      commentAgrees: [],
      commentDisagrees: [],
      commentContent: '',
      user: {
        name: '',
        email: '',
        website: '',
        gravatar: ''
      },
      isEdit: false,
      isComment: false,
      isReply: false,
      replyComment: {
        id: -1,
        name: '',
        content: ''
      },
      placeHolder: defaultPlaceholder
    }
  },
  computed: {
    comments () {
      return this.$store.state.comment.pagination.list
    },
    total () {
      return this.$store.state.comment.pagination.total
    }
  },
  mounted () {
    this.loadComments()
    this.init()
    this.commentPasteListen()
  },
  methods: {
    // 跳转到某条指定的id位置
    toSomeAnchorById (id) {
      this.$util.goAnchor(id, 120, 60)
    },
    gravatar (email) {
      return this.$gravarar.url(email, { s: '36', d: 'retro' })
    },
    updateUserGravatar () {
      if (this.user.email) {
        this.user.gravatar = this.gravatar(this.user.email)
      }
    },
    commentPasteListen () {
      document.addEventListener('copy', e => {
        if (!window.getSelection) return
        const content = window.getSelection().toString()
        e.clipboardData.setData('text/plain', content)
        e.preventDefault()
      })
    },
    commentClickListen () {
      this.isEdit = true
      this.placeHolder = ''
    },
    commentBlurListen () {
      this.isEdit = false
      if (!this.commentContent || this.commentContent === '') {
        this.placeHolder = defaultPlaceholder
      }
    },
    commentEditListen ($event) {
      this.isEdit = true
      this.commentContent = this.$refs.content.innerText
      if (this.commentContent !== '') {
        this.placeHolder = ''
      }
    },
    closeReply () {
      this.isReply = false
      this.replyComment.id = -1
      this.replyComment.content = ''
      this.replyComment.name = ''
    },
    replyFor (id, name, content) {
      this.isReply = true
      this.replyComment.id = id
      this.replyComment.content = content
      this.replyComment.name = name
      this.toSomeAnchorById('reply')
    },
    clearComment () {
      this.commentContent = ''
      this.isEdit = false
      this.isComment = false
      this.isReply = false
      this.replyComment = {
        id: -1,
        name: '',
        content: ''
      }
      this.$refs.content.innerHTML = ''
      this.placeHolder = defaultPlaceholder
    },
    isAgree (commentId) {
      return this.commentAgrees.includes(commentId)
    },
    isDisagree (commentId) {
      return this.commentDisagrees.includes(commentId)
    },
    async assessComment (commentId, assess) {
      if (assess === 'agree') {
        if (this.isAgree(commentId)) return
      }
      if (assess === 'disagree') {
        if (this.isDisagree(commentId)) return
      }
      let res = null
      if (assess === 'agree') {
        res = await this.$store.dispatch('agreeComment', commentId)
      }
      if (assess === 'disagree') {
        res = await this.$store.dispatch('disagreeComment', commentId)
      }
      if (res && res.success) {
        if (localStorage) {
          if (assess === 'agree') {
            this.commentAgrees.push(commentId)
            localStorage.setItem('COMMENT_AGREE', JSON.stringify(this.commentAgrees))
          } else if (assess === 'disagree') {
            this.commentDisagrees.push(commentId)
            localStorage.setItem('COMMENT_DISAGREE', JSON.stringify(this.commentDisagrees))
          }
        }
      } else {
        if (assess === 'agree') {
          alert('赞评论失败')
        }
        if (assess === 'disagree') {
          alert('踩评论失败')
        }
      }
    },
    async submitComment ($event) {
      $event.preventDefault()
      if (this.isComment) {
        alert('请等待发布完成')
      }
      let html = this.commentContent
      if (!html || html === '') {
        return alert('请填写回复内容')
      }
      // contenteditable内容转义
      html = html.replace(/<div>/g, '\n')
      html = html.replace(/<\/div>/g, '')
      html = html.replace(/&nbsp;/g, ' ')
      const lineOverflow = this.commentContent.split('\n').length > 36
      const lengthOverflow = this.commentContent.length > 1000
      if (lineOverflow || lengthOverflow) {
        return alert('请回复内容不超过1000字/36行')
      }
      this.isComment = true
      const res = await this.$store.dispatch('submitComment', {
        articleId: this.articleId,
        replyCommentId: this.replyComment.id,
        content: html,
        name: this.user.name,
        email: this.user.email,
        website: this.user.website
      })
      this.isComment = false
      if (res.success) {
        this.loadComments()
        this.clearComment()
        this.init()
      } else {
        alert(res.msg)
      }
    },
    async loadComments () {
      await this.$store.dispatch('getComments', {
        articleId: this.articleId,
        page: defaultPage,
        limit: defaultLimit
      })
    },
    init () {
      if (localStorage) {
        const commentAgrees = localStorage.getItem('COMMENT_AGREE')
        const commentDisagrees = localStorage.getItem('COMMENT_DISAGREE')
        if (commentAgrees) {
          this.commentAgrees = JSON.parse(commentAgrees)
        }
        if (commentDisagrees) {
          this.commentDisagrees = JSON.parse(commentDisagrees)
        }
      }
    }
  }
}
</script>

<style>
#comment .comment-item-body .markdown-body img {
  margin: 0.5rem 0;
  max-width: 100%;
  border-radius: 2px;
}
</style>

<style scoped>
.comment {
  font-size: 14px;
}

.comment-header {
  position: relative;
  display: flex;
  padding: 16px 0;
  align-items: center;
  justify-content: space-between;
}

.comment-header .total {
  position: relative;
  padding-right: 15px;
  color: #24292e;
  background: #fff;
  font-weight: 500;
  z-index: 99;
}

.comment-header .line {
  content: '';
  position: absolute;
  left: 0;
  right: 0;
  height: 1px;
  color: #eee;
  background: currentColor;
}

.comment-edit {
  width: 100%;
  display: flex;
}

.gravatar {
  display: block;
  margin-bottom: 5px;
  width: 36px;
  height: 36px;
}

.gravatar-comment {
  margin-right: 10px;
}

.gravatar img {
  width: 100%;
  height: 100%;
  transition: transform 0.5s ease-out;
  border-radius: 4px;
}

.comment-edit .editor {
  flex-grow: 1;
  position: relative;
  max-width: calc(100% - 56px);
}

.comment-edit .editor .reply {
  font-size: 14px;
  margin-bottom: 15px;
}

.comment-edit .editor .reply .reply-name {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
  padding: 0 15px;
  height: 30px;
  line-height: 30px;
  border: 1px solid #eee;
  border-radius: 4px;
}

.comment-edit .editor .reply .reply-preview {
  max-height: 10em;
  overflow: auto;
  padding: 1rem;
  border: 1px solid #eee;
  border-radius: 4px;
}

.comment-edit .editor .markdown {
  position: relative;
  overflow: hidden;
}

.comment-edit .editor .markdown .markdown-editor {
  min-height: 70px;
  max-height: 300px;
  overflow: auto;
  outline: none;
  padding: 0.5em;
  cursor: auto;
  line-height: 16px;
  border: 1px solid #eee;
  border-radius: 4px;
  transition: all 0.25s;
}

.comment-edit .editor .markdown .markdown-editor:hover {
  border: 1px solid #8391a5;
}

.comment-edit .editor .markdown .markdown-editor:before {
  content: attr(placeholder);
  color: rgba(0, 0, 0, 0.38);
}

[contenteditable] {
  caret-color: #24292e;
}

.comment-user {
  display: inline-flex;
  flex-wrap: wrap;
  justify-content: space-between;
  width: calc(100% - 56px);
  padding-left: 50px;
  margin-top: 10px;
  line-height: 32px;
}

.comment-user div {
  margin-right: 16px;
}

.comment-user .name,
.comment-user .email,
.comment-user .website {
  font-family: Microsoft YaHei, Arial, Helvetica, sans-serif;
  flex-grow: 1;
}

.comment-user .user-input {
  display: inline-flex;
  flex-wrap: wrap;
}

.comment-user input {
  width: 100%;
  height: 16px;
  padding: 5px;
  background: transparent;
  caret-color: #24292e;
  color: #555;
  display: inline-block;
  outline: none;
  border: 1px solid #eee;
  border-radius: 4px;
}

.comment-user input:hover {
  border-color: #8391a5;
}

.comment-user input:focus {
  border-color: #24292e !important;
}

.comment-box button {
  height: 32px;
  border: 0;
  padding: 0 8px;
  border-radius: 4px;
  color: #8391a5;
  text-align: center;
  background: transparent;
  outline: none;
  white-space: nowrap;
  cursor: pointer;
}

.comment-box button:hover {
  color: #24292e;
  background: rgba(0, 0, 0, 0.12);
}

.comment-box button span {
  margin-right: 8px;
}

.editing {
  border-color: #24292e !important;
}

.comment-body {
  margin-top: 16px;
}

.comment-item {
  margin: 20px 5px;
  width: 100%;
  display: flex;
}

.comment-item-body {
  flex-grow: 1;
  position: relative;
  padding: 0 10px;
  max-width: calc(100% - 56px);
}

.comment-item-body .comment-item-header {
  display: flex;
  justify-content: space-between;
  position: relative;
}

.comment-item-body .user-name {
  color: rgba(0, 0, 0, 0.54);
  font-weight: 700;
  font-size: 12px;
  margin-right: 5px;
  font-family: Microsoft YaHei, Arial, Helvetica, sans-serif;
}

.comment-item-header a:hover {
  text-decoration: underline;
}

.comment-item-body .comment-item-content {
  line-height: 30px;
  margin: 10px 0;
  word-wrap: break-word;
}

.comment-item-content .comment-item-reply-box {
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #eee;
  border-radius: 4px;
}

.comment-item-content .comment-item-reply-box .comment-item-replay-content {
  margin-top: 10px;
}

.comment-item-body .comment-item-footer {
  display: flex;
  align-items: center;
  position: relative;
}

.comment-item-body .comment-item-footer a {
  color: #8391a5;
  font-size: 10px;
  margin-right: 15px;
  opacity: 0.8;
}

.comment-item-body .comment-item-footer a:hover,
.comment-item-body .comment-item-footer .active {
  color: #ea6f5a;
}

.comment-item-body .comment-item-footer a span {
  margin: 2px;
}

*,
:hover {
  transition: all 0.25s;
}
</style>
