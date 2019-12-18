export default (fieldList) => {
  const obj = {}
  const fields = fieldList.filter(item => !item.isHidden)
  for (const item of fields) {
    const type = (item.type === 'select' || item.type === 'date' || item.type === 'radio' || item.type === 'checkbox') ? '选择' : '输入'
    const eventName = item.type === 'date' ? 'change' : 'blur'
    obj[item.prop] = []
    // 是否必填
    if (item.required) {
      obj[item.prop].push({
        required: item.required,
        message: '请' + type + item.label,
        trigger: eventName
      })
    }

    if (item.validator) {
      obj[item.prop].push({
        validator: item.validator,
        trigger: eventName
      })
    }

    if (item.min && !item.max) {
      obj[item.prop].push({
        min: item.min,
        message: `至少要有 ${item.min} 个字符`,
        trigger: eventName
      })
    }

    if (item.max && !item.min) {
      obj[item.prop].push({
        max: item.max,
        message: `至多 ${item.max} 个字符`,
        trigger: eventName
      })
    }

    if (item.min && item.max) {
      obj[item.prop].push({
        min: item.min,
        max: item.max,
        message: `长度需要在 ${item.min} 和 ${item.max} 个字符`,
        trigger: eventName
      })
    }

    if (item.pattern) {
      obj[item.prop].push({
        pattern: item.pattern,
        message: '格式不正确',
        trigger: eventName
      })
    }
  }
  return obj
}
