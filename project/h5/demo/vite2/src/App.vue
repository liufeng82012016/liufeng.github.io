<template>
  <h1>parent1</h1>
  <!--  <img alt="Vue logo" src="./assets/logo.png"/>-->
  <HelloWorld msg="Hello Vue 3.0 + Vite"/>
  <!--  全局组件名称要保持一致-->
  <CustomComp1 msg="10"></CustomComp1>
  <CustomComp2 msg="20" thin="false"></CustomComp2>
  <h1>parent2</h1>
  <Header title="header comp" bgcolor="dodgerblue" color="white"></Header>
  <hr>
  <ComputeDemo></ComputeDemo>
  <hr>
  <!--  3. 监听自定义事件-->
  <CustomEvent @countChange="getCount"></CustomEvent>
  <hr>
  <!--父组件中使用v-bind属性绑定的形式，将数据传递给子组件-->
  <CustomCompModel v-model:number="modelCount"></CustomCompModel>
  <span>modelCount:{{ modelCount }}</span>
  <button @click="modelCount+=1">+1</button>
  <hr>
  <CustomTaskList v-model:taskList="taskList"></CustomTaskList>
  <!--  <span>{{ serial }}</span>-->
  <hr>
  <Watch></Watch>
  <LifeCycle v-if="lifeCycleOpen"></LifeCycle>
  <button @click="lifeCycleOpen=!lifeCycleOpen">切换生命周期</button>
  <hr>
  <EventBusLeft></EventBusLeft>
  <br>
  <EventBusRight></EventBusRight>

  <hr>
  <span>father.name:{{ name }} father.author:{{ author }} father.bookCount:{{ bookCount }}</span>
  <button @click="bookCount++">+1</button>
  <br>
  <SecondComp></SecondComp>
  <hr>
  <AxiosGet></AxiosGet>
  <AxiosPost></AxiosPost>

</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import CustomComp2 from './components/CustomComp2.vue'
import CustomComp1 from './components/CustomComp1.vue'
import Header from './components/Header.vue'
import ComputeDemo from './components/ComputeDemo.vue'
import CustomEvent from './components/CustomEvent.vue'
import CustomCompModel from './components/CustomCompModel.vue'
import CustomTaskList from './components/CustomTaskList.vue'
import Watch from './components/Watch.vue'
import LifeCycle from './components/LifeCycle.vue'
import EventBusLeft from './components/EventBusLeft.vue'
import EventBusRight from './components/EventBusRight.vue'
import SecondComp from './components/SecondComp.vue'
import AxiosGet from './components/AxiosGet.vue'
import AxiosPost from './components/AxiosPost.vue'
import {computed} from 'vue'

export default {
  name: 'App',
  components: {
    // 注解使用组件的name属性注册
    HelloWorld,
    CustomComp1,
    Header,
    ComputeDemo,
    CustomEvent,
    CustomCompModel,
    CustomTaskList,
    Watch,
    LifeCycle,
    EventBusLeft,
    EventBusRight,
    SecondComp,
    AxiosGet,
    AxiosPost,
    // 局部注册组件
    "CustomComp2": CustomComp2
  },
  data() {
    return {
      modelCount: 0,
      lifeCycleOpen: true,
      taskList: [
        {
          desc: "周一早晨九点开会",
          state: false
        },
        {
          desc: "周一早晨九点开会",
          state: true
        }
      ],
      name: "西游记",
      author: "吴承恩",
      bookCount: 0,
    }
  },
  // 向子孙组件共享数据，默认不是响应式的数据，可以使用computed
  provide() {
    return {
      name: this.name,
      author: this.author,
      bookCount: computed(() => this.bookCount),
    }
  },
  methods: {
    // 4. 处理自定义事件
    getCount(val) {
      console.log("触发了countChange自定义事件,val:", val)
    }
  },
  computed: {
    serial() {
      return JSON.stringify(this.taskList);
    }
  }
}
</script>
<style lang="css">
/*加了scoped应该不会生效，为什么依然生效?如果子组件只有一个标签，scoped不生效，多个标签就会生效？？hash值计算吗*/
:deep(.title) {
  color: red;
}
</style>