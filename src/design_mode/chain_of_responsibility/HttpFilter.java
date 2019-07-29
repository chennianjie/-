package design_mode.chain_of_responsibility;


/**
 * 过滤器顶级抽象接口类
 */
public abstract class HttpFilter {

    abstract void doFilter(HttpRequest request, HttpResponse response, FilterChain filterChain);
}
