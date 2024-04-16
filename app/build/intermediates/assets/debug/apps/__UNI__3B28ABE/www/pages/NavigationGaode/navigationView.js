"use weex:vue";

if (typeof Promise !== 'undefined' && !Promise.prototype.finally) {
  Promise.prototype.finally = function(callback) {
    const promise = this.constructor
    return this.then(
      value => promise.resolve(callback()).then(() => value),
      reason => promise.resolve(callback()).then(() => {
        throw reason
      })
    )
  }
};

if (typeof uni !== 'undefined' && uni && uni.requireGlobal) {
  const global = uni.requireGlobal()
  ArrayBuffer = global.ArrayBuffer
  Int8Array = global.Int8Array
  Uint8Array = global.Uint8Array
  Uint8ClampedArray = global.Uint8ClampedArray
  Int16Array = global.Int16Array
  Uint16Array = global.Uint16Array
  Int32Array = global.Int32Array
  Uint32Array = global.Uint32Array
  Float32Array = global.Float32Array
  Float64Array = global.Float64Array
  BigInt64Array = global.BigInt64Array
  BigUint64Array = global.BigUint64Array
};


(()=>{var u=Object.create;var p=Object.defineProperty;var _=Object.getOwnPropertyDescriptor;var m=Object.getOwnPropertyNames;var g=Object.getPrototypeOf,v=Object.prototype.hasOwnProperty;var f=(t,e)=>()=>(e||t((e={exports:{}}).exports,e),e.exports);var w=(t,e,r,a)=>{if(e&&typeof e=="object"||typeof e=="function")for(let l of m(e))!v.call(t,l)&&l!==r&&p(t,l,{get:()=>e[l],enumerable:!(a=_(e,l))||a.enumerable});return t};var b=(t,e,r)=>(r=t!=null?u(g(t)):{},w(e||!t||!t.__esModule?p(r,"default",{value:t,enumerable:!0}):r,t));var i=f((y,c)=>{c.exports=Vue});var o=b(i()),n={__name:"navigationView",setup(t){return(0,o.ref)(123),(e,r)=>{let a=(0,o.resolveComponent)("t-chip");return(0,o.openBlock)(),(0,o.createElementBlock)("scroll-view",{scrollY:!0,showScrollbar:!0,enableBackToTop:!0,bubble:"true",style:{flexDirection:"column"}},[(0,o.createElementVNode)("div",null,[(0,o.createElementVNode)("u-text",null,"\u5BFC\u822A\u539F\u751F\u9875\u9762"),(0,o.createElementVNode)("div",{class:"flex-horizontal"},[(0,o.createVNode)(a)])])])}}};var s=plus.webview.currentWebview();if(s){let t=parseInt(s.id),e="pages/NavigationGaode/navigationView",r={};try{r=JSON.parse(s.__query__)}catch(l){}n.mpType="page";let a=Vue.createPageApp(n,{$store:getApp({allowDefault:!0}).$store,__pageId:t,__pagePath:e,__pageQuery:r});a.provide("__globalStyles",Vue.useCssStyles([...__uniConfig.styles,...n.styles||[]])),a.mount("#root")}})();
