package design_pattern.prototype;

/**
 * 原型设计模式 使用原型实例指定要创建对象的类型，通过复制这个原型来创建新对象
 *
 * 使用场景：
 * 创建对象成本比较大，比如初始化要很长时间的，占用太多CPU的，新对象可以通过复制已有的对象获得的，如果是相似的对象，则可以对其成员变量稍作修改
 */
public abstract class Prototype {

     abstract Prototype myClone();
}
