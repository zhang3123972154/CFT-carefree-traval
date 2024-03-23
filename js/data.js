//  用来处理数据的函数

// 可以处理任意数量的zip压缩
export function zip(...arrays) {
  const length = Math.min(...arrays.map((arr) => arr.length));
  return Array.from({ length }, (_, i) => arrays.map((arr) => arr[i]));
}