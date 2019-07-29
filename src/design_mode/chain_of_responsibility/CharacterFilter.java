package design_mode.chain_of_responsibility;

/**
 * 字符过滤器
 */
public class CharacterFilter extends HttpFilter{

    @Override
    void doFilter(HttpRequest request, HttpResponse response, FilterChain filterChain) {
        String requestContext = request.getRequestContext();
        if (requestContext.contains("chen")) {
            requestContext = requestContext.replace("chen","?");
        }
        request.setRequestContext(requestContext);
        filterChain.doFilter(request, response);
        System.out.println("字符校验已通过。");
    }
}
