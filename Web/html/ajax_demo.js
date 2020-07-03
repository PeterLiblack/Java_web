function ajaxResponse(data) {
  console.log("已经获取响应");
  console.log(data);
  
  var tbody = $("tbody#content");
  
  for (var i in data) {
    var course = data[i];
    
    var text = "<tr><td>" + course["课程"] + "</td><td>" + course["姓名"] + "</td></tr>";
    console.log(text);
    $(text).appendTo(tbody);
  }
}

function ajaxRequest() {
  console.log("已经点击了");
  $.ajax({
    "url": "http://127.0.0.1:8080/peixinchen/data.json",
    "type": "get",
    "dataType": "json",
    "success": ajaxResponse
  });
}