// 判断组件的亲子关系
export function isDescendant(parent, child) {
  let node = child;
  while (node) {
    if (node == parent) return true;
    node = node.parentNode;
    // console.info(node);
  }
  return false;
};