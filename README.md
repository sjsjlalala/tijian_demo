# OpenAPI definition


**简介**:OpenAPI definition


**HOST**:http://localhost:8080


**联系人**:


**Version**:v0


**接口路径**:/v3/api-docs/default


[TOC]






# 体检套餐管理接口


## 根据用户性别获取套餐信息


**接口地址**:`/setmeal/getSetmealBySex`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sex||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
  "success": true,
  "errorMsg": null,
  "data": [
    {
      "smId": 4,
      "name": "女士-基础套餐",
      "type": 0,
      "price": 400,
      "checkitem": [
        {
          "ciId": 1,
          "ciName": "一般检查",
          "ciContent": "血压、身高、体重、腰围、臀围。",
          "meaning": "通过仪器测定人体基本健康指标。",
          "remarks": null
        },
        {
          "ciId": 2,
          "ciName": "血常规",
          "ciContent": "血常规24项:\r\n                中性粒细胞计数绝对值、红细胞压值、血小板比容、单核细胞计数百分比、平均血小板体积、\r\n                大血小板比例、嗜碱性粒细胞计数百分比、平均血红蛋白含量等。",
          "meaning": "临床三大常规检测之一，是最基本的血液检验。通过观察血细胞的数量变化及形态分布，从而判断血液状况及相关疾病。",
          "remarks": null
        },
        {
          "ciId": 3,
          "ciName": "尿常规",
          "ciContent": "尿维生素C测定、尿比重测定、尿液酮体测定、尿胆红素测定、尿亚硝酸盐测定、尿液颜色、尿白细胞计数、尿蛋白定性、\r\n                尿液镜检、尿葡萄糖测定、尿液隐血、尿液酸碱度、尿液清晰度、尿胆原。",
          "meaning": "临床三大常规检测之一，可反映机体的代谢状况，是很多疾病诊断的重要指标。",
          "remarks": null
        },
        {
          "ciId": 6,
          "ciName": "妇科检查",
          "ciContent": "全身检查、腹部检查和盆腔检查。检查外阴、阴道、子宫颈和子宫、输卵管、卵巢及宫旁组织和骨盆腔内壁。",
          "meaning": "主要作用是对一些妇科疾病作出早期诊断、预防以及早期治疗。",
          "remarks": null
        }
      ]
    },
    {
      "smId": 5,
      "name": "女士-肾病检查",
      "type": 0,
      "price": 650,
      "checkitem": [
        {
          "ciId": 1,
          "ciName": "一般检查",
          "ciContent": "血压、身高、体重、腰围、臀围。",
          "meaning": "通过仪器测定人体基本健康指标。",
          "remarks": null
        },
        {
          "ciId": 2,
          "ciName": "血常规",
          "ciContent": "血常规24项:\r\n                中性粒细胞计数绝对值、红细胞压值、血小板比容、单核细胞计数百分比、平均血小板体积、\r\n                大血小板比例、嗜碱性粒细胞计数百分比、平均血红蛋白含量等。",
          "meaning": "临床三大常规检测之一，是最基本的血液检验。通过观察血细胞的数量变化及形态分布，从而判断血液状况及相关疾病。",
          "remarks": null
        },
        {
          "ciId": 3,
          "ciName": "尿常规",
          "ciContent": "尿维生素C测定、尿比重测定、尿液酮体测定、尿胆红素测定、尿亚硝酸盐测定、尿液颜色、尿白细胞计数、尿蛋白定性、\r\n                尿液镜检、尿葡萄糖测定、尿液隐血、尿液酸碱度、尿液清晰度、尿胆原。",
          "meaning": "临床三大常规检测之一，可反映机体的代谢状况，是很多疾病诊断的重要指标。",
          "remarks": null
        },
        {
          "ciId": 4,
          "ciName": "肾功能",
          "ciContent": "肾功能（renal function）是指肾脏排泄体内代谢废物，维持机体钠、钾、钙等电解质的稳定及酸碱平衡的功能，肾功能检查包括血肌酐、血尿素氮、血及尿β2—微球蛋白、尿白蛋白、尿免疫球蛋白G、尿分泌型免疫球蛋白A等。",
          "meaning": "肾功能检查的临床意义用于急慢性肾炎、肾病、尿毒症、肾衰竭等疾病的检查。",
          "remarks": null
        },
        {
          "ciId": 6,
          "ciName": "妇科检查",
          "ciContent": "全身检查、腹部检查和盆腔检查。检查外阴、阴道、子宫颈和子宫、输卵管、卵巢及宫旁组织和骨盆腔内壁。",
          "meaning": "主要作用是对一些妇科疾病作出早期诊断、预防以及早期治疗。",
          "remarks": null
        }
      ]
    },
    {
      "smId": 6,
      "name": "女士-肝病检查",
      "type": 0,
      "price": 650,
      "checkitem": [
        {
          "ciId": 1,
          "ciName": "一般检查",
          "ciContent": "血压、身高、体重、腰围、臀围。",
          "meaning": "通过仪器测定人体基本健康指标。",
          "remarks": null
        },
        {
          "ciId": 2,
          "ciName": "血常规",
          "ciContent": "血常规24项:\r\n                中性粒细胞计数绝对值、红细胞压值、血小板比容、单核细胞计数百分比、平均血小板体积、\r\n                大血小板比例、嗜碱性粒细胞计数百分比、平均血红蛋白含量等。",
          "meaning": "临床三大常规检测之一，是最基本的血液检验。通过观察血细胞的数量变化及形态分布，从而判断血液状况及相关疾病。",
          "remarks": null
        },
        {
          "ciId": 3,
          "ciName": "尿常规",
          "ciContent": "尿维生素C测定、尿比重测定、尿液酮体测定、尿胆红素测定、尿亚硝酸盐测定、尿液颜色、尿白细胞计数、尿蛋白定性、\r\n                尿液镜检、尿葡萄糖测定、尿液隐血、尿液酸碱度、尿液清晰度、尿胆原。",
          "meaning": "临床三大常规检测之一，可反映机体的代谢状况，是很多疾病诊断的重要指标。",
          "remarks": null
        },
        {
          "ciId": 5,
          "ciName": "肝功能",
          "ciContent": "丙氨酸氨基转移酶（又称谷丙转氨酶，ALT）、门冬氨酸氨基转移酶（又称谷草转氨酶，AST）、碱性磷酸酶（ALP）、γ－谷氨酰转肽酶（γ－GT或GGT）。",
          "meaning": "肝功能检查的目的在于探测肝脏有无疾病、肝脏损害程度以及查明肝病原因、判断预后和鉴别发生黄疸的病因等。",
          "remarks": null
        },
        {
          "ciId": 6,
          "ciName": "妇科检查",
          "ciContent": "全身检查、腹部检查和盆腔检查。检查外阴、阴道、子宫颈和子宫、输卵管、卵巢及宫旁组织和骨盆腔内壁。",
          "meaning": "主要作用是对一些妇科疾病作出早期诊断、预防以及早期治疗。",
          "remarks": null
        }
      ]
    }
  ],
  "total": 3,
  "code": 200
}
```


# 医院信息管理接口


## 获取医院信息


**接口地址**:`/hospital/getHospital`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": {},
	"total": 0,
	"code": 0
}
```


