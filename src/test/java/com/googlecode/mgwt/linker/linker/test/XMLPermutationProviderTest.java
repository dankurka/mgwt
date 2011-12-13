package com.googlecode.mgwt.linker.linker.test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.mgwt.linker.linker.XMLPermutationProvider;
import com.googlecode.mgwt.linker.linker.XMLPermutationProviderException;
import com.googlecode.mgwt.linker.server.BindingProperty;

public class XMLPermutationProviderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadFile() throws XMLPermutationProviderException {
		XMLPermutationProvider xmlPermutationProvider = new XMLPermutationProvider();

		InputStream stream = getClass().getResourceAsStream("resources/example.manifestmap.xml");

		Map<String, List<BindingProperty>> map = xmlPermutationProvider.getBindingProperties(stream);

		Assert.assertEquals(15, map.size());
		Assert.assertNotNull(map.get("C83A451EFE8ADF0BDB46AEAAC44B0063"));
		Assert.assertNotNull(map.get("C5038CC6700BE1E13984B0C9B1C1CF39"));
		Assert.assertNotNull(map.get("BC0FBD6E19A643C849F55798FBDC6B7C"));
		Assert.assertNotNull(map.get("CE6093653BC656C5325AD9A3AD53190F"));
		Assert.assertNotNull(map.get("AFE43E4243D6D9E467B4EF9AC1B9A556"));
		Assert.assertNotNull(map.get("214B4087FF08669D2E69F4A54A473857"));
		Assert.assertNotNull(map.get("04898CDE872132FE196264EA78903568"));
		Assert.assertNotNull(map.get("1DE681F76CF42D7DAB43FBA413692180"));
		Assert.assertNotNull(map.get("24CDDA719C9F0168D42F39A56432B111"));
		Assert.assertNotNull(map.get("3375A4CB10F21AB527E717409E342385"));
		Assert.assertNotNull(map.get("337DF9DBA826DEEAAB23EC650DD4A527"));
		Assert.assertNotNull(map.get("7C2CC23ECDA1A4E8BC60B81F5AEAA04C"));
		Assert.assertNotNull(map.get("65593A1B9B5E003CE88C406E73A3F475"));
		Assert.assertNotNull(map.get("9EFCF8D411F0BC1926A3031A56459663"));
		Assert.assertNotNull(map.get("7064A1A163CFC3C63ADF5DF50E91C5BB"));

		List<BindingProperty> list = map.get("C83A451EFE8ADF0BDB46AEAAC44B0063");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "blackberry")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("C5038CC6700BE1E13984B0C9B1C1CF39");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "android")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("BC0FBD6E19A643C849F55798FBDC6B7C");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "retina")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("CE6093653BC656C5325AD9A3AD53190F");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "ipad")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("AFE43E4243D6D9E467B4EF9AC1B9A556");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "desktop")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("214B4087FF08669D2E69F4A54A473857");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "iphone")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("04898CDE872132FE196264EA78903568");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "android")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("1DE681F76CF42D7DAB43FBA413692180");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "desktop")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "gecko1_8")));

		list = map.get("24CDDA719C9F0168D42F39A56432B111");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "blackberry")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("3375A4CB10F21AB527E717409E342385");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "android_tablet")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("337DF9DBA826DEEAAB23EC650DD4A527");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "ipad")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("7C2CC23ECDA1A4E8BC60B81F5AEAA04C");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "retina")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("65593A1B9B5E003CE88C406E73A3F475");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "iphone")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("9EFCF8D411F0BC1926A3031A56459663");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "desktop")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "mobilesafari")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

		list = map.get("7064A1A163CFC3C63ADF5DF50E91C5BB");
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains(new BindingProperty("mgwt.os", "android_tablet")));
		Assert.assertTrue(list.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(list.contains(new BindingProperty("user.agent", "safari")));

	}
}
