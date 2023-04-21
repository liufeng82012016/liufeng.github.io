/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent.locks;
import java.util.concurrent.TimeUnit;

/**
 * {@code Lock} implementations provide more extensive locking
 * operations than can be obtained using {@code synchronized} methods
 * and statements.  They allow more flexible structuring, may have
 * quite different properties, and may support multiple associated
 * {@link Condition} objects.
 * {@code Lock} 实现提供的锁定操作比使用 {@code syncd} 方法和语句获得的要广泛。
 * 它们允许更灵活的结构，可能具有完全不同的属性，并且可以支持多个关联的 {@link Condition} 对象
 *
 * <p>A lock is a tool for controlling access to a shared resource by
 * multiple threads. Commonly, a lock provides exclusive access to a
 * shared resource: only one thread at a time can acquire the lock and
 * all access to the shared resource requires that the lock be
 * acquired first. However, some locks may allow concurrent access to
 * a shared resource, such as the read lock of a {@link ReadWriteLock}.
 *
 * <p>The use of {@code synchronized} methods or statements provides
 * access to the implicit monitor lock associated with every object, but
 * forces all lock acquisition and release to occur in a block-structured way:
 * when multiple locks are acquired they must be released in the opposite
 * order, and all locks must be released in the same lexical scope in which
 * they were acquired.
 * 使用 {@code syncd} 方法或语句提供对与每个对象关联的隐式监视器锁的访问，但强制所有锁获取和释放以块结构方式进行：
 * 当获取多个锁时，必须以相反的顺序释放它们，并且所有锁必须在获取它们的同一词法范围内释放
 *
 * <p>While the scoping mechanism for {@code synchronized} methods
 * and statements makes it much easier to program with monitor locks,
 * and helps avoid many common programming errors involving locks,
 * there are occasions where you need to work with locks in a more
 * flexible way. For example, some algorithms for traversing
 * concurrently accessed data structures require the use of
 * &quot;hand-over-hand&quot; or &quot;chain locking&quot;: you
 * acquire the lock of node A, then node B, then release A and acquire
 * C, then release B and acquire D and so on.  Implementations of the
 * {@code Lock} interface enable the use of such techniques by
 * allowing a lock to be acquired and released in different scopes,
 * and allowing multiple locks to be acquired and released in any
 * order.
 *虽然 {@code syncd} 方法和语句的作用域机制使使用监视器锁进行编程变得更加容易，并有助于避免许多涉及锁的常见编程错误，但在某些情况下，您需要以更灵活的方式使用锁。
 * 例如，一些遍历并发访问的数据结构的算法需要使用“交接”或“链锁”：您获取节点 A 的锁，然后获取节点 B，然后释放 A 并获取 C，然后释放 B 并获取 D 等等。{@code Lock} 接口的实现允许使用此类技术，方法是允许在不同的范围内获取和释放锁，并允许以任何顺序获取和释放多个锁
 * <p>With this increased flexibility comes additional
 * responsibility. The absence of block-structured locking removes the
 * automatic release of locks that occurs with {@code synchronized}
 * methods and statements. In most cases, the following idiom
 * should be used:
 * 随着灵活性的提高，责任也随之增加。缺少块结构锁定消除了 {@code syncd} 方法和语句发生的锁的自动释放。在大多数情况下，应使用以下成语
 *
 *  <pre> {@code
 * Lock l = ...;
 * l.lock();
 * try {
 *   // access the resource protected by this lock
 * } finally {
 *   l.unlock();
 * }}</pre>
 *
 * When locking and unlocking occur in different scopes, care must be
 * taken to ensure that all code that is executed while the lock is
 * held is protected by try-finally or try-catch to ensure that the
 * lock is released when necessary.
 * 当锁定和解锁发生在不同的作用域时，必须注意确保在保持锁时执行的所有代码都受到 try-finally 或 try-catch 的保护，以确保在必要时释放锁
 *
 * <p>{@code Lock} implementations provide additional functionality
 * over the use of {@code synchronized} methods and statements by
 * providing a non-blocking attempt to acquire a lock ({@link
 * #tryLock()}), an attempt to acquire the lock that can be
 * interrupted ({@link #lockInterruptibly}, and an attempt to acquire
 * the lock that can timeout ({@link #tryLock(long, TimeUnit)}).
 *{@code Lock} 实现通过提供获取锁的非阻塞尝试（{@link tryLock（）}）、获取可中断的锁的尝试（{@link <p>lockInterruptibly}）
 * 以及获取可能超时的锁的尝试（{@link tryLock（long， TimeUnit）}）来提供与使用 {@code syncd} 方法和语句相关的附加功能。
 * <p>A {@code Lock} class can also provide behavior and semantics
 * that is quite different from that of the implicit monitor lock,
 * such as guaranteed ordering, non-reentrant usage, or deadlock
 * detection. If an implementation provides such specialized semantics
 * then the implementation must document those semantics.
 * 类还可以提供与隐式监视器锁完全不同的行为和语义，例如保证排序、非重入用法或死锁检测。如果实现提供了这样的专用语义，则实现必须记录这些语义
 *
 * <p>Note that {@code Lock} instances are just normal objects and can
 * themselves be used as the target in a {@code synchronized} statement.
 * Acquiring the
 * monitor lock of a {@code Lock} instance has no specified relationship
 * with invoking any of the {@link #lock} methods of that instance.
 * It is recommended that to avoid confusion you never use {@code Lock}
 * instances in this way, except within their own implementation.
 *
 * 请注意，{@code Lock} 实例只是普通对象，它们本身可以用作 {@code syncd} 语句中的目标。
 * 获取 {@code Lock} 实例的监视器锁与调用该实例的任何 {@link lock} 方法没有指定的关系。为避免混淆，建议永远不要以这种方式使用 {@code Lock} 实例，除非在它们自己的实现中
 * <p>Except where noted, passing a {@code null} value for any
 * parameter will result in a {@link NullPointerException} being
 * thrown.
 * 除非另有说明，否则为任何参数传递 {@code null} 值都将导致引发 {@link NullPointerException}
 *
 * <h3>Memory Synchronization</h3>
 *
 * <p>All {@code Lock} implementations <em>must</em> enforce the same
 * memory synchronization semantics as provided by the built-in monitor
 * lock, as described in
 * 所有 {@code Lock} 实现<em>都必须<em>强制实施内置监视器锁提供的相同内存同步语义，如中所述
 * <a href="https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.4">
 * The Java Language Specification (17.4 Memory Model)</a>:
 * <ul>
 * <li>A successful {@code lock} operation has the same memory
 * synchronization effects as a successful <em>Lock</em> action.
 * <li>A successful {@code unlock} operation has the same
 * memory synchronization effects as a successful <em>Unlock</em> action.
 * </ul>
 *
 * Unsuccessful locking and unlocking operations, and reentrant
 * locking/unlocking operations, do not require any memory
 * synchronization effects.
 * 不成功的锁定和解锁操作以及可重入锁定解锁操作不需要任何内存同步效果
 *
 * <h3>Implementation Considerations</h3>
 *
 * <p>The three forms of lock acquisition (interruptible,
 * non-interruptible, and timed) may differ in their performance
 * characteristics, ordering guarantees, or other implementation
 * qualities.  Further, the ability to interrupt the <em>ongoing</em>
 * acquisition of a lock may not be available in a given {@code Lock}
 * class.  Consequently, an implementation is not required to define
 * exactly the same guarantees or semantics for all three forms of
 * lock acquisition, nor is it required to support interruption of an
 * ongoing lock acquisition.  An implementation is required to clearly
 * document the semantics and guarantees provided by each of the
 * locking methods. It must also obey the interruption semantics as
 * defined in this interface, to the extent that interruption of lock
 * acquisition is supported: which is either totally, or only on
 * method entry.
 * 锁获取的三种形式（可中断、不可中断和定时）在性能特征、排序保证或其他实现质量方面可能有所不同。
 * 此外，中断<em>正在进行的<em>锁获取的功能在给定的 {@code Lock} 类中可能不可用。
 * 因此，实现不需要为所有三种形式的锁获取定义完全相同的保证或语义，也不需要支持中断正在进行的锁获取。需要实现来清楚地记录每个锁定方法提供的语义和保证。
 * 它还必须遵守此接口中定义的中断语义，只要支持中断锁获取：要么完全中断，要么仅在方法输入时中断
 *
 * <p>As interruption generally implies cancellation, and checks for
 * interruption are often infrequent, an implementation can favor responding
 * to an interrupt over normal method return. This is true even if it can be
 * shown that the interrupt occurred after another action may have unblocked
 * the thread. An implementation should document this behavior.
 * 由于中断通常意味着取消，并且中断检查通常不频繁，因此实现可能倾向于响应中断而不是正常方法返回。即使可以证明中断发生在另一个操作可能取消了线程阻塞之后，也是如此。实现应记录此行为
 *
 * @see ReentrantLock
 * @see Condition
 * @see ReadWriteLock
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface Lock {

    /**
     * Acquires the lock.
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     * 如果锁不可用，则当前线程将出于线程调度目的而被禁用，并处于休眠状态，直到获取锁为止
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation may be able to detect erroneous use
     * of the lock, such as an invocation that would cause deadlock, and
     * may throw an (unchecked) exception in such circumstances.  The
     * circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     * {@code Lock} 实现可能能够检测锁的错误使用，例如会导致死锁的调用，并可能在这种情况下引发（未选中的）异常。环境和异常类型必须由该 {@code Lock} 实现记录
     */
    void lock();

    /**
     * Acquires the lock unless the current thread is
     * {@linkplain Thread#interrupt interrupted}.
     * 获取锁，除非当前线程是 {@linkplain线程中断中断}
     *
     * <p>Acquires the lock if it is available and returns immediately.
     * 获取锁（如果可用）并立即返回
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until
     * one of two things happens:
     * 如果锁不可用，则当前线程将出于线程调度目的而被禁用并处于休眠状态，直到发生以下两种情况之一
     *
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * 锁由当前线程获取
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported.
     * 其他一些线程{@linkplain线程中断中断}当前线程，支持锁获取中断
     * </ul>
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring the
     * lock, and interruption of lock acquisition is supported,
     * 在进入此方法时设置其中断状态;或者在<li>获取锁时{@linkplain线程中断}，并且支持锁获取中断
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p><b>Implementation Considerations</b>
     * 实现注意事项
     *
     * <p>The ability to interrupt a lock acquisition in some
     * implementations may not be possible, and if possible may be an
     * expensive operation.  The programmer should be aware that this
     * may be the case. An implementation should document when this is
     * the case.
     * 在某些实现中，中断锁获取的能力可能是不可能的，如果可能的话，这可能是一个昂贵的操作。程序员应该意识到可能是这种情况。在这种情况下，实现应记录
     *
     * <p>An implementation can favor responding to an interrupt over
     * normal method return.
     * 实现可能有利于响应中断而不是正常方法返回。
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would
     * cause deadlock, and may throw an (unchecked) exception in such
     * circumstances.  The circumstances and the exception type must
     * be documented by that {@code Lock} implementation.
     * {@code Lock} 实现可能能够检测锁的错误使用，例如会导致死锁的调用，并可能在这种情况下引发（未选中的）异常。环境和异常类型必须由该 {@code Lock} 实现记录
     *
     * @throws InterruptedException if the current thread is
     *         interrupted while acquiring the lock (and interruption
     *         of lock acquisition is supported)
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * Acquires the lock only if it is free at the time of invocation.
     * 仅当锁在调用时处于空闲状态时，才获取锁。
     *
     * <p>Acquires the lock if it is available and returns immediately
     * with the value {@code true}.
     * 获取锁（如果可用），并立即返回值 {@code true}。
     * If the lock is not available then this method will return
     * immediately with the value {@code false}.
     * 如果锁不可用，则此方法将立即返回值 {@code false}。
     *
     * <p>A typical usage idiom for this method would be:
     *  <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // manipulate protected state
     *   } finally {
     *     lock.unlock();
     *   }
     * } else {
     *   // perform alternative actions
     * }}</pre>
     *
     * This usage ensures that the lock is unlocked if it was acquired, and
     * doesn't try to unlock if the lock was not acquired.
     * 此用法可确保在获取锁时解锁锁，如果未获取锁，则不会尝试解锁
     *
     * @return {@code true} if the lock was acquired and
     *         {@code false} otherwise
     */
    boolean tryLock();

    /**
     * Acquires the lock if it is free within the given waiting time and the
     * current thread has not been {@linkplain Thread#interrupt interrupted}.
     * 如果锁在给定的等待时间内空闲并且当前线程尚未 {@linkplain线程中断}，则获取锁。
     * <p>If the lock is available this method returns immediately
     * with the value {@code true}.
     * If the lock is not available then
     * the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of three things happens:
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported; or
     * <li>The specified waiting time elapses
     * 指定的等待时间已过
     * </ul>
     *
     * <p>If the lock is acquired then the value {@code true} is returned.
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring
     * the lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>If the specified waiting time elapses then the value {@code false}
     * is returned.
     * If the time is
     * less than or equal to zero, the method will not wait at all.
     * 如果时间小于或等于零，则该方法根本不会等待。
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The ability to interrupt a lock acquisition in some implementations
     * may not be possible, and if possible may
     * be an expensive operation.
     * 在某些实现中中断锁获取的能力可能是不可能的，如果可能的话，这可能是一个昂贵的操作,程序员应该意识到可能是这种情况。在这种情况下，实现应记录。
     * The programmer should be aware that this may be the case. An
     * implementation should document when this is the case.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return, or reporting a timeout.
     * 实现可能有利于响应中断而不是正常方法返回，或报告超时
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would cause
     * deadlock, and may throw an (unchecked) exception in such circumstances.
     * The circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return {@code true} if the lock was acquired and {@code false}
     *         if the waiting time elapsed before the lock was acquired
     *
     * @throws InterruptedException if the current thread is interrupted
     *         while acquiring the lock (and interruption of lock
     *         acquisition is supported)
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Releases the lock.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation will usually impose
     * restrictions on which thread can release a lock (typically only the
     * holder of the lock can release it) and may throw
     * an (unchecked) exception if the restriction is violated.
     * Any restrictions and the exception
     * type must be documented by that {@code Lock} implementation.
     * {@code Lock} 实现通常会对哪个线程可以释放锁施加限制（通常只有锁的持有者才能释放它），并且如果违反限制，可能会引发（未选中的）异常。任何限制和异常类型都必须由该 {@code Lock} 实现记录
     */
    void unlock();

    /**
     * Returns a new {@link Condition} instance that is bound to this
     * {@code Lock} instance.
     *
     * <p>Before waiting on the condition the lock must be held by the
     * current thread.
     * 在等待条件之前，锁必须由当前线程持有
     * A call to {@link Condition#await()} will atomically release the lock
     * before waiting and re-acquire the lock before the wait returns.
     * 对 {@link Conditionawait（）} 的调用将在等待之前以原子方式释放锁，并在等待返回之前重新获取锁
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The exact operation of the {@link Condition} instance depends on
     * the {@code Lock} implementation and must be documented by that
     * implementation.
     *
     * @return A new {@link Condition} instance for this {@code Lock} instance
     * @throws UnsupportedOperationException if this {@code Lock}
     *         implementation does not support conditions
     */
    Condition newCondition();
}
