/***
 *  系统角色 v 1.0
 *
 * @type {null}
 */


layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {

    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , form = layui.form //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element; //元素操作


    // 项目路径
    var serverPath = $('#path').val();

    var isSendFlag = false;

    //演示动画
    $('#send').on('click', function () {
        var othis = $(this), anim = othis.data('anim');
        //停止循环
        if (othis.hasClass('layui-anim-loop')) {
            return othis.removeClass(anim);
        }
        othis.removeClass(anim);
        setTimeout(function () {
            othis.addClass(anim);
        });
        //恢复渐隐
        if (anim === 'layui-anim-fadeout') {
            setTimeout(function () {
                othis.removeClass(anim);
            }, 1300);
        }
        sendEmail();
    });


    /*****************************************  END   【  以上是lay组件初始化   】 *****************************************/

    /*****************************************  START 【 以下是获取数据 操作数据 】 *****************************************/

    /**
     *  发送邮件 --->
     */
    var sendEmail =function () {
       var addr =  $('#emailAddr')[0].value;
       if(addr!=''){
           if(!isSendFlag){
               layer.msg('邮件正在发送中 。。。。');
               isSendFlag = true;
               $.post(serverPath + "/sys/email/sendEmailToOne.do", {
                   emailAddr: $('#emailAddr')[0].value
               }, function (data) {
                   if (data.code == '0') {
                       layer.msg('发送成功 ! ');
                   } else {
                       layer.msg('查询错误 : ' + data.msg);
                   }
                   isSendFlag = false;
               });
           }else{
               layer.msg('邮件已经在发送中。。。。。。');
           }
       }else{
             layer.msg('输入的邮件地址不能为空!');
       }
    }




























});