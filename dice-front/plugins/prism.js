import Prism from 'prismjs'
import 'prismjs/components/prism-java'
import 'prismjs/components/prism-xml-doc'
import 'prismjs/components/prism-go'
import 'prismjs/components/prism-css'
import 'prismjs/components/prism-sql'
import 'prismjs/components/prism-json'
import 'prismjs/components/prism-bash'
import 'prismjs/components/prism-less'
import 'prismjs/components/prism-scss'
import 'prismjs/components/prism-powershell'
import 'prismjs/components/prism-nginx'
import 'prismjs/components/prism-stylus'
import 'prismjs/components/prism-yaml'
import 'prismjs/components/prism-python'
import 'prismjs/components/prism-javascript'
import 'prismjs/components/prism-typescript'
import 'prismjs/components/prism-properties'

export function prism(el) {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach((block) => {
    Prism.highlightElement(block)
  })
}
