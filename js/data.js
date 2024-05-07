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

// Info 统计字符类型
export function countCharacters(text) {
  let chineseCount = 0;
  let englishCount = 0;
  let digitCount = 0;
  let symbolCount = 0;
  for (let i = 0; i < text.length; i++) {
      const char = text.charAt(i);
      if (char.match(/[\u4e00-\u9fa5]/)) {
          chineseCount++;
      } else if (char.match(/[a-zA-Z]/)) {
          englishCount++;
      } else if (char.match(/[0-9]/)) {
          digitCount++;
      } else {
          symbolCount++;
      }
  }
  return { chineseCount, englishCount, digitCount, symbolCount };
}