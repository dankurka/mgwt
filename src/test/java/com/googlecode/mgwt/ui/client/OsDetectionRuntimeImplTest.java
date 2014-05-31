package com.googlecode.mgwt.ui.client;

import com.googlecode.mgwt.linker.server.propertyprovider.test.UserAgents;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test for {@link OsDetectionRuntimeImpl}.
 * @author dankurka
 */
@RunWith(MockitoJUnitRunner.class)
public class OsDetectionRuntimeImplTest {

  private OsDetectionRuntimeImpl osDetection;
  private String userAgent;
  private double devicePixelRatio;

  @Before
  public void before() {
    osDetection = new OsDetectionRuntimeImpl() {
      @Override
      String getUserAgent() {
        return userAgent.toLowerCase();
      }

      @Override
      double getDevicePixelRatio() {
        return devicePixelRatio;
      }
    };
  }

  @Test
  public void testNexus5() {
    userAgent = UserAgents.ANDROID_4_4_PHONE_NEXUS_5__USERAGENT;
    devicePixelRatio = 3;

    Assert.assertTrue(osDetection.isAndroid());
    Assert.assertTrue(osDetection.isAndroid4_4_OrHigher());
    Assert.assertTrue(osDetection.isAndroidPhone());
    Assert.assertTrue(osDetection.isPhone());

    Assert.assertFalse(osDetection.isAndroid2x());
    Assert.assertFalse(osDetection.isAndroidTablet());
    Assert.assertFalse(osDetection.isBlackBerry());
    Assert.assertFalse(osDetection.isDesktop());
    Assert.assertFalse(osDetection.isIOs());
    Assert.assertFalse(osDetection.isIPad());
    Assert.assertFalse(osDetection.isIPadRetina());
    Assert.assertFalse(osDetection.isIPhone());
    Assert.assertFalse(osDetection.isRetina());
    Assert.assertFalse(osDetection.isTablet());
  }

  @Test
  public void testNexus7() {
    userAgent = UserAgents.ANDROID_4_4_TABLET_NEXUS_7_USERAGENT;
    devicePixelRatio = 2;

    Assert.assertTrue(osDetection.isAndroid());
    Assert.assertTrue(osDetection.isAndroid4_4_OrHigher());
    Assert.assertTrue(osDetection.isAndroidTablet());
    Assert.assertTrue(osDetection.isTablet());

    Assert.assertFalse(osDetection.isAndroid2x());
    Assert.assertFalse(osDetection.isAndroidPhone());
    Assert.assertFalse(osDetection.isBlackBerry());
    Assert.assertFalse(osDetection.isDesktop());
    Assert.assertFalse(osDetection.isIOs());
    Assert.assertFalse(osDetection.isIPad());
    Assert.assertFalse(osDetection.isIPadRetina());
    Assert.assertFalse(osDetection.isIPhone());
    Assert.assertFalse(osDetection.isPhone());
    Assert.assertFalse(osDetection.isRetina());
  }

  @Test
  public void testIphoneIOS7() {
    userAgent = UserAgents.IOS_IPHONE_7_1;
    devicePixelRatio = 2;

    Assert.assertTrue(osDetection.isIOs());
    Assert.assertTrue(osDetection.isPhone());
    Assert.assertTrue(osDetection.isRetina());

    Assert.assertFalse(osDetection.isAndroid());
    Assert.assertFalse(osDetection.isAndroid4_4_OrHigher());
    Assert.assertFalse(osDetection.isAndroidTablet());
    Assert.assertFalse(osDetection.isTablet());
    Assert.assertFalse(osDetection.isAndroid2x());
    Assert.assertFalse(osDetection.isAndroidPhone());
    Assert.assertFalse(osDetection.isBlackBerry());
    Assert.assertFalse(osDetection.isDesktop());
    Assert.assertFalse(osDetection.isIPad());
    Assert.assertFalse(osDetection.isIPadRetina());
    Assert.assertFalse(osDetection.isIPhone());
  }

  @Test
  public void testIPadMiniIOS7() {
    userAgent = UserAgents.IOS_IPAD_MINI_7_1;
    devicePixelRatio = 2;

    Assert.assertTrue(osDetection.isIOs());
    Assert.assertTrue(osDetection.isTablet());
    Assert.assertTrue(osDetection.isIPadRetina());

    Assert.assertFalse(osDetection.isAndroid());
    Assert.assertFalse(osDetection.isAndroid4_4_OrHigher());
    Assert.assertFalse(osDetection.isAndroidTablet());
    Assert.assertFalse(osDetection.isAndroid2x());
    Assert.assertFalse(osDetection.isAndroidPhone());
    Assert.assertFalse(osDetection.isBlackBerry());
    Assert.assertFalse(osDetection.isDesktop());
    Assert.assertFalse(osDetection.isIPad());
    Assert.assertFalse(osDetection.isIPhone());
    Assert.assertFalse(osDetection.isPhone());
    Assert.assertFalse(osDetection.isRetina());
  }

  @Test
  public void testIPadIOS7() {
    userAgent = UserAgents.IOS_IPAD_7_1;
    devicePixelRatio = 2;

    Assert.assertTrue(osDetection.isIOs());
    Assert.assertTrue(osDetection.isTablet());
    Assert.assertTrue(osDetection.isIPadRetina());

    Assert.assertFalse(osDetection.isAndroid());
    Assert.assertFalse(osDetection.isAndroid4_4_OrHigher());
    Assert.assertFalse(osDetection.isAndroidTablet());
    Assert.assertFalse(osDetection.isAndroid2x());
    Assert.assertFalse(osDetection.isAndroidPhone());
    Assert.assertFalse(osDetection.isBlackBerry());
    Assert.assertFalse(osDetection.isDesktop());
    Assert.assertFalse(osDetection.isIPad());
    Assert.assertFalse(osDetection.isIPhone());
    Assert.assertFalse(osDetection.isPhone());
    Assert.assertFalse(osDetection.isRetina());
  }
}
