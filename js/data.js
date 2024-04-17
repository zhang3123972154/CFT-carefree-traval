//  用来处理数据的函数

// 可以处理任意数量的zip压缩
export function zip(...arrays) {
  const length = Math.min(...arrays.map((arr) => arr.length));
  return Array.from({ length }, (_, i) => arrays.map((arr) => arr[i]));
}

export function swap(array, index1, index2) {
  if (index1 < array.length && index2 < array.length) {
    let temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
}