# 用户管理接口


## 用户登录


**接口地址**:`/users/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "userId": "1",
  "password": "1"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|users|Users|body|true|Users|Users|
|&emsp;&emsp;userId|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;realName|||false|string||
|&emsp;&emsp;sex|||false|integer(int32)||
|&emsp;&emsp;identityCard|||false|string||
|&emsp;&emsp;birthday|||false|string(date)||
|&emsp;&emsp;userType|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
  "success": true,
  "errorMsg": null,
  "data": {
    "userId": "1",
    "realName": "1",
    "sex": 1,
    "identityCard": "1",
    "birthday": "2024-06-13",
    "userType": 2
  },
  "total": null,
  "code": 200
}
```


## 用户退出


**接口地址**:`/users/logout`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
    "success": true,
    "errorMsg": null,
    "data": "退出成功",
    "total": null,
    "code": 200
}
```


## 用户注册


**接口地址**:`/users/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "success": true,
  "errorMsg": null,
  "data": {
    "userId": "1",
    "realName": "1",
    "sex": 1,
    "identityCard": "1",
    "birthday": "2024-06-13",
    "userType": 2
  },
  "total": null,
  "code": 200
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|users|Users|body|true|Users|Users|
|&emsp;&emsp;userId|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;realName|||false|string||
|&emsp;&emsp;sex|||false|integer(int32)||
|&emsp;&emsp;identityCard|||false|string||
|&emsp;&emsp;birthday|||false|string(date)||
|&emsp;&emsp;userType|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": "注册成功",
	"total": 0,
	"code": 200
}
```


# 预约管理接口


## 取消预约


**接口地址**:`/orders/cancelOrder`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderId|订单id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|取消预约成功|Result|
|400|取消预约失败|Result|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
    "success": true,
    "errorMsg": null,
    "data": "取消成功",
    "total": null,
    "code": 200
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": {},
	"total": 0,
	"code": 0
}
```


