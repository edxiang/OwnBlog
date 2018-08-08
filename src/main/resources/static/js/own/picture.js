$().ready(function(){
    $("#uploadPicture").bind('click',function(){
        layer.open({
            type:1,
            title: 'upload picture',
            area:['420px','250px'],
            content:$("#uploadDiv"),
            cancel:layer.closeAll()
        });
    });

    $("#btnUpload").bind('click',function(){
        var fd = new FormData();
        var albumId = $("#hiddenAlbumId").val();

        fd.append("albumId", albumId);
        var obj = document.getElementById("file");
        var length = obj.files.length;
        for(var i=0;i<length;i++){
            var temp = obj.files[i].name;
            console.log(temp);
            fd.append("fileArray",obj.files[i]);
        }

        $.ajax({
            url: '../albums/uploadFiles',
            type:'post',
            data: fd,
            processData: false,
            contentType: false,
            success:function(data){
                alert(data);
                window.location.reload();
            },
            complete:layer.closeAll()
        });
    });
});