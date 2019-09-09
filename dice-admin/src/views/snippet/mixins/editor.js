import { processIosEditorHeight } from '../../../utils/editor_helper'
import { editorHeightDelta } from '../../../utils/variables'

export default {
  data() {
    return {
      languageOptions: {
        'automatically': 'Automatically',
        'c': 'C',
        'cpp': 'C++',
        'cs': 'C#',
        'clojure': 'Clojure',
        'coffeescript': 'CoffeeScript',
        'crystal': 'Crystal',
        'css': 'CSS',
        'bash': 'Bash',
        'd': 'D',
        'dart': 'Dart',
        'django': 'Django',
        'dockerfile': 'Dockerfile',
        'elm': 'Elm',
        'erlang': 'Erlang',
        'fortran': 'Fortran',
        'go': 'Go',
        'groovy': 'Groovy',
        'haml': 'HAML',
        'handlebars': 'Handlebars',
        'haskell': 'Haskell',
        'html': 'HTML',
        'http': 'HTTP',
        'java': 'Java',
        'javascript': 'JavaScript',
        'julia': 'Julia',
        'less': 'LESS',
        'livescript': 'Livescript',
        'lua': 'Lua',
        'markdown': 'Markdown',
        'nginx': 'Nginx',
        'objectivec': 'Objective-C',
        'perl': 'Perl',
        'php': 'PHP',
        'powershell': 'Powershell',
        'plain': 'Plain Text',
        'puppet': 'Puppet',
        'python': 'Python',
        'r': 'R',
        'ruby': 'Ruby',
        'rust': 'Rust',
        'scss': 'SCSS',
        'stylus': 'Stylus',
        'smalltalk': 'Smalltalk',
        'sql': 'SQL',
        'swift': 'Swift',
        'tcl': 'Tcl',
        'twig': 'Twig',
        'xml': 'XML',
        'xquery': 'XQuery',
        'yaml': 'YAML'
      },
      tabOptions: {
        2: '2 spaces',
        4: '4 spaces',
        8: '8 spaces'
      }
    }
  },

  computed: {
    editorHeight() {
      return processIosEditorHeight() ? document.querySelector('body').offsetHeight - editorHeightDelta + 'px' : undefined
    }
  }
}
