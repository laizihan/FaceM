package com.lp.example.facem;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by laizihan on 6/2/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    private Button nextButton;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public MainActivityTest(){
        super(MainActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        mainActivity = getActivity();
        nextButton = (Button) mainActivity.findViewById(R.id.next_activity);




        //Controller.init((Activity)getContext());
    }

    @MediumTest
    public void testButton_layout(){
        View decorView = mainActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,decorView);
        final  ViewGroup.LayoutParams layoutParams = nextButton.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }



    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testActivityTestCaseSetUpProperly() {
        final int a =2;
        final int b =2;
        assertEquals(a,b);
    }

}
