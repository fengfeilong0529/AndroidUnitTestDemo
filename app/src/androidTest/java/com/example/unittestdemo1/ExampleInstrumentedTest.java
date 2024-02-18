package com.example.unittestdemo1;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.RequiresDevice;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 * 插桩测试：
 * 需要运行在模拟器或者真机上，可以使用Android系统的api，但是速度较慢
 *<p>
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * <p>
 * 在 JUnit 4.x 测试中，您可以使用注解对测试运行进行配置。此功能可将向测试中添加样板文件和条件代码的需求降至最低。
 * 除了 JUnit 4 支持的标准注解外，测试运行器还支持 Android 特定的注解，包括：
 *
 * @RequiresDevice 指定测试仅在物理设备而不在模拟器上运行。
 * @SdkSupress 禁止在低于给定级别的 Android API 级别上运行测试。例如，要禁止在低于 18 的所有 API 级别上运行测 试，请使用注解 @SDKSupress(minSdkVersion=18)。
 * @SmallTest、@MediumTest 和 @LargeTest 指定测试的运行时长以及运行频率。
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test. 通过InstrumentationRegistry来获取Context
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.unittestdemo1", appContext.getPackageName());
    }

    //指定该测试方法仅在物理设备而不在模拟器上运行
    @RequiresDevice
    public void mustRunOnDevice() {
        System.out.println("===mustRunOnDevice()===");
    }

    //指定该测试方法的最低API级别
    @SdkSuppress(minSdkVersion = 18)
    public void setMinSdkTest() {
        System.out.println("===mustRunOnDevice()===");
    }


}