## 创建预约订单


**接口地址**:`/orders/createOrder`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
    "hpId": "1",
    "orderDate": "2024-06-29",
    "userId": "3",
    "smId": "4"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orders|预约信息|body|true|Orders|Orders|
|&emsp;&emsp;orderId|||false|integer(int32)||
|&emsp;&emsp;orderDate|||false|string(date)||
|&emsp;&emsp;userId|||false|string||
|&emsp;&emsp;hpId|||false|integer(int32)||
|&emsp;&emsp;smId|||false|integer(int32)||
|&emsp;&emsp;state|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|创建预约订单成功|Result|
|400|创建预约订单失败|Result|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
    "success": true,
    "errorMsg": null,
    "data": "下单成功",
    "total": null,
    "code": 200
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": {},
	"total": 0,
	"code": 0
}
```


## 医生根据用户id获取预约信息


**接口地址**:`/orders/getAllOrdersForDoctor`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|用户id|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|医生根据用户id获取预约信息成功|Result|
|400|医生根据用户id获取预约信息失败|Result|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
    "success": true,
    "errorMsg": null,
    "data": [
        {
            "orderId": "2",
            "orderDate": "2024-06-19",
            "map": [
                {
                    "cddrs": [
                        {
                            "cidrId": 394,
                            "name": "收缩压",
                            "unit": null,
                            "minrange": 0.0,
                            "maxrange": 140.0,
                            "normalValue": null,
                            "normalValueString": "<140",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 1,
                            "orderId": 2
                        },
                        {
                            "cidrId": 395,
                            "name": "舒张压",
                            "unit": null,
                            "minrange": 0.0,
                            "maxrange": 90.0,
                            "normalValue": null,
                            "normalValueString": "<90",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 1,
                            "orderId": 2
                        },
                        {
                            "cidrId": 396,
                            "name": "身高",
                            "unit": "cm",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": null,
                            "normalValueString": null,
                            "type": 3,
                            "value": null,
                            "isError": 0,
                            "ciId": 1,
                            "orderId": 2
                        },
                        {
                            "cidrId": 397,
                            "name": "体重",
                            "unit": "kg",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": null,
                            "normalValueString": null,
                            "type": 3,
                            "value": null,
                            "isError": 0,
                            "ciId": 1,
                            "orderId": 2
                        },
                        {
                            "cidrId": 398,
                            "name": "腰围",
                            "unit": "cm",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": null,
                            "normalValueString": null,
                            "type": 3,
                            "value": null,
                            "isError": 0,
                            "ciId": 1,
                            "orderId": 2
                        },
                        {
                            "cidrId": 399,
                            "name": "臀围",
                            "unit": "cm",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": null,
                            "normalValueString": null,
                            "type": 3,
                            "value": null,
                            "isError": 0,
                            "ciId": 1,
                            "orderId": 2
                        }
                    ],
                    "name": [
                        "一般检查"
                    ]
                },
                {
                    "cddrs": [
                        {
                            "cidrId": 400,
                            "name": "白细胞计数",
                            "unit": "10^9/L",
                            "minrange": 4.0,
                            "maxrange": 10.0,
                            "normalValue": null,
                            "normalValueString": "4-10",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 401,
                            "name": "红细胞压值",
                            "unit": "%",
                            "minrange": 36.0,
                            "maxrange": 50.0,
                            "normalValue": null,
                            "normalValueString": "36-50",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 402,
                            "name": "淋巴细胞计数百分比",
                            "unit": "%",
                            "minrange": 18.3,
                            "maxrange": 47.9,
                            "normalValue": null,
                            "normalValueString": "18.3-47.9",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 403,
                            "name": "单核细胞计数百分比",
                            "unit": "%",
                            "minrange": 4.2,
                            "maxrange": 15.2,
                            "normalValue": null,
                            "normalValueString": "4.2-15.2",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 404,
                            "name": "嗜酸性粒细胞计数百分比",
                            "unit": "%",
                            "minrange": 0.2,
                            "maxrange": 7.6,
                            "normalValue": null,
                            "normalValueString": "0.2-7.6",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 405,
                            "name": "中性粒细胞计数绝对值",
                            "unit": "10^9/L",
                            "minrange": 1.8,
                            "maxrange": 8.89,
                            "normalValue": null,
                            "normalValueString": "1.8-8.89",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 406,
                            "name": "血小板计数",
                            "unit": "10^9/L",
                            "minrange": 100.0,
                            "maxrange": 300.0,
                            "normalValue": null,
                            "normalValueString": "100-300",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        },
                        {
                            "cidrId": 407,
                            "name": "血红细胞计数",
                            "unit": "10^12/L",
                            "minrange": 3.5,
                            "maxrange": 5.5,
                            "normalValue": null,
                            "normalValueString": "3.5-5.5",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 2,
                            "orderId": 2
                        }
                    ],
                    "name": [
                        "血常规"
                    ]
                },
                {
                    "cddrs": [
                        {
                            "cidrId": 408,
                            "name": "尿白细胞",
                            "unit": "/ul",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": "-",
                            "normalValueString": "-",
                            "type": 2,
                            "value": null,
                            "isError": 0,
                            "ciId": 3,
                            "orderId": 2
                        },
                        {
                            "cidrId": 409,
                            "name": "尿亚硝酸盐",
                            "unit": null,
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": "-",
                            "normalValueString": "-（阴性）",
                            "type": 2,
                            "value": null,
                            "isError": 0,
                            "ciId": 3,
                            "orderId": 2
                        },
                        {
                            "cidrId": 410,
                            "name": "尿液酸碱度",
                            "unit": null,
                            "minrange": 5.5,
                            "maxrange": 6.5,
                            "normalValue": null,
                            "normalValueString": "5.5-6.5",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 3,
                            "orderId": 2
                        },
                        {
                            "cidrId": 411,
                            "name": "尿蛋白定性",
                            "unit": "g/L",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": "-",
                            "normalValueString": "-",
                            "type": 2,
                            "value": null,
                            "isError": 0,
                            "ciId": 3,
                            "orderId": 2
                        },
                        {
                            "cidrId": 412,
                            "name": "尿比重",
                            "unit": null,
                            "minrange": 1.015,
                            "maxrange": 1.025,
                            "normalValue": null,
                            "normalValueString": "1.015-1.025",
                            "type": 1,
                            "value": null,
                            "isError": 0,
                            "ciId": 3,
                            "orderId": 2
                        },
                        {
                            "cidrId": 413,
                            "name": "尿维生素",
                            "unit": "mmol/L",
                            "minrange": null,
                            "maxrange": null,
                            "normalValue": "0",
                            "normalValueString": "0",
                            "type": 2,
                            "value": null,
                            "isError": 0,
                            "ciId": 3,
                            "orderId": 2
                        }
                    ],
                    "name": [
                        "尿常规"
                    ]
                }
            ],
            "overallresults": [
                {
                    "orId": 4,
                    "title": "超重",
                    "content": "您的体重指标属超重。体内脂肪过度增加，使体重超过正常范围，可引起高血压、高血脂、糖尿病、冠心病以及免疫功能降低等并发症。\n                            建议: 合理膳食，以低盐、低脂、高纤维为原则；三餐定时，不吃零食；根据自身情况适量运动，以消耗体内脂肪，维持正常体重。\n                ",
                    "orderId": 2
                },
                {
                    "orId": 5,
                    "title": "血压增高",
                    "content": "此次检测血压增高，已经达到高血压的诊断标准，建议就诊心血管内科进一步明确高血压诊断，积极控制好血压。低盐、低脂、低胆固醇饮食。\n                            戒烟酒，避免情绪激动，长期过度紧张工作或劳累，保存心境平和，保证充足睡眠。定期到医院系统复查，预防并发症。\n                   ",
                    "orderId": 2
                },
                {
                    "orId": 6,
                    "title": "血常规异常",
                    "content": " 此次检测白细胞减少，将会导致免疫功能的下降。建议就诊做进一步诊断。\n                            同时注意：胆固醇高不要吃油的，甜的，多吃水果和蔬菜，多吃富含蛋白质的食物，如牛奶和豆奶。注意锻炼，坚持每天三次，超过10分钟，中等强度，每周锻炼不少于5次。\n                   ",
                    "orderId": 2
                }
            ]
        }
    ],
    "total": 1,
    "code": 200
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": {},
	"total": 0,
	"code": 0
}
```


