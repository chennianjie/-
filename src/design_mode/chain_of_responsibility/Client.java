package design_mode.chain_of_responsibility;



public class Client {

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        CharacterFilter characterFilter = new CharacterFilter();
        LoginFilter loginFilter = new LoginFilter();
        filterChain.addFilter(loginFilter);
        filterChain.addFilter(characterFilter);
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setRequestContext("chennianjie");
        httpRequest.setRequestParamMap("username", "chennianjie");
        httpRequest.setRequestParamMap("password", "123456");
        HttpResponse httpResponse = new HttpResponse();
        filterChain.doFilter(httpRequest, httpResponse);
        System.out.println(httpRequest.getRequestContext());
    }

}
