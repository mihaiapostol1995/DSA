public class SynchronizedInheritance {
    public void doSomething() {
        synchronized (this) {
            System.out.println("do in parent: " + this.getClass());
        }
    }

    public void doSomethingElse() {
        System.out.println("do something else parent: " + this);
    }

    public static void main(String[] args) {
        SynchronizedInheritance s = new B();
        s.doSomething();
        s.doSomethingElse();
    }
}

class B extends SynchronizedInheritance{
    public void doSomething() {
        synchronized (this) {
            System.out.println("do in child: " + this.getClass());
        }
    }
}
