package life.mashuai.communtiy.provider;


import com.alibaba.fastjson.JSON;
import life.mashuai.communtiy.dto.AccessTokenDto;
import life.mashuai.communtiy.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//省区new一个对象
public class GithubProvider {

    //主要接受授权登录的状态吗
    public  String getAccesoken(AccessTokenDto accessTokenDto){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client=new OkHttpClient();
        RequestBody body=RequestBody.create(mediaType,JSON.toJSONString(accessTokenDto));
        Request request=new  Request.Builder().url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            //拆分数据，拿到需要的access_token
            String accessToken=string.split("&")[0].split("=")[1];
            return accessToken;
            //返回拆分好的access_token的状态吗
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //授权成功之后传入·当前的access_token码，解析出当前用户的信息方法
    public GithubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new  Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        // https://api.github.com/user?access_token=960851d9358d41507cea87fe0dd25351d45afbfd
        //在浏览器输入https://api.github.com/user?access_token="+accessToken 可以看出access_token携带当前用户的所有信息
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            //请求的网站带出所有的数据转换为json格式放进对象
            GithubUser user= JSON.parseObject(string, GithubUser.class);
            //将得到的字符串转换为整个GithubUser类的对象
            System.out.println(user.toString());
            return user;//返回用户信息的对象
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

}


/*
这是acces_token携带的所有信息
{
        "login": "ma937958191",
        "id": 60689139,
        "node_id": "MDQ6VXNlcjYwNjg5MTM5",
        "avatar_url": "https://avatars3.githubusercontent.com/u/60689139?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/ma937958191",
        "html_url": "https://github.com/ma937958191",
        "followers_url": "https://api.github.com/users/ma937958191/followers",
        "following_url": "https://api.github.com/users/ma937958191/following{/other_user}",
        "gists_url": "https://api.github.com/users/ma937958191/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/ma937958191/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/ma937958191/subscriptions",
        "organizations_url": "https://api.github.com/users/ma937958191/orgs",
        "repos_url": "https://api.github.com/users/ma937958191/repos",
        "events_url": "https://api.github.com/users/ma937958191/events{/privacy}",
        "received_events_url": "https://api.github.com/users/ma937958191/received_events",
        "type": "User",
        "site_admin": false,
        "name": "mashuai",
        "company": null,
        "blog": "",
        "location": null,
        "email": null,
        "hireable": null,
        "bio": null,
        "public_repos": 1,
        "public_gists": 0,
        "followers": 0,
        "following": 0,
        "created_at": "2020-02-05T08:18:45Z",
        "updated_at": "2020-02-06T12:48:13Z",
        "private_gists": 0,
        "total_private_repos": 0,
        "owned_private_repos": 0,
        "disk_usage": 0,
        "collaborators": 0,
        "two_factor_authentication": false,
        "plan": {
        "name": "free",
        "space": 976562499,
        "collaborators": 0,
        "private_repos": 10000
        }
        }*/
