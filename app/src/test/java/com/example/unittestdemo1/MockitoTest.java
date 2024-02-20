package com.example.unittestdemo1;

import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

/**
 * Mockito：
 * 模拟数据框架
 * 参考Blog：
 * https://blog.csdn.net/m0_58026506/article/details/131423775
 */
public class MockitoTest {

    @Test
    public void mockTest() {
        //create mock
        List mockedList = mock(List.class);
        //use mock object
        mockedList.add("one");
        mockedList.clear();
        //验证add方法是否在前面被调用了一次，且参数为“one”。clear方法同样。
        verify(mockedList).add("one");
        verify(mockedList).clear();
        //下面的验证会失败。因为没有调用过add("two")。

//        verify(mockedList).add("two");

        //======分割线======
        //是否add("twice")被调用了两次。
        verify(mockedList, times(1)).add("one");
        //验证add("twice")被调用了至少一次。以及其他。
        verify(mockedList, atLeastOnce()).add("one");
        verify(mockedList, atLeast(1)).add("one");
        verify(mockedList, atMost(5)).add("one");
        verify(mockedList, never()).add("two");
    }

    //方式一：通过mock()静态方法模拟出一个实例
    @Test
    public void mockTest2() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
        // stubbing appears before the actual execution
        //这句话 Mockito 会解析为：当对象 mockedList 调用 get()方法，并且参数为 0 时，返回结果为"first"，
        // 这相当于定制了我们 mock 对象的行为结果（mock LinkedList 对象为 mockedList，指定其行为 get(0)，则返回结果为 "first")
        when(mockedList.get(0)).thenReturn("first");
        // the following prints "first"
        System.out.println(mockedList.get(0));
        // the following prints "null" because get(999) was not stubbed
        //由于 mockedList 没有指定 get(999) 的行为，所以其结果为 null。因为 Mockito 的底层原理是使用 cglib 动态生成一个 代理类对象，
        //因此，mock 出来的对象其实质就是一个 代理，该代理在 没有配置/指定行为 的情况下，默认返回 空值。
        System.out.println(mockedList.get(999));
    }

    //方式二：通过注解@Mock来模拟出一个实例
    @Mock
    private Intent mIntent;

    //规则 MockitoRule 会自动帮我们调用 MockitoAnnotations.initMocks(this) 去 实例化 出 注解 的成员变量，我们就无需手动进行初始化了。
    //会自动对@Mock, @Spy, @InjectMocks 等注解的成员变量进行初始化
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void mockAndroid() {
        Intent intent = mockIntent();
        assertThat(intent.getAction()).isEqualTo("com.yn.test.mockito");
        assertThat(intent.getStringExtra("Name")).isEqualTo("Whyn");
        System.out.println("----getScheme()----" + intent.getScheme());
    }

    private Intent mockIntent() {
        //when(methodCall).thenReturn(value)用法：指定methodCall返回数据为value；未指定的则为null
        //指定mIntent.getAction()的数据为com.yn.test.mockito
        when(mIntent.getAction()).thenReturn("com.yn.test.mockito");
        when(mIntent.getScheme()).thenReturn("我是模拟的getScheme()数据！！！");
        when(mIntent.getStringExtra("Name")).thenReturn("Whyn");
        return mIntent;
    }

    @Test
    public void mockTest3() {
        // You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // Stubbing
        //对同个方法配置多次后，最后一次起作用
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(0)).thenReturn("two");
        when(mockedList.get(0)).thenReturn("three");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        // Following prints "first"，配置多次后以最后次为准
        System.out.println(mockedList.get(0));
        // Following throws runtime exception
//        System.out.println(mockedList.get(1));
        // Following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        // Although it is possible to verify a stubbed invocation, usually it's just redundant
        // If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        // If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void mockTest4() {
        LinkedList mockedList = mock(LinkedList.class);
        // Stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");
        // Following prints "element"
        System.out.println(mockedList.get(999));
        // You can also verify using an argument matcher
        verify(mockedList).get(anyInt());
    }

    /**
     * 校验次数
     */
    @Test
    public void mockTest5() {
        LinkedList mockedList = mock(LinkedList.class);
        // Use mock
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // Follow two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        // Exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        // Verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        // Verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    @Test
    public void mockTest6() {
        LinkedList mockedList = mock(LinkedList.class);

        doThrow(new RuntimeException()).when(mockedList).clear();
        // following throws RuntimeException
        mockedList.clear();
    }

    /**
     * 按顺序校验
     */
    @Test
    public void mockTest7() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);
        // Use a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");
        // Create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);
        // Following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        // Use mocks
        firstMock.add("was called first");
        secondMock.add("was called second");
        // Create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder2 = inOrder(firstMock, secondMock);
        // Following will make sure that firstMock was called before secondMock
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");
    }

    /**
     * 存根连续调用
     * 对于同一个方法，如果我们想让其在 多次调用 中分别 返回不同 的数值，那么就可以使用存根连续调用
     */
    @Test
    public void mockTest8() {
        List mock = mock(List.class);

        when(mock.get(0))
                .thenThrow(new RuntimeException())
                .thenReturn("foo", "one", "two", "three");

        // First call: throws runtime exception:
        try {
            mock.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Second call: prints "foo"
        System.out.println(mock.get(0));
        // Any consecutive call: prints "foo" as well (last stubbing wins).
        System.out.println(mock.get(0));
    }


    /**
     * spy监视真实对象
     * 前面使用的都是 mock 出来一个对象。这样，当 没有配置/存根 其具体行为的话，结果就会返回 空类型。
     * 而如果使用 特务对象（spy），那么对于 没有存根 的行为，它会调用 原来对象 的方法。可以把 spy 想象成局部 mock。
     */
    @Test
    public void mockTest9() {
        List list = new LinkedList();
        List spy = spy(list);

        // Optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);
        // Use the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        // Prints "one" - the first element of a list
        System.out.println(spy.get(0));
        // Size() method was stubbed - 100 is printed
        System.out.println(spy.size());
        // Optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    /**
     * spy监视真实对象
     * 由于 spy 是局部 mock，所以有时候使用 when(Object) 时，无法做到存根作用。
     * 此时，就可以考虑使用 doReturn() | Answer() | Throw() 这类方法进行存根
     */
    @Test
    public void mockTest10() {
        List list = new LinkedList();
        List spy = spy(list);
        // Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        try {
            when(spy.get(0)).thenReturn("foo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
        System.out.println(spy.get(0));
    }


}

