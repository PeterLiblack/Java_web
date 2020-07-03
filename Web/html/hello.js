/*
var s = "你好世界";
console.log(s);
alert(s);
*/


function addItem() {
  console.log("点击就送");
  // 计划往 <ol> 增加的项的 html 形式
  var listItemText = "<li>Hello</li>";
  // 使用 jQuery 的方法进行增加
  // jQuery 中，把 $ 作用 jQuery 的命名空间
  
  // 1. 我先找到要添加的 ol
  // 使用选择器
  var list = $("ol#list");
  // 2. 通过 jQuery，把我们要添加的 item 包装下
  var item = $(listItemText);
  // 3. 添加
  item.appendTo(list);
}
