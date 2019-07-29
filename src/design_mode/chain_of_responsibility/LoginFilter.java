package design_mode.chain_of_responsibility;


import java.util.HashMap;

/**
 * 登录账户验证过滤器
 */
public class LoginFilter extends HttpFilter{


    @Override
    void doFilter(HttpRequest request, HttpResponse response, FilterChain filterChain) {
        HashMap<String, String> requestParam = request.getRequestParam();
        String username = requestParam.get("username");
        String password = requestParam.get("password");
        if (username != null && password != null) {
            if ("chennianjie".equals(username) && "123456".equals(password)) {
                System.out.println("登录过滤器已验证通过");
                filterChain.doFilter(request, response);
            }else {
                System.out.println("登录过滤器已拒绝");
            }
        }
    }
}
