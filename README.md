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
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
  "userId": "",
  "password": "",
  "realName": "",
  "sex": 0,
  "identityCard": "",
  "birthday": "",
  "userType": 0
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
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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


## 用户注册


**接口地址**:`/users/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "userId": "",
  "password": "",
  "realName": "",
  "sex": 0,
  "identityCard": "",
  "birthday": "",
  "userType": 0
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
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
|orderId||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
  "orderId": 0,
  "orderDate": "",
  "userId": "",
  "hpId": 0,
  "smId": 0,
  "state": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orders|Orders|body|true|Orders|Orders|
|&emsp;&emsp;orderId|||false|integer(int32)||
|&emsp;&emsp;orderDate|||false|string(date)||
|&emsp;&emsp;userId|||false|string||
|&emsp;&emsp;hpId|||false|integer(int32)||
|&emsp;&emsp;smId|||false|integer(int32)||
|&emsp;&emsp;state|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
|userId||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
|year||query|true|integer(int32)||
|month||query|true|integer(int32)||
|hpId||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
|userId||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|errorMsg||string||
|data||object||
|total||integer(int64)|integer(int64)|
|code||integer(int32)|integer(int32)|


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
