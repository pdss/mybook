// 点赞
$("div div div main section div a").click(function () {
    var id = this.id;
    var list = id.split("r");
    var index = list[1];
    var iocid = 'starOutline'+index;
    var spanid = 'comStar'+index;
    var name = $("#"+iocid).attr("name");
    var star = $("#"+spanid).text();
    if(name == 'star'){
        $("#"+iocid).attr("name","star-outline");
        var star1 = star*1-1;
        $("#"+spanid).text(star1);
        var url = "../sub";
        var data = {"id":index};
        $.post(url,data);
    }else{
        $("#"+iocid).attr("name","star");
        var star2 = star*1+1;
        $("#"+spanid).text(star2);
        var url = "../add";
        $.post(url,{"id":index});
    }
});
//评论
$("#pushButton").click(function () {
    var content = $("#id_comment").val();
    var id = $("#articleId").val();
    var url = "../pushComment"
    var data = {"aId":id,"content":content}
    $.post(url,data,function (result) {
        alert(result.statu);
    })
})