## 获取预约日历


**接口地址**:`/orders/getCalender`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|year|年|query|true|integer(int32)||
|month|月|query|true|integer(int32)||
|hpId|医院id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取预约日历成功|Result|
|400|获取预约日历失败|Result|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
    "success": true,
    "errorMsg": null,
    "data": [
        {
            "date": null,
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-01",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-02",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-03",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-04",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-05",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-06",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-07",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-08",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-09",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-10",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-11",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-12",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-13",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-14",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-15",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-16",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-17",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-18",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-19",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-20",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-21",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-22",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-23",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-24",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-25",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-26",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-27",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-28",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-29",
            "total": null,
            "remainder": null,
            "used": null
        },
        {
            "date": "2024-04-30",
            "total": null,
            "remainder": null,
            "used": null
        }
    ],
    "total": null,
    "code": 200
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": {},
	"total": 0,
	"code": 0
}
```


## 根据用户id获取预约信息


**接口地址**:`/orders/getOrdersByUserId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|用户id|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|根据用户id获取预约信息成功|Result|
|400|根据用户id获取预约信息失败|Result|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
    "success": true,
    "errorMsg": null,
    "data": [
        {
            "smId": 1,
            "hpId": 1,
            "orderDate": "2024-06-19",
            "orderId": 2
        }
    ],
    "total": 1,
    "code": 200
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success|是否成功|boolean||
|errorMsg|错误信息|string||
|data|返回数据|object||
|total|总记录数|integer(int64)|integer(int64)|
|code|状态码|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"success": true,
	"errorMsg": "",
	"data": {},
	"total": 0,
	"code": 0
}
```
