/*
 * Copyright 2012-2013 eBay Software Foundation and ios-driver committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.libimobiledevice.ios.driver.test;

import org.libimobiledevice.ios.driver.binding.exceptions.SDKException;
import org.libimobiledevice.ios.driver.binding.services.DeviceService;
import org.libimobiledevice.ios.driver.binding.services.IOSDevice;
import org.libimobiledevice.ios.driver.binding.services.InformationService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.libimobiledevice.ios.driver.test.ConnectedDevices.main;

// TODO freynaud create a proprty file with expected values.
public class InformationServiceTest {

  private InformationService service;


  @BeforeClass
  public void setup() throws SDKException {
    IOSDevice d = DeviceService.get(main);
    service = new InformationService(d);
  }

  @AfterClass
  public void tearDown() throws SDKException {
    service.free();
  }

  @Test
  public void serviceCanGetName() throws SDKException {
    String name = service.getDeviceName();
    System.out.println(name);
  }

  @Test
  public void serviceCanGetType() throws SDKException {
    String type = service.getDeviceType();
    System.out.println("type :" + type);
  }

  @Test
  public void serviceCanGetLanguage() throws SDKException {
    String language = service.getLanguage();
    System.out.println("language :" + language);
  }

  @Test
  public void serviceCanGetLocale() throws SDKException {
    String locale = service.getLocale();
    System.out.println("locale :" + locale);
  }

  @Test
  public void serviceCanGetProductVersion() throws SDKException {
    String productVersion = service.getProductVersion();
    System.out.println("productVersion :" + productVersion);
  }

  @Test
  public void serviceCanGetValueAsXMLAll() throws SDKException {
    String xml = service.getValueAsXML(null, null);
    System.out.println("xml :" + xml);
  }

  @Test
  public void serviceCanGetPasswordProtectedState() throws SDKException {
    System.out.println("protected ? " + service.isPasswordProtected());
  }


  @Test
  public void serviceCanGetValueAsXMLAllKeysOfADomain() throws SDKException {
    String xml = service.getValueAsXML("com.apple.international", null);
    System.out.println("xml :" + xml);
  }

  @Test
  public void serviceCanGetValueAsXMLOneKey() throws SDKException {
    String xml = service.getValueAsXML("com.apple.international", "HostKeyboard");
    System.out.println("xml :" + xml);
  }

  @Test
  public void test() throws SDKException {
    String xml = service.getValueAsXML(null, "TimeIntervalSince1970");
    System.out.println("xml :" + xml);
  }

  @Test
  public void surviveNonExistingKeys() throws SDKException {
    String xml = service.getValueAsXML("com.apple.international", "doesn't exist");
    Assert.assertNull(xml);
  }

  @Test(enabled = false)
  public void serviceCanSetDeviceName() throws SDKException {
    service.setDeviceName("test device");
  }

  @Test
  public void serviceCanSetLocale() throws SDKException {
    service.setLocale("en_GB");
  }

  @Test
  public void serviceCanSetLanguage() throws SDKException,
                                             InterruptedException {
    service.setLanguage("en");
  }

  @Test
  public void serviceCanGetDeviceEnabled() throws SDKException {
    boolean isDev = service.isDevModeEnabled();
    System.out.println("dev mode :" + isDev);
  }

  @Test
  public void getTime() throws SDKException, InterruptedException {
    System.out.println(service.getDate());
  }
}
