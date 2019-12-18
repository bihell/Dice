export default (fieldList) => {
  const obj = {}
  const fields = fieldList.filter(item => !item.isHidden)
  for (const item of fields) {
    obj[item.prop] = (item.type === 'checkbox' || (item.type === 'select' && item.multi)) ? [] : ''
  }
  return obj
}
