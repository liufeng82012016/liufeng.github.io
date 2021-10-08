package liufeng.jdk.jvm.ma;

public class FinalizeTest {
    public static FinalizeTest finalizeTest = null;

    public void isAlive() {
        System.out.println("yes ,I am alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method exec");
        finalizeTest = this;
    }

    public static void main(String[] args) throws Throwable {
        finalizeTest = new FinalizeTest();
        // 抛弃引用，提醒系统GC
        finalizeTest = null;
        System.gc();
        Thread.sleep(500);
        if (finalizeTest != null) {
            finalizeTest.isAlive();
        } else {
            System.out.println("no,I am died");
        }
        //------------------- 重复代码-------------------
        finalizeTest = null;
        System.gc();
        Thread.sleep(500);
        if (finalizeTest != null) {
            finalizeTest.isAlive();
        } else {
            System.out.println("no,I am died");
        }
    }
}
