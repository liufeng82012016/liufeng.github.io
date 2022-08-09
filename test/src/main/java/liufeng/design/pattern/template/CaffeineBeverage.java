package liufeng.design.pattern.template;

/**
 * 模版方法模式
 * <p>
 * 泡咖啡：
 * 把水煮沸
 * 用沸水冲泡咖啡
 * 把coffee倒进杯子
 * 加糖和牛奶
 * <p>
 * 泡茶：
 * 把水煮沸
 * 用沸水浸泡茶叶
 * 将茶倒进杯子
 * 加柠檬
 *
 * @author liufeng
 */
public abstract class CaffeineBeverage {
    /**
     * 制作食谱
     */
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            // 改进，添加钩子
            addCondiments();
        }
    }

    /**
     * 酿造
     */
    abstract void brew();

    /**
     * 添加调味品
     */
    abstract void addCondiments();

    /**
     * 煮沸水
     */
    void boilWater() {
        System.out.println("boiling water");
    }

    /**
     * 倒进杯子
     */
    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    /**
     * 钩子
     * 子类可选择重写
     *
     * @return 是否需要添加调味品
     */
    boolean customerWantsCondiments() {
        return true;
    }
}

class Tea extends CaffeineBeverage {

    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}

class Coffee extends CaffeineBeverage {

    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
