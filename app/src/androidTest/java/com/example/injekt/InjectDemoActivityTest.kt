package com.example.injekt

import android.test.ActivityInstrumentationTestCase2
import junit.framework.TestResult

public class InjektDemoActivityTest() : ActivityInstrumentationTestCase2<InjektDemoActivity>(javaClass()) {
    override fun run(): TestResult {
        getActivity().fi
        return super.run()
    }
}