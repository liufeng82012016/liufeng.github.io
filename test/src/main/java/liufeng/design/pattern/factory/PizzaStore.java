package liufeng.design.pattern.factory;

/**
 * @author liufeng
 * 工厂模式
 * 定义了一个创建对象的接口，由子类决定要实例话的类是哪一个
 *
 * 抽象工厂
 * 定义一个负责创建一组产品的接口，这个接口每个方法都负责创建一个具体产品
 *
 */
public abstract class PizzaStore {
    /**
     * @return pizza对象
     */
    protected abstract Pizza createPizza();

    public void orderPizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}

class NyPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza() {
        RawMaterialFactory rawMaterialFactory = new NyRawMaterialFactory();
        return new NyPizza(rawMaterialFactory);
    }
}

abstract class Pizza {
    /**
     * 名称
     */
    String name;
    /**
     * 原材料
     */
    protected RawMaterial rawMaterial;

    abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaSore box");
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 自定义Pizza实现类1
 */
class NyPizza extends Pizza {
    public NyPizza(RawMaterialFactory rawMaterialFactory) {
        this.rawMaterial = rawMaterialFactory.getRawMaterial();
    }

    @Override
    void prepare() {
        if (this.rawMaterial == null) {
            throw new RuntimeException("raw material is null");
        }
        System.out.println(this.getClass().getSimpleName() + " prepared");
    }
}


/**
 * 制作Pizza的原材料
 */
class RawMaterial {

    // 略
}

/**
 * 原料工厂，创建Pizza原材料
 */
abstract class RawMaterialFactory {
    protected abstract RawMaterial getRawMaterial();
}

class NyRawMaterialFactory extends RawMaterialFactory {
    @Override
    protected RawMaterial getRawMaterial() {
        //
        return null;
    }
}
