<template>
  <el-container style="height: 100%">
    <el-header>
      <h1>健康守护360°智慧体检平台</h1>
      <p>医生：{{11 }}</p>
    </el-header>
    <el-container>
      <el-aside width="260px">
        <el-descriptions
          class="margin-top"
          title="预约客户信息"
          :column="1"
          border
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">预约编号</div>
            </template>
            {{ orders.orderId }}
          </el-descriptions-item>
          <el-descriptions-item >
            <template #label>
              <div class="cell-item">手机号码</div>
            </template>
            {{ orders.userId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">真实姓名</div>
            </template>
            {{ orders.realName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">性别</div>
            </template>
            {{ orders.sex == 1 ? "男" : "女" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">套餐类型</div>
            </template>
            {{ orders.smName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">体检日期</div>
            </template>
            {{ orders.orderDate }}
          </el-descriptions-item>
        </el-descriptions>
        <el-button type="primary" style="margin-top: 20px" @click="toOrdersList"
          >查询体检用户</el-button
        >
      </el-aside>
      <el-main>
        <div class="main-box">
          <el-collapse>
            <el-collapse-item
              :title="ci.name"
              v-for="(ci, ciIndex) in ciReportArr"
              :key="ci.ciId"
            >
              <el-row style="color: #888">
                <el-col
                  :span="12"
                  style="box-sizing: border-box; padding: 6px 10px"
                  v-for="(cidr, cidrIndex) in ci.cidrList"
                  :key="cidr.ciId"
                >
                  <span
                    style="
                      background-color: #ba634e;
                      color: #fff;
                      box-sizing: border-box;
                      padding: 1px 3px;
                      border-radius: 3px;
                      margin-right: 5px;
                    "
                    v-if="cidr.isError == 1"
                    >异</span
                  >

                  <span style="margin-right: 5px; vertical-align: top">{{
                    cidr.name
                  }}</span>

                  <el-input
                    style="width: 26%; margin-right: 2px"
                    size="small"
                    :placeholder="cidr.name"
                    v-if="cidr.type != 4"
                    v-model="ciReportArr[ciIndex].cidrList[cidrIndex].value"
                    @blur="cidrCheckByBlur(ciIndex, cidrIndex)"
                  />
                  <el-input
                    style="width: 80%"
                    type="textarea"
                    :rows="2"
                    :placeholder="cidr.name"
                    v-model="ciReportArr[ciIndex].cidrList[cidrIndex].value"
                    v-if="cidr.type == 4"
                  />

                  <span style="margin-right: 15px">{{ cidr.unit }}</span>

                  <span v-if="cidr.normalValueString"
                    >正常值范围: {{ cidr.normalValueString }}</span
                  >
                </el-col>
              </el-row>
              <el-button
                type="primary"
                style="margin-top: 8px"
                @click="updateCiDetailedReport(ciIndex)"
                v-if="orders.state==1"
                >{{ ci.ciName }}项保存</el-button
              >
            </el-collapse-item>
          </el-collapse>

          <el-card class="box-card" style="margin-top: 20px">
            <template #header>
              <div class="card-header">
                <span>总检结论</span>
                <el-button class="button" type="danger"
                  @click="updateOrdersState"
                  v-if="orders.state==1">体检套餐总检结果报告归档</el-button
                >
              </div>
            </template>
            <div>
              <el-table :data="overallResultArr" style="width: 100%">
                <el-table-column prop="code" label="编号" width="60" />
                <el-table-column
                  prop="title"
                  label="总检结论项标题"
                  width="180"
                />
                <el-table-column prop="content" label="总检结论项内容" />
                <el-table-column label="操作" width="120">
                  <template #default="scope">
                    <el-button
                      type="text"
                      size="small"
                      @click="toUpdateOverallResult(scope.row)"
                      v-if="orders.state==1"
                      >更新</el-button
                    >
                    <el-button
                      type="text"
                      size="small"
                      @click="removeOverallResult(scope.row)"
                      v-if="orders.state==1"
                      >删除</el-button
                    >
                  </template>
                </el-table-column>
              </el-table>

              <el-form
                ref="formRef"
                :model="overallResultForm"
                style="margin-top: 20px"
                label-width="120px"
                v-if="orders.state==1"
              >
                <el-form-item label="总检结论标题">
                  <el-input
                    v-model="overallResultForm.title"
                    placeholder="总检结论标题"
                  ></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input
                    v-model="overallResultForm.content"
                    :rows="2"
                    type="textarea"
                    placeholder="总检结论内容"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="addOverallResult"
                    >添加</el-button
                  >
                  <el-button type="warning" @click="resetOverallResult"
                    >清空</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </el-card>

          <!-- 总检结论更新用对话框 -->
          <el-dialog v-model="dialogVisible" title="总检结论项更新" width="60%">
            <span>
              <el-form :model="updateOverallResultForm" label-width="120px">
                <el-form-item label="总检结论标题">
                  <el-input v-model="updateOverallResultForm.title"></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input v-model="updateOverallResultForm.content" type="textarea" :rows="3"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateOverallResult">更新</el-button>
                  <el-button @click="dialogVisible = false">取消</el-button>
                </el-form-item>
              </el-form>
            </span>
          </el-dialog>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, reactive, onMounted,watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import { getSessionStorage } from '../common.js'; // 确保路径正确



const router = useRouter();
const route = useRoute();

// 响应式状态
const orderId = ref(route.query.orderId);
const doctor = ref(getSessionStorage("doctor") || '');
const orders = ref({});
const ciReportArr = ref([]);
const overallResultArr = ref([]);
const overallResultForm = reactive({
title: '',
content: '',
});
const updateOverallResultForm = reactive({
orId: '',
title: '',
content: '',
});
const dialogVisible = ref(false);

// 初始化
onMounted(async () => {
  console.log("初始化");
await getOrdersById();
await listCiReport();
await listOverallResultByOrderId();
});

// 获取体检预约信息
async function getOrdersById() {
try {
  orderId.value =2;
  const response = await axios.get("/api/orders/getorder?orderId="+orderId.value );
      orders.value = response.data.data;
      console.log("orders",orders.value);
} catch (error) {
  console.error('Error fetching orders by ID:', error);
}
}

// 获取体检报告检查项信息
async function listCiReport() {
try {
  const response = await axios.get("/api/cidetailedreport/getList?orderId=2");
  ciReportArr.value = response.data.data;
  console.log("ciReportArr",ciReportArr.value);
} catch (error) {
  console.error('Error listing CI report:', error);
}
}

// 获取总检结论项信息
async function listOverallResultByOrderId() {
try {
  const response = await axios.post("overallResult/listOverallResultByOrderId", { orderId: orderId.value });
  overallResultArr.value = response.data.map((item, index) => ({
    ...item,
    code: index + 1,
  }));
} catch (error) {
  console.error('Error listing overall results by order ID:', error);
}
}
// 查询体检用户
function toOrdersList() {
router.push({ name: 'OrdersList' });
}

// 检查项明细验证
function cidrCheckByBlur(ciIndex, cidrIndex) {
const cidr = ciReportArr.value[ciIndex].cidrList[cidrIndex];
if (cidr.type === 1) {
  if (cidr.value !== '' && (parseFloat(cidr.value) < cidr.minrange || parseFloat(cidr.value) > cidr.maxrange)) {
    cidr.isError = 1;
  } else {
    cidr.isError = 0;
  }
} else if (cidr.type === 2) {
  if (cidr.value !== '' && cidr.value !== cidr.normalValue) {
    cidr.isError = 1;
  } else {
    cidr.isError = 0;
  }
}
}

// 更新检查项明细报告
async function updateCiDetailedReport(ciIndex) {
const cidrArr = ciReportArr.value[ciIndex].cidrList;
for (const cidr of cidrArr) {
  if (!cidr.value) {
    alert(`${cidr.name} 不能为空！`);
    return;
  }
  if (cidr.type === 1 && isNaN(parseFloat(cidr.value))) {
    alert(`${cidr.name} 必须为数字！`);
    return;
  }
}

const arr = cidrArr.map(cidr => ({
  cidrId: cidr.cidrId,
  value: cidr.value,
  isError: cidr.isError,
}));

try {
  const response = await axios.post("ciDetailedReport/updateCiDetailedReport", arr);
  if (response.data > 0) {
    alert("保存成功！");
    listCiReport();
  } else {
    alert("保存失败！");
  }
} catch (error) {
  console.error('Error updating CI detailed report:', error);
}
}

// 添加总检结论
async function addOverallResult() {
if (!overallResultForm.title) {
  alert("总检结论项标题不能为空！");
  return;
}

overallResultForm.orderId = orderId.value;
try {
  const response = await axios.post("overallResult/saveOverallResult", overallResultForm);
  if (response.data > 0) {
    listOverallResultByOrderId();
  } else {
    alert("总检结论项添加失败！");
  }
} catch (error) {
  console.error('Error adding overall result:', error);
}
}

// 清空总检结论表单
function resetOverallResult() {
overallResultForm.title = '';
overallResultForm.content = '';
}

// 删除总检结论项
async function removeOverallResult(orId) {
if (!confirm("确认删除此数据吗？")) {
  return;
}

try {
  const response = await axios.post("overallResult/removeOverallResult", { orId });
  if (response.data > 0) {
    listOverallResultByOrderId();
  } else {
    alert("总检结论项删除失败！");
  }
} catch (error) {
  console.error('Error removing overall result:', error);
}
}

// 更新总检结论项
async function updateOverallResult() {
try {
  const response = await axios.post("overallResult/updateOverallResult", updateOverallResultForm);
  if (response.data > 0) {
    listOverallResultByOrderId();
    dialogVisible.value = false;
  } else {
    alert("总检结论项更新失败！");
  }
} catch (error) {
  console.error('Error updating overall result:', error);
}
}

// 归档体检套餐总检结果报告
async function updateOrdersState() {
if (!confirm("总检结论报告归档前，请务必确认是否所有检查项数据都正确？")) {
  return;
}

try {
  const response = await axios.post("orders/updateOrdersState", {
    orderId: orderId.value,
    state: 2,
  });
  if (response.data > 0) {
    router.push({ name: 'OrdersList' });
  } else {
    alert("总检结论报告归档失败！");
  }
} catch (error) {
  console.error('Error archiving overall result report:', error);
}
}

// 监听 orderId 的变化
watch(orderId, (newOrderId) => {
if (newOrderId) {
  getOrdersById();
  listCiReport();
  listOverallResultByOrderId();
}
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

//图标
@import url('https://unpkg.com/tailwindcss@^2.0/dist/tailwind.min.css');
</style>