package org.vaadin.marcus.ui.regression;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.marcus.ui.pageobjects.MainViewPO;
import org.vaadin.marcus.ui.pageobjects.TBUtils;

import static junit.framework.Assert.assertTrue;

public class RenderingSpeedTest extends TestBenchTestCase {

    private MainViewPO mainViewPO;

    @Before
    public void setUp() {
        mainViewPO = TBUtils.openInitialView().login();
    }

    @Test
    public void testRenderingSpeed() {
        mainViewPO.$(ButtonElement.class).caption("Slow rendering view").first().click();

        int maxAllowedRenderTime = 1000;
        long renderTime = mainViewPO.testBench().timeSpentRenderingLastRequest();
        assertTrue("Render took " + renderTime + "ms, max allowed is " + maxAllowedRenderTime + "ms", renderTime < maxAllowedRenderTime);
    }

    @After
    public void tearDown() {
        mainViewPO.getDriver().close();
    }

}
