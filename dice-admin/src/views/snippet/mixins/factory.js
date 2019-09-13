export default {
  methods: {
    factory() {
      return {
        snippet: {
          id: null,
          title: '',
          description: '',
          label: '',
          snippetFiles: [{
            title: '',
            content: '',
            language: 'automatically',
            tabs: 4
          }]
        }
      }
    }
  }
}
