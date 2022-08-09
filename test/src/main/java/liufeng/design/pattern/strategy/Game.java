package liufeng.design.pattern.strategy;

/**
 * 游戏角色设计
 * 1. 类
 * 代表游戏角色
 * 代表武器行为
 * <p>
 * 限定：每个角色只能使用一种武器，但是游戏过程可以切换武器
 */
public class Game {
}

abstract class Character {
    protected WeaponBehavior weapon;

    public void fight() {
        weapon.userWeapon();
    }

    public WeaponBehavior getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponBehavior weapon) {
        this.weapon = weapon;
    }
}

class Queen extends Character {

}

class King extends Character {

}

interface WeaponBehavior {
    void userWeapon();
}

class KnifeBehavior implements WeaponBehavior {

    @Override
    public void userWeapon() {
        System.out.println("使用匕首战斗");
    }
}
class AxeBehavior implements WeaponBehavior {

    @Override
    public void userWeapon() {
        System.out.println("使用斧头战斗");
    }
}
