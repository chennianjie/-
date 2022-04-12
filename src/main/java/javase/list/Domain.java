package javase.list;

public class Domain {

    private String name;

    private Integer age;

    public Domain(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode(){
        return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
