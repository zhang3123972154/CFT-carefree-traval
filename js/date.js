
export function getCurrentDateFormatted() {
    const date = new Date(); // 获取当前日期和时间
  
    const year = date.getFullYear(); // 获取年份
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 获取月份，月份从0开始，所以加1
    const day = date.getDate().toString().padStart(2, '0'); // 获取日期
  
    return `${year}-${month}-${day}`; // 使用模板字符串返回格式化的日期
}