<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input
          placeholder="Lower than 1000"
          v-model="number1"
          :disabled="true">
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button @click="randomIntegerNumbers()">Generate Integer Random Numbers</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input
          v-model="number2"
          :disabled="true">
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button @click="randomIntegerNumbersFromTo()">Generate Integer Random Numbers</el-button>
      </el-col>
      <el-col :span="1">
        From
      </el-col>
      <el-col :span="2">
        <el-input
          v-model="from">
        </el-input>
      </el-col>
      <el-col :span="1">
        To
      </el-col>
      <el-col :span="2">
        <el-input
          v-model="to">
        </el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Random",
    data() {
      return {
        number1: null,
        number2: null,
        from: null,
        to: null
      };
    },
    methods: {
      randomIntegerNumbers() {
        this.number1 = Math.floor(Math.random() * 1000);
      },
      randomIntegerNumbersFromTo() {
        if (this.from < 0 || this.to < 0) {
          this.$message.error('数值必须大于0');
        }
        if (this.from > this.to) {
          this.$message.error('数值范围错误');
        }
        this.number2 = Math.floor(Math.random() * (this.to - this.from + 2)  + this.from);
      }
    }
  }
</script>

<style scoped>

</style>
