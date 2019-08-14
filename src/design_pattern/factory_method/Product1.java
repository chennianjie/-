package design_pattern.factory_method;


public class Product1 extends Product {

    private String name;
    private String createdTime;

    public Product1(String name, String createdTime) {
        this.name = name;
        this.createdTime = createdTime;
    }

    @Override
    public Product createInstance() {
        return new Product1("chocolate","2019-7-15");
    }
}
