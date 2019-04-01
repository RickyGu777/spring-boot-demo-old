<template>
  <div>
    <tr v-for="(order, index) in orders" :key="index">
      <td>{{order}}</td>
      <td>{{index}}</td>
      <td>
        <button type="button" @click="fordelete(index)">用索引删除</button>
        <button type="button" @click="deleteByID(order.id)">用 ID 删除</button>
      </td>
    </tr>
  </div>
</template>

<script>
  export default {
    name: "Test",
    data() {
      return {
        orders: [
          {
            name: 1, id: 1
          },
          {
            name: 2, id: 2
          },
          {
            name: 3, id: 3
          }]
      }
    },
    methods: {
      fordelete(index) {
        // 用索引删除
        this.orders.splice(index, 1);
      },
      deleteByID(id) {
        this.orders.splice(this.orders.find(order => {
          return order.id === id;
        }), 1);
      }
    }
  }
</script>
