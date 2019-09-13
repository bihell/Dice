import hljs from 'highlight.js'
import { getPropertyOrDefault } from './tools'

const hljsModesMatrix = {
  automatically: '',
  c: 'cpp',
  html: 'xml'
}

export const highlightMarkdownCodeBlocks = (component) => {
  if (component.snippetFile.language === 'markdown') {
    component.$el.querySelectorAll('pre code[class]').forEach((block, i) => {
      hljs.highlightBlock(block)
    })
  }
}

export const processHljsMode = (mode) => {
  return getPropertyOrDefault(hljsModesMatrix, mode)
}
