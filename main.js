import App from './App'

// 引入全局uview-plus
import uviewPlus from './uni_modules/uview-plus/index.js'

// i18n部分的配置
// 引入语言包，注意路径
import zhHans from '@/common/locales/zh.js';
import en from '@/common/locales/en.js';
const messages = {
	en,
	'zh-Hans': zhHans,
}
let i18nConfig = {
  locale: uni.getLocale(),// 获取已设置的语言
  messages
}

// mark 自定义组件
  // tag small
import btnIcon from "./components/Com/btnIcon.vue";
import chip from "./components/Com/chip.vue";
import btn from "./components/Com/Button.vue";
  // tag mid
import tabber from "./components/Com/tabbar.vue";

// 引入uView对小程序分享的mixin封装
import mpShare from '@/uni_modules/uview-plus/libs/mixin/mpShare.js'
import mixin from './common/mixin'

// #ifdef VUE3
import { createSSRApp } from 'vue'
import { createI18n } from 'vue-i18n'
const i18n = createI18n(i18nConfig)
import { createPinia } from 'pinia'
import {initRequest} from './util/request/index'

export function createApp() {
const app = createSSRApp(App)
const pinia = createPinia()
  
  // 引入请求封装
  initRequest(app)

  app.use(i18n)
	.use(pinia)
	.use(uviewPlus)

  // #ifdef MP
  app.mixin(mpShare)
  app.mixin(mixin)
  // #endif

  // mark 自定义组件
  app.component("t-btn-icon", btnIcon);
  app.component("t-tabbar", tabber);
  app.component("t-chip", chip);
  app.component("t-btn", btn);

  return {
    app,
    pinia
  }
}
// #endif






