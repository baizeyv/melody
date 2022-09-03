import axios from 'axios'

// 创建一个单例
const instance = axios.create({
    // baseURL: 'http://localhost:7373', // 所有请求的公共地址部分
    timeout: 3000 // 请求超时时间，当前为请求时间超过5s还未获得结果时，提示用户超时
})

// 请求拦截，在请求拦截中可以补充请求相关的配置
instance.interceptors.request.use(config => {
    console.log(config)
    return config;
}, error => {
    // 请求发生错误时候的相关处理 抛出异常
    Promise.reject(error)
})

// 响应拦截
instance.interceptors.response.use(res => {
    // 一般在这里处理，请求失败后的错误状态码
    // res 是所有相应的信息
    console.log(res);
    return Promise.resolve(res)
}, error => {
    Promise.reject(error)
})

export default instance