<template>
  <div class="task">
    <div class="task-header">
      <div>
        任务
        <input placeholder="请填写任务信息" v-model="taskDesc"/>
      </div>
      <div @click="addTask">添加新任务</div>
    </div>
    <ul>
      <li v-for="task in selectedTask" :key="task.desc">
        <input type="checkbox" v-model="task.state"/>
        <span :class="task.state?'del':''">{{ task.desc }}</span>
        <span :class="task.state?'green':'yellow'">{{ task.state ? '已完成' : '未完成' }}</span>
      </li>
    </ul>
    <div class="task-actions">
      <span :class="this.index===0?'select':''" @click="selected(0)">全部</span>
      <span :class="this.index===1?'select':''" @click="selected(1)">已完成</span>
      <span :class="this.index===2?'select':''" @click="selected(2)">未完成</span>
    </div>
  </div>
</template>
<script>
export default {
  name: "CustomTaskList",
  props: {
    taskList: Array
  },
  data() {
    return {
      index: 0,
      taskDesc: ""
    }
  },
  emits: ['update:taskList'],
  methods: {
    addTask() {
      if (!this.taskDesc || this.taskDesc.length <= 0) {
        return alert("任务描述不能为空")
      }
      this.taskList.push({
        desc: this.taskDesc,
        state: false
      });
      this.$emit('update:taskList', this.taskList);
    },
    selected(index) {
      this.index = index;
    }
  },
  computed: {
    selectedTask() {
      switch (this.index) {
        case 0:
          return this.taskList;
        case 1:
          return this.taskList.filter(x => x.state);
        case 2:
          return this.taskList.filter(x => !x.state);
      }
    }
  }

}
</script>
<style lang="less">
.task {
  .task-header {
    display: flex;

    input {
      border: none;
    }

    div:first-child {
      border: 1px solid lightgray;
      flex: 1;
      display: flex;
      padding-left: 5px;

      input {
        margin-left: 5px;
        flex: 1;
      }
    }

    div:last-child {
      margin-left: 10px;
      background-color: #226ed3;
      padding: 0 5px;
      border-radius: 3px;
      color: white;
    }
  }

  li {
    //display: block;
    height: 30px;
    align-items: center;
  }

  .del {
    text-decoration: line-through;
  }

  .green {
    background-color: lightgreen;
    padding: 0 3px;
    border-radius: 3px;
    color: white;
  }

  .yellow {
    background-color: yellow;
    padding: 0 3px;
    border-radius: 3px;
  }

  .task-actions {
    span {
      padding: 2px 5px;
      background-color: lightslategray;
    }

    .select {
      background-color: #0d6efd;
    }
  }
}
</style>