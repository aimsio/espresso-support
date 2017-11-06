package com.novoda.espresso;

import android.app.Instrumentation;
import android.support.annotation.LayoutRes;
import android.support.test.InstrumentationRegistry;
import android.view.View;

public class TalkBackViewTestRule<T extends View> extends ViewTestRule<T> {

    private final TalkBackStateSettingRequester talkBackStateSettingRequester = new TalkBackStateSettingRequester();

    public TalkBackViewTestRule(@LayoutRes int layoutId) {
        this(ViewTestRule.<T>createInflateFromXmlViewCreator(layoutId));
    }

    public TalkBackViewTestRule(ViewCreator<T> viewCreator) {
        this(InstrumentationRegistry.getInstrumentation(), viewCreator);
    }

    public TalkBackViewTestRule(Instrumentation instrumentation, ViewCreator<T> viewCreator) {
        super(instrumentation, viewCreator);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        talkBackStateSettingRequester.requestEnableTalkBack();
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        talkBackStateSettingRequester.requestDisableTalkBack();
    }
}
