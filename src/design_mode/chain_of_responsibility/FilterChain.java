package design_mode.chain_of_responsibility;


import java.util.ArrayList;

/**
 * 责任链设计模式
 * 模仿servlet过滤器
 * 装载Filter的容器，控制Filter的执行流程及顺序
 */
public class FilterChain {

    private ArrayList<HttpFilter> filters = new ArrayList<>();

    private Integer currentIndex = 0;

    public ArrayList<HttpFilter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<HttpFilter> filters) {
        this.filters = filters;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void addFilter(HttpFilter httpFilter) {
        filters.add(httpFilter);
    }

    public void removeFilter(HttpFilter httpFilter) {
        filters.remove(httpFilter);
    }

    public void doFilter(HttpRequest request, HttpResponse response) {
        if (currentIndex == filters.size()) {
            return;
        }
        filters.get(currentIndex++).doFilter(request, response, this);
    }
}
