<template>
  <el-container style="height: 100%">
    <el-header>
      <h1>健康守护360°智慧体检平台</h1>


      <el-dropdown size="large" split-button type="primary">  
        <h2>医生：{{ state.doctor.realName }}</h2>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-header>
    <el-container>

      <el-col :span="3">

        <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-menu-item index="1" @click="showForm = true">
            <template #title>
              <el-icon>
                <Search />
              </el-icon>
              <span>筛选</span>
            </template>
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon>
              <Stamp />
            </el-icon>
            <span>管理医院信息</span>
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon>
              <Film />
            </el-icon>
            <span>管理套餐信息</span>
          </el-menu-item>
        </el-menu>


      </el-col>




      <!-- <el-aside width="260px" :style="{ transform: `translateX(${isCollapsed ? '-100%' : '0'})` }">
        <h4>体检用户查询</h4>
        <el-form ref="formRef" :model="state.selectForm" label-width="auto">
          <el-form-item label="手机号码">
            <el-input v-model="state.selectForm.userId" placeholder="手机号码"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="state.selectForm.realName" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="state.selectForm.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="套餐类型">
            <el-select v-model="state.selectForm.smId" placeholder="套餐类型">
              <el-option v-for="setmeal in state.setmealArr" :key="setmeal.smId" :label="setmeal.name"
                :value="setmeal.smId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="体检日期">
            <el-date-picker v-model="state.selectForm.orderDate" type="date" placeholder="体检日期" style="width: 100%"
              format="YYYY/MM/DD" value-format="YYYY-MM-DD"></el-date-picker>
          </el-form-item>
          <el-form-item label="是否归档">
            <el-radio-group v-model="state.selectForm.state">
              <el-radio border label="1">未归档</el-radio>
              <el-radio border label="2">已归档</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="doSelect">查询</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </el-aside>
 -->

      <el-main :class="{ 'full-width': isCollapsed }">
        <el-table :data="state.ordersPageResponseDto.data" style="width: 100%">
          <el-table-column prop="orderId" label="预约编号" width="120" />
          <el-table-column prop="userId" label="手机号码" width="140" />
          <el-table-column prop="realName" label="真实姓名" width="120" />
          <el-table-column label="性别" width="60">
            <template #default="scope">
              <span>{{ scope.row.sex == 1 ? "男" : "女" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="smName" label="套餐类型" />
          <el-table-column prop="hpName" label="体检医院" width="220" />
          <el-table-column prop="orderDate" label="体检日期" />
          <el-table-column label="操作" width="120">
            <template #default="scope">
              <el-button type="text" size="small" @click="ciReport(scope.row)">{{ scope.row.state == 1 ? '编辑体检报告' :
                '查看体检报告' }}</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="prev, pager, next, total" :total="state.ordersPageResponseDto.total  "
          :page-size="10" style="margin-top: 20px" @current-change="currentChange">
        </el-pagination>
      </el-main>
    </el-container>

  </el-container>

  <el-dialog v-model="showForm" title="体检用户查询" width="50%" @close="onClose">
    <el-form ref="formRef" :model="state.selectForm" label-width="auto">
      <el-form-item label="手机号码">
        <el-input v-model="state.selectForm.userId" placeholder="手机号码"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="state.selectForm.realName" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="state.selectForm.sex">
          <el-radio label="1">男</el-radio>
          <el-radio label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="套餐类型">
            <el-select v-model="state.selectForm.smId" placeholder="套餐类型">
              <el-option v-for="setmeal in state.setmealArr" :key="setmeal.smId" :label="setmeal.name"
                :value="setmeal.smId"></el-option>
            </el-select>
      </el-form-item>
      <el-form-item label="体检日期">
        <el-date-picker v-model="state.selectForm.orderDate" type="date" placeholder="体检日期" style="width: 100%"
          format="YYYY/MM/DD" value-format="YYYY-MM-DD"></el-date-picker>
      </el-form-item>
      <el-form-item label="是否归档">
        <el-radio-group v-model="state.selectForm.state">
          <el-radio label="1">未归档</el-radio>
          <el-radio label="2">已归档</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="warning" @click="reset">重置</el-button>
        <el-button @click="showForm = false">取消</el-button>
        <el-button type="primary" @click="doSelect">查询</el-button>
      </span>
    </template>
  </el-dialog>


  

</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { getSessionStorage,setSessionStorage} from '../common.js';

import { Location, Search, Stamp, Film } from '@element-plus/icons-vue';
import { inject } from 'vue';

const axios = inject('axios');
const router = useRouter();

// 使用 reactive 管理可变状态
const state = reactive({
  doctor: getSessionStorage('doctor'),
  selectForm: {
    userId: '',
    realName: '',
    sex: '',
    smId: '',
    orderDate: '',
    state: '1',
  },
  setmealArr: [],
  ordersPageResponseDto: {},
});

// 控制对话框显示状态
const showForm = ref(false);
// 关闭对话框时重置表单
const onClose = () => {
  Object.keys(state.selectForm).forEach(key => {
    state.selectForm[key] = '';
  });
};

//退出登录
const logout = () => {
  setSessionStorage('doctor', null);
  axios.get('/api/doctor/exit')
  router.push('/')
}

// 关闭对话框前的回调
const handleClose = (done) => {
  ElMessageBox.confirm('确定要关闭吗？')
    .then(() => {
      done();
    })
    .catch(() => { });
};

// 定义方法
const listSetmeal = () => {
  axios.get('/api/setmeal/getList')
    .then(response => {
      state.setmealArr = response.data.data;
    })
    .catch(error => {
      console.error(error);
    });
};

const listOrders = (pageNum) => {
  
  
  axios.post('/api/orders/searchList?pageNum='+pageNum+'&maxPageNum=10' ,state.selectForm)
    .then(response => {
      state.ordersPageResponseDto = response.data;
      console.log(state.ordersPageResponseDto);
    })
    .catch(error => {
      console.error(error);
    });
};

const ciReport = (row) => {
  axios.get('/api/orders/getorder?orderId='+row.orderId
    
  )
    .then(response => {
      if (response.data != null) {
        router.push({ path: '/ordersContent', query: { orderId: row.orderId } });
      } else {
        alert('生成报告模板失败！');
      }
    })
    .catch(error => {
      console.error(error);
    });
};

const doSelect = () => {
  console.log(state.selectForm);
  showForm.value = false;
  listOrders(1);

};

const currentChange = (pageNum) => {
  listOrders(pageNum);
};

const reset = () => {
  state.selectForm = {
    userId: '',
    realName: '',
    sex: '',
    smId: '',
    orderDate: '',
    state: '1',
  };
};

// 生命周期钩子
onMounted(() => {
  listSetmeal();
  listOrders(1);
});
</script>

<style scoped lang="scss">
$primary-color: #2196F3; // 清新蓝色
$secondary-color: #64B5F6; // 柔和蓝
$text-color: #333;
$background-color: #F8F9FA; // 轻盈灰白
$border-color: rgba(0, 0, 0, 0.1);

// 全局字体设置
html {
  font-family: 'Microsoft YaHei', Arial, sans-serif;
}

.el-header {
  background-color: $primary-color;
  color: #fff;
  padding: 0 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;

  h1 {
    font-size: 28px;
    font-weight: bold;
    margin: 0;
  }

  p {
    font-size: 16px;
    color: rgba(255, 255, 255, 0.8);
  }
}

.el-aside {
  background-color: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  padding: 24px;

  h4 {
    color: $text-color;
    margin-bottom: 20px;
    font-size: 18px;
    font-weight: 600;
  }
}

.el-form {
  max-width: 300px;
  margin: 0 auto;
}

.el-form-item {
  margin-bottom: 16px;
}

.el-input,
.el-select,
.el-date-picker {
  width: 100%;
  border-radius: 4px;
}

.el-table {
  margin-top: 32px;

  th,
  td {
    padding: 16px 0;
    border-color: transparent;
  }

  .el-button--text {
    color: $primary-color;
    transition: color 0.3s ease;
  }

  .el-button--text:hover {
    color: darken($primary-color, 10%);
  }
}

.el-pagination {
  margin-top: 48px;
  text-align: center;
}

.el-main {
  background-color: $background-color;
  padding: 40px;
  min-height: calc(100vh - 120px); // 确保内容区域填满可用空间
}

// 交互反馈
.el-input__inner:focus,
.el-select .el-input__inner:focus,
.el-date-editor .el-input__inner:focus {
  border-color: $primary-color !important;
  outline: none;
}
.el-main {
  /* 限制主区域的最大高度，使其在达到此高度时出现滚动条 */
  max-height: calc(100vh - 200px); /* 举例：减去头部和底部高度，根据实际情况调整 */
  overflow-y: auto; /* 当内容溢出时，自动显示垂直滚动条 */
}

//图标
@import url('https://unpkg.com/tailwindcss@^2.0/dist/tailwind.min.css');
</style>