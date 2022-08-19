// 这里放置一些检测登录状态的公共代码：

//加上一个逻辑，通过GET /login 这个接口来获取当前的登陆状态：
function getUserInfo(pageName){
    $.ajax({
        type: 'get',
        url: 'login',
        success: function(body){
            // 判定此处的body是不是一个有效的user对象（userId 是否为非0）
            if(body.userId && body.userId > 0){
                // 此时登陆成功，不做处理
                console.log('当前登陆成功！用户名：'+body.username);

                // 根据当前用户登录的情况，把用户名设置到html页面上：
                if(pageName == 'blog_list.html'){
                   changeUserName(body.username);
                }
            }else{
                // 登录失败：
                // 让前端页面跳转到login.html
                alert('当前尚未登录，请登录后再访问！')
                location.assign('blog_login.html');
            }
        },
        error: function(){
            alert('当前尚未登录，请登录后再访问！')
            location.assign('blog_login.html');
        }
    });
}

function changeUserName(username){
    let h3 = document.querySelector('.card>h3');
    h3.innerHTML = username;
}