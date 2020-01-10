import Vue from 'vue'
import permission from './permission'
import waves from './waves'

const directives = [permission, waves]
directives.forEach(d => Vue.use(d))
