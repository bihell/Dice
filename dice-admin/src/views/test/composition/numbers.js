import { ref, reactive, computed, watch, watchEffect, onMounted } from 'vue';

export function useNumbers() {
  // ref 比较适合单个值
  const count = ref(0);
  const a = ref(0);
  const b = ref(0);
  const history = ref([]);

  // reactive 比较适合复杂值，对象之类
  const numbers = reactive({
    a: 0,
    b: 0,
  });

  const increase = (num) => {
    numbers[num] += 1;
  };
  const increment = () => {
    count.value += 1;
  };

  // watch(numbers,(newVal) => {
  //   console.log(newVal.a,newVal.b)
  // },{ immediate:true})
  //
  // 只看变更了的数据
  watchEffect(() => {
    console.log(numbers.a);
  });

  // 变更前变更后,只作用域ref变量
  watch(count, (newVal, oldVal) => {
    console.log(newVal, oldVal);
  });

  watch([a, b], ([newA, newB], [oldA, oldB]) => {
    if (newA !== oldA) {
      history.value.push(`a:${oldA} -> ${newA}`);
    }
    if (newB !== oldB) {
      history.value.push(`a:${oldB} -> ${newB}`);
    }
  });

  // const total = computed(() => { return count.value + numbers.a + numbers.b});
  const total = computed(() => count.value + numbers.a + numbers.b);

  onMounted(() => {
    console.log('Mounted!!!');
  });

  return {
    a,
    b,
    count,
    history,
    increase,
    total,
    increment,
    numbers,
  };
}
