//고객 ID로 찾기
$(document).ready(function(){
    // $( "#button" ).click(function(e) {
    //     e.preventDefault();
    //
    //     let data = { id: $('#id').val()};
    //
    //     if($("#id").val()=='') {
    //         alert('ID를 입력해주세요'); $('#id').focus();
    //         return;
    //     }

        $.ajax({
            type: "post",
            url: "/board",
            dataType: 'json',
            success: function(data) {

                html="";

                for(i in data){
                    html += '<TR>';
                    html += '<TD>'+data[i].article_no+'</TD>';
                    html += '<TD>'+data[i].title+'</TD>';
                    html += '<TD>'+data[i].writer_name+'</TD>';
                    html += '<TD>'+data[i].read_cnt+'</TD>';
                }
                $("#tBody").empty();
                $("#tBody").append(html);
            },
            error: function(err) {
                console.log("error!");
            }
        });
    // });
});

//고객 삭제
$(document).on("click","#delete",function(){

    let data = { delete : $("#tBody").children().children().eq(0).text()};

    $.ajax({
        type: "post",
        url: "/user",
        data: data,
        dataType: 'json',
        success: function(data) {
            $(this).parent().parent().remove();
            alert("삭제 완료");
        },
        error: function(err) {
            console.log("error!");
        }
    });
});

//주문 삭제
